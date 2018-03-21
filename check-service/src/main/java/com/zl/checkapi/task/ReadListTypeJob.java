package com.zl.checkapi.task;

import com.zl.checkapi.cache.ListTypeInfo;
import com.zl.checkapi.mysql.dao.DictItemMapper;
import com.zl.checkapi.mysql.domain.DictItem;
import com.zl.checkapi.mysql.domain.DictItemExample;
import com.zl.checkapi.pojo.CertNOToStarRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouliang on 2018/3/20 0020.
 */
public class ReadListTypeJob {
    @Autowired
    private DictItemMapper dictItemMapper;

    @Autowired
    private ListTypeInfo listTypeInfo;

    private Logger logger = LoggerFactory.getLogger(ReadListTypeJob.class);

    public void load(){
        execute();
    }

    private void execute(){
        try{
            logger.info("开始加载名单到内存");
            listTypeInfo.getReentrantLock().writeLock().lock();
            //加载一级分类
            List<String> fstTypeList = getFstType();
            listTypeInfo.setFstType(fstTypeList);
            //加载二级分类
            List<DictItem> secTypeList = getSecType();
            List<String> secResultList = new ArrayList<>();
            for(DictItem dictItem : secTypeList){
                secResultList.add(dictItem.getItemValue());
            }
            listTypeInfo.setSecType(secResultList);
            //加载一级分类和二级分类对应的code关系
            Map<String, List<String>> relationCode = new HashMap<>(16);
            for(String fstCode : fstTypeList){
                List<String> tempList = new ArrayList<>();
                for(DictItem dictItem : secTypeList){
                    if(fstCode.equals(dictItem.getEntryCode().substring(0,2))){
                        tempList.add(dictItem.getEntryCode());
                    }
                }
                relationCode.put(fstCode, tempList);
            }
            listTypeInfo.setRelationCode(relationCode);
            //加载需要加密的身份证的二级分类code
            listTypeInfo.setCertNoToStarList(getCertNoToStarList());
            //加载加密身份证号的规则
            listTypeInfo.setCertNoToStarRuleList(getCertNOToStarRuleList());
            logger.info("加载名单到内存中完成=>{}", listTypeInfo.getAllInfo());
        }catch (Exception e){
            logger.error("加载名单到内存异常", e);
        }finally {
            listTypeInfo.getReentrantLock().writeLock().unlock();
        }
    }

    private List<String> getFstType(){
        DictItemExample dictItemExample = new DictItemExample();
        DictItemExample.Criteria criteria = dictItemExample.createCriteria();
        criteria.andEntryCodeEqualTo("risk_fst_type");
        List<DictItem> list = dictItemMapper.selectByExample(dictItemExample);
        List<String> resultList = new ArrayList<>();
        if(list.size() > 0){
            for(DictItem dictItem : list){
                resultList.add(dictItem.getItemValue());
            }
        }
        return resultList;
    }

    private List<DictItem> getSecType(){
        DictItemExample dictItemExample = new DictItemExample();
        DictItemExample.Criteria criteria = dictItemExample.createCriteria();
        criteria.andEntryCodeEqualTo("risk_sec_type");
        List<DictItem> list = dictItemMapper.selectByExample(dictItemExample);
        if(list.size() > 0){
            return list;
        }
        return new ArrayList<>();
    }

    private List<String> getCertNoToStarList(){
        DictItemExample dictItemExample = new DictItemExample();
        DictItemExample.Criteria criteria = dictItemExample.createCriteria();
        criteria.andEntryCodeEqualTo("xing_blacklist_type");
        List<DictItem> list = dictItemMapper.selectByExample(dictItemExample);
        List<String> resultList = new ArrayList<>();
        if(list.size()>0){
            for(DictItem dictItem : list){
                resultList.add(dictItem.getItemCode());
            }
        }
        return resultList;
    }

    private List<CertNOToStarRule> getCertNOToStarRuleList(){
        DictItemExample dictItemExample = new DictItemExample();
        DictItemExample.Criteria criteria = dictItemExample.createCriteria();
        criteria.andEntryCodeEqualTo("xing_certno_format");
        List<DictItem> list = dictItemMapper.selectByExample(dictItemExample);
        List<CertNOToStarRule> resultList = new ArrayList<>();
        if(list.size()>0){
            for(DictItem dictItem : list){
                CertNOToStarRule certNOToStarRule = new CertNOToStarRule();
                String[] tempStr = dictItem.getItemValue().split(",");
                certNOToStarRule.setStartIdx(tempStr[0]);
                certNOToStarRule.setEndIdx(tempStr[1]);
                certNOToStarRule.setValue(tempStr[2]);
                resultList.add(certNOToStarRule);
            }
        }
        return resultList;
    }
}

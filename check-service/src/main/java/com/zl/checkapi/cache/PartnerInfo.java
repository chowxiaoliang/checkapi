package com.zl.checkapi.cache;

import com.zl.checkapi.mysql.dao.UsrPartnerMapper;
import com.zl.checkapi.mysql.domain.UsrPartner;
import com.zl.checkapi.mysql.domain.UsrPartnerExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhouliang
 * 加载相关信息到内存
 */
@Component
public class PartnerInfo implements ApplicationContextAware {

    @Autowired
    private UsrPartnerMapper usrPartnerMapper;

    private Map<String, UsrPartner> partnerMap = new HashMap(16);

    private final Logger logger = LoggerFactory.getLogger(PartnerInfo.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.refreshData();
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    /**
     * 每分钟刷新一次
     */
    private void refreshData(){
        UsrPartnerExample usrPartnerExample = new UsrPartnerExample();
        UsrPartnerExample.Criteria criteria = usrPartnerExample.createCriteria();
        criteria.andPartnerIdIsNotNull();
        List<UsrPartner> list = usrPartnerMapper.selectByExample(usrPartnerExample);
        if(list!=null && list.size()>0){
            for(UsrPartner usrPartner : list){
                partnerMap.put(usrPartner.getPartnerId(), usrPartner);
            }
            logger.info("load partnerInfo success => size={}", list.size());
        }
    }

    public UsrPartner getPartnerInfo(String partnerId){
        if(StringUtils.isEmpty(partnerId)){
            return null;
        }else{
            return partnerMap.get(partnerId);
        }
    }
}

package com.zl.checkapi.main;

import com.zl.checkapi.mysql.dao.UsrPartnerMapper;
import com.zl.checkapi.mysql.domain.UsrPartner;
import com.zl.checkapi.mysql.domain.UsrPartnerExample;
import com.zl.checkapi.util.ExcelUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 合作方导商户全程数据到mysql
 */
public class ImportPartnerNameToMysql {

    private final static String FILE_URL = "/app/partner.xlsx";

    public static void main(String[] args){

        ApplicationContext cxt = new ClassPathXmlApplicationContext("application.xml");

        UsrPartnerMapper usrPartnerMapper = cxt.getBean(UsrPartnerMapper.class);

        File file = new File(FILE_URL);
        String path = file.getPath();
        System.out.println("所得文件的路劲是=>"+path);
        System.out.println("等待30m后开始清洗数据...");

        try{
            Thread.sleep(30000);
            long startTime = System.currentTimeMillis();
            Map <String, List<Map<String, String>>> resultMap = ExcelUtils.readExcel(path);
            List<Map<String, String>> resultList = resultMap.get("Sheet1");
            System.out.println("本次更新的数据的大小是=>"+resultList.size());
            for(int i=1;i<resultList.size();i++){
                try{
                    System.out.println("开始进行第"+i+"条数据更新");
                    Map<String, String> stringStringMap = resultList.get(i);
                    String operationId = stringStringMap.get("COLUMN_0");
                    String partnerId = stringStringMap.get("COLUMN_2");
                    String partnerFullName = stringStringMap.get("COLUMN_3");
                    if(StringUtils.isEmpty(partnerFullName)){
                        continue;
                    }
                    if(StringUtils.isNotEmpty(operationId) && StringUtils.isNotEmpty(partnerId)){
                        System.out.println("partnerId=>"+partnerId);
                        System.out.println("partnerFullName=>"+partnerFullName);
                        System.out.println("operationId=>"+operationId);

                        UsrPartnerExample usrPartnerExample = new UsrPartnerExample();
                        UsrPartnerExample.Criteria criteria = usrPartnerExample.createCriteria();
                        criteria.andPartnerIdEqualTo(partnerId);
                        criteria.andOperationIdEqualTo(operationId);

                        UsrPartner usrPartner = new UsrPartner();
                        usrPartner.setPartnerFullname(partnerFullName);
                        usrPartnerMapper.updateByExampleSelective(usrPartner, usrPartnerExample);
                        System.out.println("第"+i+"条数据更新完成");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println("更新数据总共耗时=>"+((endTime-startTime)/1000)+"s");
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        System.exit(0);
    }
}

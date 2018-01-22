package com.zl.checkapi.mysql.dao;

import com.zl.checkapi.mysql.domain.UsrPartner;
import com.zl.checkapi.mysql.domain.UsrPartnerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsrPartnerMapper {
    int countByExample(UsrPartnerExample example);

    int deleteByExample(UsrPartnerExample example);

    int deleteByPrimaryKey(String customId);

    int insert(UsrPartner record);

    int insertSelective(UsrPartner record);

    List<UsrPartner> selectByExample(UsrPartnerExample example);

    UsrPartner selectByPrimaryKey(String customId);

    int updateByExampleSelective(@Param("record") UsrPartner record, @Param("example") UsrPartnerExample example);

    int updateByExample(@Param("record") UsrPartner record, @Param("example") UsrPartnerExample example);

    int updateByPrimaryKeySelective(UsrPartner record);

    int updateByPrimaryKey(UsrPartner record);
}
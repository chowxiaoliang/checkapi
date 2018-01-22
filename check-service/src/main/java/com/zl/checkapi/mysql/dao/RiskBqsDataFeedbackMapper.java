package com.zl.checkapi.mysql.dao;

import com.zl.checkapi.mysql.domain.RiskBqsDataFeedback;
import com.zl.checkapi.mysql.domain.RiskBqsDataFeedbackExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RiskBqsDataFeedbackMapper {
    int countByExample(RiskBqsDataFeedbackExample example);

    int deleteByExample(RiskBqsDataFeedbackExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RiskBqsDataFeedback record);

    int insertSelective(RiskBqsDataFeedback record);

    List<RiskBqsDataFeedback> selectByExample(RiskBqsDataFeedbackExample example);

    RiskBqsDataFeedback selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RiskBqsDataFeedback record, @Param("example") RiskBqsDataFeedbackExample example);

    int updateByExample(@Param("record") RiskBqsDataFeedback record, @Param("example") RiskBqsDataFeedbackExample example);

    int updateByPrimaryKeySelective(RiskBqsDataFeedback record);

    int updateByPrimaryKey(RiskBqsDataFeedback record);
}
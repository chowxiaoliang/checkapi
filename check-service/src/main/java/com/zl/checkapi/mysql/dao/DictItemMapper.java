package com.zl.checkapi.mysql.dao;

import com.zl.checkapi.mysql.domain.DictItem;
import com.zl.checkapi.mysql.domain.DictItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DictItemMapper {
    int countByExample(DictItemExample example);

    int deleteByExample(DictItemExample example);

    int deleteByPrimaryKey(String itemId);

    int insert(DictItem record);

    int insertSelective(DictItem record);

    List<DictItem> selectByExample(DictItemExample example);

    DictItem selectByPrimaryKey(String itemId);

    int updateByExampleSelective(@Param("record") DictItem record, @Param("example") DictItemExample example);

    int updateByExample(@Param("record") DictItem record, @Param("example") DictItemExample example);

    int updateByPrimaryKeySelective(DictItem record);

    int updateByPrimaryKey(DictItem record);
}
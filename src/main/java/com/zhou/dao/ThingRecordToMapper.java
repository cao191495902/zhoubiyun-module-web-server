package com.zhou.dao;

import com.zhou.bean.ThingRecordTo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThingRecordToMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ThingRecordTo record);

    int insertSelective(ThingRecordTo record);

    ThingRecordTo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ThingRecordTo record);

    int updateByPrimaryKey(ThingRecordTo record);

    List<ThingRecordTo> getRecordListById(@Param("id") Integer id);
}
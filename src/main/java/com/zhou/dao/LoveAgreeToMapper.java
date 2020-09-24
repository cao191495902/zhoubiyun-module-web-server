package com.zhou.dao;

import com.zhou.bean.LoveAgreeTo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoveAgreeToMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoveAgreeTo record);

    int insertSelective(LoveAgreeTo record);

    LoveAgreeTo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoveAgreeTo record);

    int updateByPrimaryKey(LoveAgreeTo record);

    List<LoveAgreeTo> getAgreeListById(@Param("id") Integer id);
}
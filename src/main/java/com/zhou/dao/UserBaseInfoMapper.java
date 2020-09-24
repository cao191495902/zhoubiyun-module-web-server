package com.zhou.dao;

import com.zhou.bean.UserBaseInfo;
import org.apache.ibatis.annotations.Param;

public interface UserBaseInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserBaseInfo record);

    int insertSelective(UserBaseInfo record);

    UserBaseInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserBaseInfo record);

    int updateByPrimaryKey(UserBaseInfo record);

    UserBaseInfo selectByUserName(@Param("userName") String userName);
}
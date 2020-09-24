package com.zhou.service;

import com.zhou.bean.LoveAgreeTo;
import com.zhou.bean.ThingRecordTo;
import com.zhou.bean.UserBaseInfo;
import com.zhou.dao.LoveAgreeToMapper;
import com.zhou.dao.ThingRecordToMapper;
import com.zhou.dao.UserBaseInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserBaseInfoMapper userBaseInfoMapper;
    @Autowired
    LoveAgreeToMapper loveAgreeToMapper;
    @Autowired
    ThingRecordToMapper thingRecordToMapper;
    @Override
    public UserBaseInfo getUserInfoByUserName(String userName) {
        return userBaseInfoMapper.selectByUserName(userName);
    }

    @Override
    public UserBaseInfo getUserInfoById(Integer id) {
        return userBaseInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<LoveAgreeTo> getAgreeListById(Integer id) {
        return loveAgreeToMapper.getAgreeListById(id);
    }

    @Override
    public List<ThingRecordTo> getRecordListById(Integer id) {
        return thingRecordToMapper.getRecordListById(id);
    }
}

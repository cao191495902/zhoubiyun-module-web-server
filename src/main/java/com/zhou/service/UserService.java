package com.zhou.service;

import com.zhou.bean.LoveAgreeTo;
import com.zhou.bean.ThingRecordTo;
import com.zhou.bean.UserBaseInfo;

import java.util.List;

public interface UserService {

    UserBaseInfo getUserInfoByUserName(String userName);

    UserBaseInfo getUserInfoById(Integer id);

    List<LoveAgreeTo> getAgreeListById(Integer id);

    List<ThingRecordTo> getRecordListById(Integer id);
}

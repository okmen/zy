package com.egou.dao;

import com.egou.bean.UUsers;


public interface UUsersMapper {
	
    int deleteByPrimaryKey(Long userid);

    int insert(UUsers record);

    int insertSelective(UUsers record);

    UUsers getUUsersByUserID(Long userid);
    
    UUsers getUUsersByUserName(String username);

    int updateByPrimaryKeySelective(UUsers record);

    int updateByPrimaryKey(UUsers record);
}
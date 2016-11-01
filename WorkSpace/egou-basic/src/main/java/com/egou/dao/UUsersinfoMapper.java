package com.egou.dao;

import com.egou.bean.UUsersinfo;


public interface UUsersinfoMapper {
	
    int deleteByPrimaryKey(Long userid);

    int insert(UUsersinfo record);

    int insertSelective(UUsersinfo record);

    UUsersinfo selectByPrimaryKey(Long userid);

    int updateByPrimaryKeySelective(UUsersinfo record);

    int updateByPrimaryKey(UUsersinfo record);
}
package com.egou.dao;

import com.egou.bean.PProductOcc;

public interface PProductOccMapper {
    int deleteByPrimaryKey(Long productid);

    int insert(PProductOcc record);

    int insertSelective(PProductOcc record);

    PProductOcc selectByPrimaryKey(Long productid);

    int updateByPrimaryKeySelective(PProductOcc record);

    int updateByPrimaryKey(PProductOcc record);
}
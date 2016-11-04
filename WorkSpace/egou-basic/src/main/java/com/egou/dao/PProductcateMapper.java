package com.egou.dao;

import com.egou.bean.PProductcate;

public interface PProductcateMapper {
    int deleteByPrimaryKey(Integer cateid);

    int insert(PProductcate record);

    int insertSelective(PProductcate record);

    PProductcate selectByPrimaryKey(Integer cateid);

    int updateByPrimaryKeySelective(PProductcate record);

    int updateByPrimaryKey(PProductcate record);
}
package com.egou.dao;

import java.util.List;

import com.egou.bean.PSearchkeyword;

public interface PSearchkeywordMapper {
    int deleteByPrimaryKey(Long wordid);

    int insert(PSearchkeyword record);

    int insertSelective(PSearchkeyword record);

    PSearchkeyword selectByPrimaryKey(Long wordid);
    /**
     * 根据搜索关键词获取对象
     * @param key
     * @return
     */
    PSearchkeyword selectByKeyName(String key);
    /**
     * 获取排在前面的关键词
     * @param key
     * @return
     */
    List<PSearchkeyword> find_PSearchkeywords(String key);

    int updateByPrimaryKeySelective(PSearchkeyword record);

    int updateByPrimaryKey(PSearchkeyword record);
}
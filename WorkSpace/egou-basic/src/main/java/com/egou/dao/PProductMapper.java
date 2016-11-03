package com.egou.dao;

import java.util.List;

import com.egou.bean.PProduct;
import com.egou.vo.product.SearchParam;
import com.github.pagehelper.PageHelper;

public interface PProductMapper {
	
    int deleteByPrimaryKey(Long productid);

    int insert(PProduct record);

    int insertSelective(PProduct record);

    PProduct selectByPrimaryKey(Long productid);

    int updateByPrimaryKeySelective(PProduct record);

    int updateByPrimaryKey(PProduct record);
    /**
     * 获取所有产品
     * @return
     */
    List<PProduct> findAll();
    /**
     * 产品搜索
     * @param param
     * @return
     */
    List<PProduct> find_PProducts(SearchParam param);
}
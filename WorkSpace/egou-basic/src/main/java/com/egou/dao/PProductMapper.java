package com.egou.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egou.bean.PProduct;
import com.egou.vo.product.SearchParam;

public interface PProductMapper {
	
    int deleteByPrimaryKey(Long productid);

    int insert(PProduct record);

    int insertSelective(PProduct record);

    PProduct selectByPrimaryKey(Long productid);

    int updateByPrimaryKeySelective(PProduct record);

    int updateByPrimaryKey(PProduct record);
   
    /**
     * 产品搜索
     * @param param
     * @return
     */
    List<PProduct> find_PProducts(SearchParam param);
    /**
     * 根据产品id获取产品列表
     * @param pids
     * @return
     */
    List<PProduct> find_PProductsByPids(@Param("pids")List<Long> pids);
}
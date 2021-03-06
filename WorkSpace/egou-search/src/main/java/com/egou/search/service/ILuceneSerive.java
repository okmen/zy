package com.egou.search.service;

import java.io.IOException;
import java.util.List;

import com.egou.bean.PProduct;
import com.egou.search.vo.ProductIndex;
import com.egou.search.vo.SearchProductParam;
import com.github.pagehelper.PageInfo;

public interface ILuceneSerive {

	
	
	/**
	 * lucene 创建索引 
	 */
	void createIndex(List<PProduct> proList);
	/**
	 * 更新索引
	 * @param proList
	 */
	void updateIndex(List<PProduct> proList);

	/**
	 * 产品搜索
	 * @param param
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws IOException
	 */
	PageInfo<ProductIndex> searchProducts(SearchProductParam param ,int pageIndex,int pageSize) throws IOException;
	/**
	 * 初始化产品表 数据（test）
	 * @param index
	 * @param size
	 */
//	void insertInit(int index,int size);
}

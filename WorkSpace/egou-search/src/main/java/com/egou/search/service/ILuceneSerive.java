package com.egou.search.service;

import java.io.IOException;
import java.util.List;

import com.egou.bean.PProduct;
import com.egou.search.vo.ProductIndex;
import com.egou.vo.product.SearchParam;
import com.github.pagehelper.PageInfo;

public interface ILuceneSerive {

	
	
	PageInfo<PProduct> find_PProductslist(SearchParam param, int pageIndex,int size);
	/**
	 * 创建索引
	 */
	void createIndex(List<PProduct> proList);
	
	/**
	 * Lucene 查询商品列表
	 * @param title
	 * @param index
	 * @param size
	 * @return
	 * @throws IOException
	 */
	List<ProductIndex> find_Products(String title,int index,int size) throws IOException;
	
	public void insertInit(int index,int size);
}

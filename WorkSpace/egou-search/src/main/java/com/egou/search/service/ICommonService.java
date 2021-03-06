package com.egou.search.service;

import java.util.List;

import com.egou.bean.PProduct;
import com.egou.bean.PProductcate;
import com.egou.bean.PSearchkeyword;
import com.egou.vo.product.SearchParam;
import com.github.pagehelper.PageInfo;

public interface ICommonService {

	/**
	 * 新增
	 * @param cate
	 */
	void addProductCate(PProductcate cate);
	
	/**
	 * 收录 搜索关键词
	 * @param key
	 * @throws Exception
	 */
	void addKeyWord(String key)throws Exception;
	/**
	 * 获取热门搜索
	 * @param key
	 * @return
	 */
	List<PSearchkeyword> find_keys(String key);
	
	PageInfo<PProduct> find_PProductslist(SearchParam param, int pageIndex,int size);
}

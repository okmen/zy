package com.egou.service;

import com.egou.bean.PProduct;
import com.egou.vo.ReturnModel;
import com.egou.vo.product.SearchParam;
import com.github.pagehelper.PageInfo;



public interface IProductManageService {

	/**
	 * 产品搜索
	 * @param param
	 * @param pageIndex
	 * @param size
	 * @return
	 */
	PageInfo<PProduct> find_PProductslist(SearchParam param, int pageIndex,int size);
	
}

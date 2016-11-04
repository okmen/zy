package com.egou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egou.bean.PProduct;
import com.egou.dao.PProductMapper;
import com.egou.service.IProductManageService;
import com.egou.vo.product.SearchParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("productManageService")
@Transactional(rollbackFor={RuntimeException.class, Exception.class})
public class ProductManageService implements IProductManageService {
	
	@Autowired
	private PProductMapper productDao;
	
	public PageInfo<PProduct> find_PProductslist(SearchParam param, int pageIndex,int size) {
		PageHelper.startPage(pageIndex,size);
		List<PProduct> relist=productDao.find_PProducts(param);
		PageInfo<PProduct> pageInfo=new PageInfo<PProduct>(relist);
		return pageInfo;
	}
	
	/**
	 * 产品productid的生成规则 ，编号*100
	 * 款式styltId 跟着productId尾数
	 * 如：产品a 的productId：196600 (这里产品表 可以默认 productId:196601)
	 * 产品a的款式红、白、蓝的styleId 分别为(196601,196602,196603)
	 *
	 *==============================
	 *新增产品
	 */
	public void addPProduct(){
		
	}
}

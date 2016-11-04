package com.egou.search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egou.bean.PProductcate;
import com.egou.dao.PProductcateMapper;
import com.egou.search.service.ICommonService;

@Service("commonService")
@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
public class CommonService implements ICommonService{

	@Autowired
	private PProductcateMapper cateMapper;
	
	public void addProductCate(PProductcate cate){
		cateMapper.insert(cate);
	}
}

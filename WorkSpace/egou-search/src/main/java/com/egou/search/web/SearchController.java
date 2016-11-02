package com.egou.search.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.egou.search.service.ILuceneSerive;
import com.egou.search.vo.ProductIndex;
import com.egou.utils.JsonUtils;

@Controller
@RequestMapping(value = "/search")
public class SearchController {
	
	@Resource(name="luceneService")
	private ILuceneSerive productDao;
	
	@ResponseBody
	@RequestMapping(value = "/createIndex",method ={RequestMethod.POST,RequestMethod.GET})
	public String createIndex() throws Exception {
		productDao.createIndex();
		return JsonUtils.objectToJson("dssds");
	}
	
	@ResponseBody
	@RequestMapping(value = "/search",method ={RequestMethod.POST,RequestMethod.GET})
	public String search(String title) throws Exception {
		List<ProductIndex> list=  productDao.find_Products(title, 1, 10);
		return JsonUtils.objectToJson(list);
	}
}

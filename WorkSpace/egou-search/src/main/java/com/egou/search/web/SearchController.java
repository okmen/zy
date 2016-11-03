package com.egou.search.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egou.bean.PProduct;
import com.egou.search.service.ILuceneSerive;
import com.egou.search.vo.ProductIndex;
import com.egou.utils.JsonUtils;
import com.egou.vo.product.SearchParam;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value = "/search")
public class SearchController {

	@Resource(name = "luceneService")
	private ILuceneSerive productDao;

	@ResponseBody
	@RequestMapping(value = "/createIndex", method = { RequestMethod.POST, RequestMethod.GET })
	public String createIndex() throws Exception {
		productDao.createIndex();
		return JsonUtils.objectToJson("dssds");
	}

	@ResponseBody
	@RequestMapping(value = "/search", method = { RequestMethod.POST, RequestMethod.GET })
	public String search(String title) throws Exception {
		List<ProductIndex> list = productDao.find_Products(title, 1, 10);
		return JsonUtils.objectToJson(list);
	}
	
	@ResponseBody
	@RequestMapping(value = "/test", method = { RequestMethod.POST, RequestMethod.GET })
	public String test(String title,@RequestParam(required = false, defaultValue = "1") int index, @RequestParam(required = false, defaultValue = "10") int size) throws Exception {
		SearchParam param=new SearchParam();
		param.setTitle(title);
		PageInfo<PProduct> pageInfo =productDao.find_PProductslist(param, index,size);
		return JsonUtils.objectToJson(pageInfo);
	}

	@ResponseBody
	@RequestMapping(value = "/proInit", method = { RequestMethod.POST, RequestMethod.GET })
	public String proInit(@RequestParam(required = false, defaultValue = "1") int index, @RequestParam(required = false, defaultValue = "10") int size) throws Exception {
		int tem=338;
		for(int i=1;i<tem;i++){
			productDao.insertInit(1, 1000);
		}
		return JsonUtils.objectToJson("222");
	}
}

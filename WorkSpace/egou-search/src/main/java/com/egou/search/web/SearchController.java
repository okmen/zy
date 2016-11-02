package com.egou.search.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egou.search.service.ILuceneSerive;
import com.egou.search.vo.ProductIndex;
import com.egou.utils.JsonUtils;

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
	@RequestMapping(value = "/proInit", method = { RequestMethod.POST, RequestMethod.GET })
	public String proInit(@RequestParam(required = false, defaultValue = "1") int index, @RequestParam(required = false, defaultValue = "10") int size) throws Exception {
		int tem=338;
		for(int i=1;i<tem;i++){
			productDao.insertInit(1, 1000);
		}
		return JsonUtils.objectToJson("222");
	}
}

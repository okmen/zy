package com.egou.search.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egou.search.service.ILuceneSerive;
import com.egou.search.vo.ProductIndex;
import com.egou.search.vo.SearchProductParam;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value = "/")
public class SearchPageController {
	@Resource(name = "luceneService")
	private ILuceneSerive productDao;
	
	@RequestMapping(value = "/search")
	public String login(Model model,String key,@RequestParam(required = false, defaultValue = "1") int index, @RequestParam(required = false, defaultValue = "10") int size) throws Exception {
//		List<ProductIndex> list = productDao.find_Products(title, index, size);
		SearchProductParam param=new SearchProductParam();
		param.setTitle(key); 
		PageInfo<ProductIndex> pageInfo= productDao.searchProducts(param,  index, size);
		model.addAttribute("page", pageInfo);
		model.addAttribute("title", key);
		return "search";
	}
}

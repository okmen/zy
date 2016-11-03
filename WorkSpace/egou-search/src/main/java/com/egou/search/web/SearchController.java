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
		SearchParam param=new SearchParam();
		int index=1;
		int size=100;
		PageInfo<PProduct> pageInfo =productDao.find_PProductslist(param,index,size);
		if(pageInfo.getList()!=null&&pageInfo.getList().size()>0){
			for(;index<=pageInfo.getPages();index++){
				PageInfo<PProduct> list =productDao.find_PProductslist(null,index,size);
				if(list.getList()!=null&&list.getList().size()>0){
					productDao.createIndex(list.getList());
				}
			}
		}
		return JsonUtils.objectToJson(index*size+"条数据处理完");
	}

	/**
	 * lucene搜索 查询
	 * @param title
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/search", method = { RequestMethod.POST, RequestMethod.GET })
	public String search(String title,@RequestParam(required = false, defaultValue = "1") int index, @RequestParam(required = false, defaultValue = "10") int size) throws Exception {
		List<ProductIndex> list = productDao.find_Products(title, index, size);
		return JsonUtils.objectToJson(list);
	}
	
	
	/**
	 * 测试
	 * @param title
	 * @param index
	 * @param size
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/test", method = { RequestMethod.POST, RequestMethod.GET })
	public String test(String title,@RequestParam(required = false, defaultValue = "1") int index, @RequestParam(required = false, defaultValue = "10") int size) throws Exception {
		SearchParam param=new SearchParam();
		param.setTitle(title);
		PageInfo<PProduct> pageInfo =productDao.find_PProductslist(param, index,size);
		return JsonUtils.objectToJson(pageInfo);
	}

	/**
	 * 拿取 okwei产品数据
	 * @param index
	 * @param size
	 * @return
	 * @throws Exception
	 */
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

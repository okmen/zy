package com.egou.search.web;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egou.bean.PProduct;
import com.egou.bean.PProductcate;
import com.egou.bean.PSearchkeyword;
import com.egou.dao.PSearchkeywordMapper;
import com.egou.search.service.ICommonService;
import com.egou.search.service.ILuceneSerive;
import com.egou.search.service.ths.ThreadCreateIndexLucene;
import com.egou.search.vo.ProductIndex;
import com.egou.search.vo.SearchProductParam;
import com.egou.utils.JsonUtils;
import com.egou.utils.ParseHelper;
import com.egou.vo.product.SearchParam;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value = "/search")
public class SearchController{

	@Resource(name = "luceneService")
	private ILuceneSerive lucene;

	@Resource(name = "commonService")
	private ICommonService commonService;

	@Autowired
	private PSearchkeywordMapper searchDao;

	

	/**
	 * 初始化索引文件
	 * @param type
	 * @param index
	 * @param size
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/createIndex", method = { RequestMethod.POST, RequestMethod.GET })
	public String createIndex() throws Exception {
		SearchParam param = new SearchParam();
		PageInfo<PProduct> pageInfo = commonService.find_PProductslist(param, 1, 100);
		long time1 = new Date().getTime();
//		if (type == 0) {
//			if (pageInfo.getList() != null && pageInfo.getList().size() > 0) {
//				for (; index <= 4; index++) {// pageInfo.getPages()
//					PageInfo<PProduct> list = cateService.find_PProductslist(null, index, size);
//					if (list.getList() != null && list.getList().size() > 0) {
//						lucene.createIndex(list.getList());
//					}
//				}
//			}
//		} else {
			new ThreadCreateIndexLucene(commonService,lucene).initCreateIndex();
//		}
		long time2 = new Date().getTime();
		return JsonUtils.objectToJson(pageInfo.getTotal() + "条数据处理完,花时：" + (time2 - time1));
	}
	

	

	/**
	 * lucene搜索 查询
	 * 
	 * @param title
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/search", method = { RequestMethod.POST, RequestMethod.GET })
	public String search(String title, @RequestParam(required = false, defaultValue = "1") int index, @RequestParam(required = false, defaultValue = "10") int size) throws Exception {
		SearchProductParam params = new SearchProductParam();
		params.setTitle(title);
		PageInfo<ProductIndex> pageInfo = lucene.searchProducts(params, index, size);
		return JsonUtils.objectToJson(pageInfo);
	}
	
	
	

	/**
	 * 测试
	 * 
	 * @param title
	 * @param index
	 * @param size
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/test", method = { RequestMethod.POST, RequestMethod.GET })
	public String test(String title, @RequestParam(required = false, defaultValue = "1") int index, @RequestParam(required = false, defaultValue = "10") int size, String catename, String parent, String step) throws Exception {
		// SearchParam param=new SearchParam();
		// param.setTitle(title);
		// PageInfo<PProduct> pageInfo =productDao.find_PProductslist(param,
		// index,size);
		PProductcate cate = new PProductcate();
		cate.setCatename(catename);
		cate.setStep(ParseHelper.toInt(step));
		cate.setParentid(ParseHelper.toInt(parent));
		commonService.addProductCate(cate);
		return JsonUtils.objectToJson("cc");
	}
	
	

//	/**
//	 * 拿取 okwei产品数据
//	 * 
//	 * @param index
//	 * @param size
//	 * @return
//	 * @throws Exception
//	 */
//	@ResponseBody
//	@RequestMapping(value = "/proInit", method = { RequestMethod.POST, RequestMethod.GET })
//	public String proInit(@RequestParam(required = false, defaultValue = "1") int index, @RequestParam(required = false, defaultValue = "10") int size) throws Exception {
//		int tem = 338;
//		for (int i = 1; i < tem; i++) {
//			lucene.insertInit(1, 1000);
//		}
//		return JsonUtils.objectToJson("222");
//	}

}

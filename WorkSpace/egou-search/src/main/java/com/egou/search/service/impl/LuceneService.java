package com.egou.search.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egou.bean.PProduct;
import com.egou.dao.PProductMapper;
import com.egou.search.lucene.CreateLuceneIndex;
import com.egou.search.lucene.LuceneSearch;
import com.egou.search.service.ILuceneSerive;
import com.egou.search.vo.ProductIndex;
import com.egou.service.IProductManageService;
import com.egou.utils.HttpRequestHelper;
import com.egou.utils.JsonUtils;
import com.egou.utils.ParseHelper;
import com.egou.vo.product.SearchParam;
import com.github.pagehelper.PageInfo;

@Service("luceneService")
@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
public class LuceneService implements ILuceneSerive {

	@Autowired
	private PProductMapper productDao;

	@Resource(name="productManageService")
	private IProductManageService productService;
	/**
	 * 创建索引类
	 */
	private CreateLuceneIndex create;

	
	
	public PageInfo<PProduct> find_PProductslist(SearchParam param, int pageIndex,int size) {
		return productService.find_PProductslist(param, pageIndex, size);
	}
	

	
	public void createIndex(List<PProduct> proList) {
		if (proList != null && proList.size() > 0) {
			List<ProductIndex> productIndexs = new ArrayList<ProductIndex>();
			for (PProduct pp : proList) {
				ProductIndex mo = new ProductIndex();
				mo.setProductid(pp.getProductid());
				mo.setTitle(pp.getTitle());
				mo.setCreatetime(pp.getCreatetime());
				productIndexs.add(mo);
			}
			try {
				if (productIndexs.size() > 0) {
					if (create == null) {
						create = new CreateLuceneIndex();
					}
					create.createIndexs(productIndexs);
				}

			} catch (IOException e) {
				System.out.println("插入Lucene索引时出错：" + e.getMessage());
			}
		}
	}

	public List<ProductIndex> find_Products(String title, int index, int size) throws IOException {
		List<ProductIndex> list = new LuceneSearch().search(title, index, size);
		System.out.println(JsonUtils.objectToJson(list));
		return list;
	}
	
	public void insertInit(int index,int size){
		HttpRequestHelper aaHelper=new HttpRequestHelper();
		String resultStr= aaHelper.sendGet("http://10.10.2.10:8080/test/findproducts", "pageIndex="+index+"&pageSize="+size);
		JSONObject model = JSONObject.fromObject(resultStr);
		String splistString = String.valueOf(model.get("BaseModle"));
		if (splistString != null && !"".equals(splistString)&&!"[]".equals(splistString)) {
			JSONArray prolistArray = new JSONArray().fromObject(splistString);
			if (prolistArray != null && prolistArray.size() > 0) {
				for (int i = 0; i < prolistArray.size(); i++) {
					JSONObject pro = prolistArray.getJSONObject(i);//
					 PProduct mo=new PProduct();
					 mo.setProductid(ParseHelper.toLong(String.valueOf(pro.get("productId"))));
					 mo.setTitle(String.valueOf(pro.get("productTitle")));
					 mo.setDefaultprice(ParseHelper.toDouble(String.valueOf(pro.get("defaultPrice"))));
					 mo.setSellerid(ParseHelper.toLong(String.valueOf(pro.get("supplierWeiId"))));
					 mo.setDefaultimg(String.valueOf(pro.get("defaultImg")));
					 mo.setCreatetime(new Date()); 
					 mo.setStatus(1);
					 mo.setBrandid(1l);
					 mo.setClassid(ParseHelper.toInt(String.valueOf(pro.get("classId"))));
					 productDao.insertSelective(mo);
				}
			}
		}
	}
}

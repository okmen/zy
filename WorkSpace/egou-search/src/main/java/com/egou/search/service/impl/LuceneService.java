package com.egou.search.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egou.bean.PProduct;
import com.egou.dao.PProductMapper;
import com.egou.search.lucene.CreateLuceneIndex;
import com.egou.search.lucene.LuceneSearch;
import com.egou.search.service.ILuceneSerive;
import com.egou.search.vo.ProductIndex;
import com.egou.utils.JsonUtils;

@Service("luceneService")
@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
public class LuceneService implements ILuceneSerive {

	@Autowired
	private PProductMapper productDao;

	/**
	 * 创建索引类
	 */
	private CreateLuceneIndex create;

	public List<PProduct> findAll() {
		return productDao.findAll();
	}

	public void createIndex() {
		List<PProduct> proList = productDao.findAll();
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
}

package com.egou.search.service.ths;

import com.egou.bean.PProduct;
import com.egou.search.service.ICommonService;
import com.egou.search.service.ILuceneSerive;
import com.egou.vo.product.SearchParam;
import com.github.pagehelper.PageInfo;

public class ThreadCreateIndexLucene implements Runnable {
	/**
	 * 创建索引类（）
	 */
	ILuceneSerive lucene;
	/**
	 * 
	 */
	ICommonService searchService;


	public ThreadCreateIndexLucene(ICommonService co, ILuceneSerive pro) {
		searchService = co;
		lucene = pro;
	}

	int index = 1;
	int size = 1000;// 每页处理多少条

	public void run() {
		synchronized (this) {
			PageInfo<PProduct> list = searchService.find_PProductslist(null, index, size);
			if (list.getList() != null && list.getList().size() > 0) {
				System.out.println(Thread.currentThread().getName() + " loop " + index);
//				lucene.createIndex(list.getList()); //创建（新建索引）
				lucene.updateIndex(list.getList());//更新
			}
		}
	}

	public void initCreateIndex() {
		ThreadCreateIndexLucene th1 = new ThreadCreateIndexLucene(searchService, lucene);
		SearchParam param = new SearchParam();
		PageInfo<PProduct> pageInfo = searchService.find_PProductslist(param, index, size);
		if (pageInfo.getList() != null && pageInfo.getList().size() > 0) {
			for (; index <= pageInfo.getPages() ; index++) {//pageInfo.getPages()
				new Thread(th1, "a" + index).start();
			}
		}
	}
}

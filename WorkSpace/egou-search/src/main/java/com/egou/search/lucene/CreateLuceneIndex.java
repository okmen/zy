package com.egou.search.lucene;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import com.egou.search.vo.ProductIndex;

public class CreateLuceneIndex extends LuceneCommon{

	public CreateLuceneIndex() {
		super.init();
	}
	
	/**
	 * 建立索引 要实现search nrt,需要使用TrackIndexWriter保存document，同时Writer也不需要关闭。
	 * 
	 * **/
	public void createIndexs(List<ProductIndex> ids) throws IOException {
		try {
			if (ids == null || ids.size() <= 0)
				return;
			for (int i = 0; i < ids.size(); i++) {
				Document doc = new Document();
				ProductIndex ldata = ids.get(i);
				try {
					doc.add(new StringField("proid", ldata.getProductid().toString(), Store.YES));
					doc.add(new TextField("title", ldata.getTitle(), Store.YES));
					//一级分类
					doc.add(new TextField("cate1",String.valueOf(ldata.getCateIdOne()) , Store.YES));
					//一级分类
					doc.add(new TextField("cate2", String.valueOf(ldata.getCateIdTwo()), Store.YES));
					doc.add(new TextField("cate3", String.valueOf(ldata.getCateIdThree()), Store.YES));
					//品牌id
					doc.add(new TextField("brandid", String.valueOf(ldata.getBrandid()), Store.YES));
					if (trackingIndexWriter == null) {
						// logger.warn("Lucene创建索引时异常，trackingIndexWriter为空");
						System.out.println("Lucene创建索引时异常，trackingIndexWriter为空");
						super.init();
					}
					trackingIndexWriter.addDocument(doc);
				} catch (Exception ex) {
					// logger.error("Lucene创建索引时异常:" + ex.getMessage());
					System.out.println("Lucene创建索引时异常:" + ex.getMessage());
					continue;
				}
			}
		} finally {
			super.commit();// 首次创建，提交索引,只有提交后，才会在索引片段中也将信息改变
		}
	}
}

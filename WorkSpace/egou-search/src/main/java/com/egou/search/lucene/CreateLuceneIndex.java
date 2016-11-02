package com.egou.search.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.TrackingIndexWriter;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.ControlledRealTimeReopenThread;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ReferenceManager;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.egou.bean.PProduct;
import com.egou.search.vo.ProductIndex;
import com.egou.search.vo.SearchResultBean;
import com.egou.utils.AppSettingUtil;

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
					doc.add(new StringField("id", ldata.getProductid().toString(), Store.YES));
					doc.add(new TextField("title", ldata.getTitle(), Store.YES));
					
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

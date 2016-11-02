package com.egou.search.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.TrackingIndexWriter;
import org.apache.lucene.search.ControlledRealTimeReopenThread;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ReferenceManager;
import org.apache.lucene.search.SearcherFactory;
import org.apache.lucene.search.SearcherManager;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;

import com.egou.utils.ObjectUtils;

public class LuceneCommon {
	/**
	 * lucene版本
	 */
	protected static Version Lucene_Version = Version.LUCENE_4_10_4;
	/**
	 * 索引文件地址
	 */
	protected Directory directory = null;
	/**
	 * 写入索引
	 */
	protected IndexWriter writer = null;
	/**
	 * 读取索引文件
	 */
	protected IndexReader reader;
	/**
	 * 读入停用词文件 路径
	 */
	protected String stopWordTablePath;
	/**
	 * 读入停用词文件名称
	 */
	protected String stopWordTablename = "ChineseStopWord.txt";

	/** nrt init **/
	protected TrackingIndexWriter trackingIndexWriter = null;
	protected ReferenceManager<IndexSearcher> reMgr = null;//
	protected ControlledRealTimeReopenThread<IndexSearcher> crt = null;
	// private Log logger = LogFactory.getLog(this.getClass());
	protected Set<String> stopWordSet = null;

	/**
	 * 定期提交内存中得索引到硬盘上，防止丢失
	 */
	public void commit() {
		try {
			writer.commit();
			crt.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initStopWord() {
		try {
			if (ObjectUtils.isEmpty(stopWordTablePath)) {
				stopWordTablePath = getClass().getClassLoader().getResource(stopWordTablename).getPath();
			}
			// 读入停用词文件
			BufferedReader StopWordFileBr = new BufferedReader(new InputStreamReader(new FileInputStream(new File(stopWordTablePath))));
			// 用来存放停用词的集合
			stopWordSet = new HashSet<String>();
			// 初如化停用词集
			String stopWord = null;
			for (; (stopWord = StopWordFileBr.readLine()) != null;) {
				stopWordSet.add(stopWord);
			}
			// 关闭流
			StopWordFileBr.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	/**
	 * 创建索引 初始化
	 */
	public void init() {
		if (crt == null) {
			try {
				initStopWord();
				directory = new FileIndexUtils().getDirectory();
				if (IndexWriter.isLocked(directory)) {
					IndexWriter.unlock(directory);
				}
				// Analyzer analyzer=new StandardAnalyzer(Version.LUCENE_46);
				writer = new IndexWriter(directory, new IndexWriterConfig(Lucene_Version, AnalyzerUtil.getIkAnalyzer()));
				trackingIndexWriter = new TrackingIndexWriter(writer);
				reMgr = new SearcherManager(writer, true, new SearcherFactory());
				/** 在0.025s~5.0s之间重启一次线程，这个是时间的最佳实践 **/
				crt = new ControlledRealTimeReopenThread<>(trackingIndexWriter, reMgr, 5.0, 0.025);
				crt.setDaemon(true);// 设置为后台服务
				crt.setName("Index update to disk");// 线程名称
				crt.start();// 线程启动

			} catch (Exception e) {
				System.out.println("Lucene初始化失败：" + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}

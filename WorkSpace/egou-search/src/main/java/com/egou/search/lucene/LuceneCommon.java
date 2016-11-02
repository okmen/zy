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
	 * lucene�汾
	 */
	protected static Version Lucene_Version = Version.LUCENE_4_10_4;
	/**
	 * �����ļ���ַ
	 */
	protected Directory directory = null;
	/**
	 * д������
	 */
	protected IndexWriter writer = null;
	/**
	 * ��ȡ�����ļ�
	 */
	protected IndexReader reader;
	/**
	 * ����ͣ�ô��ļ� ·��
	 */
	protected String stopWordTablePath;
	/**
	 * ����ͣ�ô��ļ�����
	 */
	protected String stopWordTablename = "ChineseStopWord.txt";

	/** nrt init **/
	protected TrackingIndexWriter trackingIndexWriter = null;
	protected ReferenceManager<IndexSearcher> reMgr = null;//
	protected ControlledRealTimeReopenThread<IndexSearcher> crt = null;
	// private Log logger = LogFactory.getLog(this.getClass());
	protected Set<String> stopWordSet = null;

	/**
	 * �����ύ�ڴ��е�������Ӳ���ϣ���ֹ��ʧ
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
			// ����ͣ�ô��ļ�
			BufferedReader StopWordFileBr = new BufferedReader(new InputStreamReader(new FileInputStream(new File(stopWordTablePath))));
			// �������ͣ�ôʵļ���
			stopWordSet = new HashSet<String>();
			// ���绯ͣ�ôʼ�
			String stopWord = null;
			for (; (stopWord = StopWordFileBr.readLine()) != null;) {
				stopWordSet.add(stopWord);
			}
			// �ر���
			StopWordFileBr.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	/**
	 * �������� ��ʼ��
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
				/** ��0.025s~5.0s֮������һ���̣߳������ʱ������ʵ�� **/
				crt = new ControlledRealTimeReopenThread<>(trackingIndexWriter, reMgr, 5.0, 0.025);
				crt.setDaemon(true);// ����Ϊ��̨����
				crt.setName("Index update to disk");// �߳�����
				crt.start();// �߳�����

			} catch (Exception e) {
				System.out.println("Lucene��ʼ��ʧ�ܣ�" + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}

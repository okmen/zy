package com.egou.search.lucene;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class AnalyzerUtil {
	private static Analyzer analyzer;

	public static Analyzer getIkAnalyzer() {
		
		if (analyzer == null) {
			// 当为true时，分词器迚行最大词长切分 ；当为false时，分词器迚行最细粒度切
			analyzer = new IKAnalyzer(true);//new StandardAnalyzer(Version.LUCENE_4_10_4) ;//
		}
		return analyzer;
	}
}

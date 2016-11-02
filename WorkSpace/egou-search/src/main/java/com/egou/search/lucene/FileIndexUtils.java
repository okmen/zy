package com.egou.search.lucene;
import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.egou.utils.AppSettingUtil;

public class FileIndexUtils {
	private Log logger = LogFactory.getLog(this.getClass());
	public  Directory getDirectory()
	{
		File indexDir = new File(AppSettingUtil.getSingleValue("luceneIndex"));
		Directory fsDirectory=null;
		try {
			fsDirectory = FSDirectory.open(indexDir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("打开lucene文件夹时出错?"+e.getMessage());
			e.printStackTrace();
		}
		return fsDirectory;
	}
}

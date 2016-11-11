package com.egou.search.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.egou.search.service.ICommonService;
import com.egou.search.service.ILuceneSerive;
import com.egou.search.service.ths.ThreadCreateIndexLucene;

/**
 * 插入索引
 * @author Administrator
 *
 */
public class CreateLuceneIndexJob extends QuartzJobBean {
	private ILuceneSerive luncene;
	private ICommonService cateService;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		luncene = (ILuceneSerive) context.getJobDetail().getJobDataMap().get("luceneService");
		cateService = (ICommonService) context.getJobDetail().getJobDataMap().get("commonService");
		this.work();
	}
	
	private void work(){
		new ThreadCreateIndexLucene(cateService,luncene).initCreateIndex();
		System.out.println("job时间："+new Date()); 
	}
}

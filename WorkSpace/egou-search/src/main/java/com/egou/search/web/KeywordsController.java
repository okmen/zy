package com.egou.search.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egou.bean.PSearchkeyword;
import com.egou.search.service.ICommonService;
import com.egou.utils.JsonUtils;

@Controller
@RequestMapping(value = "/keyword")
public class KeywordsController {
	@Resource(name = "commonService")
	private ICommonService commonService;
	
	
	/**
	 * ���������ؼ��ʼ�¼
	 * @param key
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/keyword", method = { RequestMethod.POST, RequestMethod.GET })
	public String addKeyWord(String key) throws Exception {
		commonService.addKeyWord(key);
		return JsonUtils.objectToJson("");
	}

	/**
	 * ��ȡ��Ӧ�Ĺؼ����б���ش� �б�
	 * @param key
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getkeys", method = { RequestMethod.POST, RequestMethod.GET })
	public String getkeys(String key) throws Exception {
		List<PSearchkeyword> list = commonService.find_keys(key);
		return JsonUtils.objectToJson(list);
	}
}

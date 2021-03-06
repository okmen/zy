package com.egou.passport.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egou.service.IUserMgtService;
import com.egou.utils.JsonUtils;
import com.egou.vo.ReturnModel;


@Controller
@RequestMapping(value = "/login")
public class loginAjaxController {

	@Resource(name="userMgtService")
	private IUserMgtService userService;
	
	/**
	 * �û���½����
	 * @param model
	 * @param userno
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/loginAjax",method ={RequestMethod.POST,RequestMethod.GET})
	public String userlogin(Model model, String userno,String pwd) throws Exception {
		ReturnModel rqModel= userService.login(userno, pwd);
		return JsonUtils.objectToJson(rqModel);
	}
}

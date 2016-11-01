package com.egou.service;

import com.egou.vo.ReturnModel;
import com.egou.vo.user.RegisterParam;
import com.egou.vo.user.UserInfoModel;


public interface IUserMgtService {

	/**
	 * ��ȡ�û���ϸ��Ϣ
	 * @param userid
	 * @return
	 */
	public UserInfoModel getUserInfoModel(Long userid) throws Exception;
	/**
	 * �û���½
	 * @param userid/username/nickName
	 * @param pwd
	 * @return 
	 * @throws Exception
	 */
	public ReturnModel login(String userno,String pwd) throws Exception;
	/**
	 * �û�ע��
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ReturnModel register(RegisterParam param) throws Exception;
	
	void editUser(UserInfoModel model) throws Exception;
}

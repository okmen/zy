package com.egou.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egou.bean.UUsers;
import com.egou.bean.UUsersinfo;
import com.egou.dao.UUsersMapper;
import com.egou.dao.UUsersinfoMapper;
import com.egou.enums.ReturnStatus;
import com.egou.service.IUserMgtService;
import com.egou.utils.MD5Encrypt;
import com.egou.utils.ObjectUtils;
import com.egou.utils.ParseHelper;
import com.egou.utils.RedisUtil;
import com.egou.vo.ReturnModel;
import com.egou.vo.user.LoginSuccessInfo;
import com.egou.vo.user.RegisterParam;
import com.egou.vo.user.UserInfoModel;


@Service("userMgtService")
@Transactional(rollbackFor={RuntimeException.class, Exception.class})
public class UserMgtService implements IUserMgtService {
	@Autowired
	private UUsersMapper userDao;
	@Autowired
	private UUsersinfoMapper userInfoDao;

	@Override
	public UserInfoModel getUserInfoModel(Long userid) throws Exception {
		UserInfoModel result = new UserInfoModel();
		UUsers user = userDao.getUUsersByUserID(userid);
		if (user != null) {
			result.setUserid(user.getUserid());
			result.setUsername(user.getUsername());
			UUsersinfo userinfo = userInfoDao.selectByPrimaryKey(userid);
			if (userinfo != null) {
				result.setCardId(userinfo.getCardid());
			}
		}
		return result;
	}

	
	public ReturnModel login(String userno, String pwd) throws Exception {
		ReturnModel rq = new ReturnModel();
		if (ObjectUtils.isEmpty(userno) || ObjectUtils.isEmpty(pwd)) {
			rq.setStatu(ReturnStatus.ParamError);
			rq.setStatusreson("参数有误");
			return rq;
		}
		UUsers user = getUUser(userno);
		if (user != null && user.getPassword() != null && MD5Encrypt.encrypt(pwd).equals(user.getPassword())) {
			rq.setStatu(ReturnStatus.Success);
			rq.setStatusreson("成功");
			rq.setBasemodle(loginSuccess(user));
			return rq;
		}
		rq.setStatu(ReturnStatus.SystemError);
		rq.setStatusreson("用户名或密码错误！");

		return rq;
	}

	/**
	 * ���� userId/userName/nickName ��ȡUUser
	 * @param userno
	 * @return
	 */
	private UUsers getUUser(String userno) {
		Long userid = ParseHelper.toLong(userno);
		if (userid > 0) {//
			UUsers user = userDao.getUUsersByUserID(userid);
			if (user != null) {
				return user;
			}
		}
		UUsers user = userDao.getUUsersByUserName(userno);
		if (user != null) {
			return user;
		}
		return null;
	}

	/**
	 * 
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ReturnModel register(RegisterParam param) throws Exception {
		ReturnModel rq=new ReturnModel();
		rq.setStatu(ReturnStatus.SystemError);
		if(param==null||ObjectUtils.isEmpty(param.getPassword()) ){
			rq.setStatu(ReturnStatus.ParamError);
			rq.setStatusreson("���벻��Ϊ��");
			return rq;
		}
		UUsers model=new UUsers();
		model.setPassword(MD5Encrypt.encrypt(param.getPassword()));
		model.setCreatetime(new Date());
		model.setStatus(1);
		if(!ObjectUtils.isEmpty(param.getUsername()) ){
			if(!checkUser(param.getUsername(), 1)){
				rq.setStatusreson("�û����Ѵ���");
				return rq;
			}
			model.setUsername(param.getUsername()); 
		}
		if(!ObjectUtils.isEmpty(param.getMobilephone())){
			model.setMobilephone(param.getMobilephone());
		} 
		userDao.insert(model);
		rq.setStatu(ReturnStatus.Success);
		rq.setBasemodle(model);
		return rq;
	}
	
	
	/**
	 * 
	 * @param userno
	 * @param type
	 * @return
	 */
	public boolean checkUser(String userno,int type){
		switch (type) {
		case 1://�û���
			UUsers users=userDao.getUUsersByUserName(userno);
			if(users!=null){
				return false;
			}
			break;
		default:
			break;
		}
		return true;
	}
	
	private LoginSuccessInfo loginSuccess(UUsers user){
		if(user!=null){
			LoginSuccessInfo result=new LoginSuccessInfo();
			result.setUserid(user.getUserid());
			result.setUsername(user.getUsername());
			result.setCreatetime(user.getCreatetime());
			result.setEmail(user.getEmail());
			result.setIdentity(user.getIdentity());
			result.setMobilebind(user.getMobilebind());
			result.setMobilephone(user.getMobilephone());
			result.setNickname(user.getNickname());
			result.setUserimg(user.getUserimg());
			String s = UUID.randomUUID().toString();
			String ticket="WD"+s;
			RedisUtil.setObject(ticket, result,3600);
			result.setTicket(ticket);
			return result;
		}
		return null;
	}
	
	
	public void editUser(UserInfoModel model) throws Exception{
		if(model!=null){
			 if(model.getUserid()!=null&&model.getUserid()>0){
				 UUsers users=userDao.getUUsersByUserID(model.getUserid());
				 if(users!=null){
					 if(!ObjectUtils.isEmpty(model.getNickname()) ){
						 users.setNickname(model.getNickname());
					 }
					 if(!ObjectUtils.isEmpty(model.getEmail())){
						 users.setEmail(model.getEmail());
					 }
					 userDao.updateByPrimaryKey(users);
				 }
			 }
		}
	}
}

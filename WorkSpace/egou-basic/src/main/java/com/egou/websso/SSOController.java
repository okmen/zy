package com.egou.websso;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import com.egou.utils.ObjectUtils;
import com.egou.utils.RedisUtil;
import com.egou.vo.user.LoginSuccessInfo;

public class SSOController {

	@Autowired
	HttpServletRequest request;

	/**
	 *
	 */
	private LoginSuccessInfo user;

	/**
	 * 
	 * @return
	 */
	public LoginSuccessInfo getUser() {
		String tiket = request.getParameter("ticket");
		if (ObjectUtils.isEmpty(tiket)) {
			tiket = getCookieByName(request, "ticket");// ֵ
			if (ObjectUtils.isEmpty(tiket)) {
				return null;
			}
		}
		Object userObject = RedisUtil.getObject(tiket);
		if (userObject != null)//
		{
			RedisUtil.setExpire(tiket, 1800);//
			user = (LoginSuccessInfo) userObject;
			return user;
		}
		return null;//
	}

	private String getCookieByName(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (ObjectUtils.isEmpty(name)) {
			return null;
		}
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName().trim())) {
					return cookie.getValue();
				}
			}
			return null;
		} else {
			return null;
		}
	}
}

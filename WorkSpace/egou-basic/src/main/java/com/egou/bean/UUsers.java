package com.egou.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 用户基本表
 * @author Administrator
 *
 */
public class UUsers  implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private Long userid;

    private String username;

    private String password;

    private String mobilephone;

    private Integer mobilebind;

    private Integer status;

    private Long identity;

    private String userimg;

    private String nickname;

    private String email;

    private Date createtime;

    
    
    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone == null ? null : mobilephone.trim();
    }

    public Integer getMobilebind() {
        return mobilebind;
    }

    public void setMobilebind(Integer mobilebind) {
        this.mobilebind = mobilebind;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getIdentity() {
        return identity;
    }

    public void setIdentity(Long identity) {
        this.identity = identity;
    }

    public String getUserimg() {
        return userimg;
    }

    public void setUserimg(String userimg) {
        this.userimg = userimg == null ? null : userimg.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
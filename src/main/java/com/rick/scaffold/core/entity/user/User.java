package com.rick.scaffold.core.entity.user;

import java.util.Date;

import com.rick.scaffold.core.entity.generic.DataEntity;

public class User extends DataEntity<User> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String loginName;

    private String password;

    private String name;

    private String email;

    private String phone;

    private Long userTag;

    private String avatar;

    private String loginIp;

    private Date loginDate;


    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Long getUserTag() {
        return userTag;
    }

    public void setUserTag(Long userTag) {
        this.userTag = userTag;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

}
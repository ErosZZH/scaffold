package com.rick.scaffold.common.security;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rick.scaffold.core.entity.user.User;

public class AuthenticationRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(getClass());
	

	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String loginname = token.getUsername();
		logger.debug("login name is: " + loginname);
		User user = new User();
		user.setName(loginname);
		user.setId("1");
		String s = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update("admin".getBytes());
			byte messageDigest[] = digest.digest();
			StringBuilder hexString = new StringBuilder();
			for(int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if(shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			s = hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		user.setPassword(s);
		return new SimpleAuthenticationInfo(new Principal(user), user.getPassword(), getName());
	}
	
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("SHA-1");
		setCredentialsMatcher(matcher);
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		return info;
	}
	
	@Override
	protected void checkPermission(Permission permission, AuthorizationInfo info) {
		authorizationValidate(permission);
		super.checkPermission(permission, info);
	}
	
	@Override
	protected boolean[] isPermitted(List<Permission> permissions, AuthorizationInfo info) {
		if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
        		authorizationValidate(permission);
            }
        }
		return super.isPermitted(permissions, info);
	}
	
	@Override
	public boolean isPermitted(PrincipalCollection principals, Permission permission) {
		authorizationValidate(permission);
		return super.isPermitted(principals, permission);
	}
	
	@Override
	protected boolean isPermittedAll(Collection<Permission> permissions, AuthorizationInfo info) {
		if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
            	authorizationValidate(permission);
            }
        }
		return super.isPermittedAll(permissions, info);
	}
	
	/**
	 * 授权验证方法
	 * @param permission
	 */
	private void authorizationValidate(Permission permission){
		// 模块授权预留接口
	}
	
	/**
	 * 授权用户信息
	 */
	public static class Principal implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private String id; // 编号
		private String name; // 姓名
		
		public Principal(User user) {
			this.id = user.getId();
			this.name = user.getName();
		}

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

	}
}

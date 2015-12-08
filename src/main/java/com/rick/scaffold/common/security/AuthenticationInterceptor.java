package com.rick.scaffold.common.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.rick.scaffold.common.Constants;
import com.rick.scaffold.exception.APIException;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory
			.getLogger(AuthenticationInterceptor.class);
	
	@Autowired
	private SecurityComponent securityComponent;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		//TODO cache token
		String token = request.getHeader("Authorization");
		if(StringUtils.isEmpty(token)) {
			logger.error("Has no token! " + request.getRequestURI());
			throw new APIException("非法用户", 401);
		} else if(token.equals("guest")) {
			request.setAttribute("userId", 0L);
			request.setAttribute("userRole", "guest");
		} else {
			String userInfo = securityComponent.decrypt(token);
			if(userInfo == null) {
				throw new APIException("非法用户", 401);
			}
			try {
				String[] s = userInfo.split(Constants.STRING_SEPERATOR);
				String userId = s[0];
				String userRole = s[1];
				request.setAttribute("userId", Long.valueOf(userId));
				request.setAttribute("userRole", userRole);
			} catch (Exception e) {
				throw new APIException("非法用户", 401);
			}
		}
		return true;
	}
}

package com.rick.scaffold.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rick.scaffold.common.config.Global;
import com.rick.scaffold.common.security.SecurityComponent;

@Controller
public class LoginController {
	
	@Autowired
	private SecurityComponent sc;

	@RequestMapping(value="/admin/login", method=RequestMethod.GET)
	public String displayLogin(HttpServletRequest request, Model model) {
		String productName = Global.getConfig("global.productName");
		String version = Global.getConfig("global.version");
		String staticDomain = Global.getConfig("global.staticDomain");
		model.addAttribute("productName", productName);
		model.addAttribute("version", version);
		model.addAttribute("staticDomain", staticDomain);
		return "login";
	}
	
	/**
	 * 登录失败，真正登录的POST请求由Filter完成
	 */
	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	public String loginFail(HttpServletRequest request, HttpServletResponse response, Model model) {
		String message = (String)request.getAttribute("message");
		if (message == null){
			return "redirect:/admin";
		} else {
			String productName = Global.getConfig("global.productName");
			String version = Global.getConfig("global.version");
			String staticDomain = Global.getConfig("global.staticDomain");
			model.addAttribute("productName", productName);
			model.addAttribute("version", version);
			model.addAttribute("staticDomain", staticDomain);
			return "login";
		}
		
	}
	
}

package com.rick.scaffold.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rick.scaffold.common.config.Global;

@Controller
public class LoginController {

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
}

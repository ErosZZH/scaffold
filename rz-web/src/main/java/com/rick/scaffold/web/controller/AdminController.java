package com.rick.scaffold.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rick.scaffold.common.config.Global;

@Controller
public class AdminController {

	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String displayLogin(Model model) {
		String staticDomain = Global.getConfig("global.staticDomain");
		model.addAttribute("staticDomain", staticDomain);
		return "adminIndex";
	}
}

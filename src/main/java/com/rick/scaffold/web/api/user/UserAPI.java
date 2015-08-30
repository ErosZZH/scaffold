package com.rick.scaffold.web.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rick.scaffold.common.jsontool.JsonMapper;
import com.rick.scaffold.core.entity.user.User;
import com.rick.scaffold.core.service.user.UserService;

@Controller
@RequestMapping(value="/api/user",produces={"application/json;charset=UTF-8"})
public class UserAPI {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String getUser(@PathVariable("id") Long id) {
		User user = userService.findOne(id);
		return JsonMapper.getInstance().toJson(user);
	}
}

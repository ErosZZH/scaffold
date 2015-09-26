package com.rick.scaffold.web.api.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rick.scaffold.core.entity.user.FullUser;
import com.rick.scaffold.core.service.user.UserService;
import com.rick.scaffold.exception.APIException;
import com.rick.scaffold.web.api.generic.BaseAPI;

@Controller
@RequestMapping(value="/api/user",produces={"application/json;charset=UTF-8"})
public class UserAPI extends BaseAPI{
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getUser(@PathVariable("id") Long id) throws APIException {
//		Map<String, Object> users = userService.findAll1();
		List<FullUser> users = userService.findLazy(1l);
		return responseSuccess(users);
	}
}

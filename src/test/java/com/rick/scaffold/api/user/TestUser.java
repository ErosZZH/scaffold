package com.rick.scaffold.api.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

import com.rick.scaffold.base.BaseApiTest;


public class TestUser extends BaseApiTest {

	@Test
	public void testGetUserAPI() throws Exception {
		mockMvc.perform(get("/api/user/1").accept(MediaType.APPLICATION_JSON)).
		andDo(print()).
		andExpect(status().isOk());	
	}
}

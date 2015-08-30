package com.rick.scaffold.api.user;

import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.rick.scaffold.api.generic.BaseTest;


public class TestUser extends BaseTest {

	@Test
	public void testGetUserAPI() throws Exception {
		mockMvc.perform(get("/api/user/1").accept(MediaType.APPLICATION_JSON)).
		andDo(print()).
		andExpect(status().isOk());	
	}
}

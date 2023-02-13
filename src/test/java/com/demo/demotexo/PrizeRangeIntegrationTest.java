package com.demo.demotexo;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class PrizeRangeIntegrationTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	@DisplayName("Retorno OK")
	void worstFilmList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/prizeRange"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	@DisplayName("Test min value")
	void worstFilmMinTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/prizeRange"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(jsonPath("$.min[0].producer", is("Bo Derek")))
			.andExpect(jsonPath("$.min[0].interval", is(6)))
			.andExpect(jsonPath("$.min[0].previousWin", is(1984)))
			.andExpect(jsonPath("$.min[0].followingWin", is(1990)))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	@DisplayName("Test max value")
	void worstFilmMaxTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/prizeRange"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(jsonPath("$.max[0].producer", is("Bo Derek")))
			.andExpect(jsonPath("$.max[0].interval", is(6)))
			.andExpect(jsonPath("$.max[0].previousWin", is(1984)))
			.andExpect(jsonPath("$.max[0].followingWin", is(1990)))
			.andDo(MockMvcResultHandlers.print());
	}
	
	
	
	

}

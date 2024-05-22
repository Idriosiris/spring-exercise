package com.example.springexample;

import com.example.springexample.domain.Address;
import com.example.springexample.domain.Company;
import com.example.springexample.domain.Officer;
import com.example.springexample.json.TestAddress;
import com.example.springexample.json.TestCompany;
import com.example.springexample.json.TestCompanySearchResponse;
import com.example.springexample.json.TestOfficer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TestingWebApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void shouldReturnHealthCheckMessage() throws Exception {
		this.mockMvc.perform(get("/healthcheck/")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Healthy!")));
	}

	@Test
	void shouldReturnAllCompanies() throws Exception {

		this.mockMvc.perform(post("/search/")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(new TestCompanySearchResponse(new TestCompany[]{
						new TestCompany(
								"06500244",
								"ltd",
								"BBC LIMITED",
								"active",
								"2008-02-11",
								new TestAddress(
										"Retford",
										"DN22 0AD",
										"Boswell Cottage Main Street",
										"North Leverton",
										"England"),
								new TestOfficer[]{
										new TestOfficer(
												"BOXALL, Sarah Victoria",
												"secretary",
												"2008-02-11",
												new TestAddress(
														"5",
														"London",
														"Cranford Close",
														"England",
														"SW20 0DP"
												)
										)
								}
						),
				}, 1))));
	}
}

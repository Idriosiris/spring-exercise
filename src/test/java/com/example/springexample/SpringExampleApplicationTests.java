package com.example.springexample;

import com.example.springexample.json.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.maciejwalkowiak.wiremock.spring.ConfigureWireMock;
import com.maciejwalkowiak.wiremock.spring.EnableWireMock;
import com.maciejwalkowiak.wiremock.spring.InjectWireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(value = "integration")
@SpringBootTest
@AutoConfigureMockMvc
@EnableWireMock({
        @ConfigureWireMock(name = "tru-proxy-API", port = 5364)
})
class TestingWebApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectWireMock("tru-proxy-API")
    private WireMockServer truProxyAPIWireMockServer;

    @Test
    void shouldReturnHealthCheckMessage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/healthcheck/")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Healthy!")));
    }

    @Test
    void shouldReturnAllCompanies() throws Exception {
        this.truProxyAPIWireMockServer.stubFor(
                get("/Search?Query=BBC%20LIMITED")
                        .withHeader("x-api-key", equalTo("test-api-key"))
                        .willReturn(
                                aResponse()
                                        .withHeader("Content-Type", "application/json")
                                        .withBody(
                                                """
                                                        	{
                                                        	  "page_number": 1,
                                                        	  "kind": "search#companies",
                                                        	  "total_results": 20,
                                                        	  "items": [
                                                        		  {
                                                        			  "company_status": "active",
                                                        			  "address_snippet": "Boswell Cottage Main Street, North Leverton, Retford, England, DN22 0AD",
                                                        			  "date_of_creation": "2008-02-11",
                                                        			  "matches": {
                                                        				  "title": [
                                                        					  1,
                                                        					  3
                                                        				  ]
                                                        			  },
                                                        			  "description": "06500244 - Incorporated on 11 February 2008",
                                                        			  "links": {
                                                        				  "self": "/company/06500244"
                                                        			  },
                                                        			  "company_number": "06500244",
                                                        			  "title": "BBC LIMITED",
                                                        			  "company_type": "ltd",
                                                        			  "address": {
                                                        				  "premises": "Boswell Cottage Main Street",
                                                        				  "postal_code": "DN22 0AD",
                                                        				  "country": "England",
                                                        				  "locality": "Retford",
                                                        				  "address_line_1": "North Leverton"
                                                        			  },
                                                        			  "kind": "searchresults#company",
                                                        			  "description_identifier": [
                                                        				  "incorporated-on"
                                                        			  ]
                                                        		  }]
                                                        	}
                                                        """)
                        ));

        this.mockMvc.perform(post("/search/").with(
						request -> {
							request.addHeader("Content-Type", "application/json");
							request.addHeader("x-api-key", "test-api-key");
							return request;
						}
				).content(objectMapper.writeValueAsString(new TestCompanySearchRequestBody("BBC LIMITED", "06500244"))))
                .andDo(print())
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
                }, 20))));
    }
}

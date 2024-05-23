package com.example.companylookup;

import com.example.companylookup.json.*;
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

import static com.example.companylookup.stubs.TruProxyAPIData.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(value = "integration")
@SpringBootTest
@AutoConfigureMockMvc
@EnableWireMock({@ConfigureWireMock(name = "tru-proxy-API", port = 5364)})
class CompanyLookupApplicationTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @InjectWireMock("tru-proxy-API")
  private WireMockServer truProxyAPIWireMockServer;

  @Test
  void shouldReturnHealthCheckMessage() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/healthcheck/")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("Healthy!")));
  }

  @Test
  void getCompaniesByCompanyName() throws Exception {
    stubTruProxyAPI("/Search?Query=BBC%20LIMITED", ACTIVE_COMPANY);

    stubTruProxyAPI("/Officers?CompanyNumber=06500244", ANTLES_KERRI_ACTIVE_OFFICER);

    this.mockMvc
            .perform(
                    post("/search")
                            .with(
                                    request -> {
                                      request.addHeader("Content-Type", "application/json");
                                      request.addHeader("x-api-key", "test-api-key");

                                      return request;
                                    })
                            .content(
                                    objectMapper.writeValueAsString(
                                            new TestCompanySearchRequest("BBC LIMITED")
                                    )))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(
                    content().json(
                            objectMapper.writeValueAsString(
                                    new TestCompanySearchResponse(
                                            new TestCompany[]{
                                                    new TestCompany(
                                                            "06500244",
                                                            "ltd",
                                                            "BBC LIMITED",
                                                            "active",
                                                            "2008-02-11",
                                                            new TestAddress("Retford", "DN22 0AD", "Boswell Cottage Main Street", "North Leverton", "England"),
                                                            new TestOfficer[]{
                                                                    new TestOfficer(
                                                                            "ANTLES, Kerri",
                                                                            "director",
                                                                            "2017-04-01",
                                                                            new TestAddress("The Leeming Building", "Leeds", "Vicar Lane", "England", "LS2 7JF")
                                                                    )
                                                            })
                                            },
                                            20
                                    ))));
  }

  @Test
  void searchByCompanyNumberWhenBothNameAndNumberAreSupplied() throws Exception {
    stubTruProxyAPI("/Search?Query=06500244", ACTIVE_COMPANY);

    stubTruProxyAPI("/Officers?CompanyNumber=06500244", TWO_ACTIVE_OFFICERS);

    this.mockMvc.perform(post("/search").with(request -> {
      request.addHeader("Content-Type", "application/json");
      request.addHeader("x-api-key", "test-api-key");
      return request;
    }).content(
            objectMapper.writeValueAsString(
                    new TestCompanySearchRequest("BBC LIMITED", "06500244"))))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(
                    objectMapper.writeValueAsString(
                            new TestCompanySearchResponse(
                                    new TestCompany[]{
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
                                                                    "ANTLES, Kerri",
                                                                    "director",
                                                                    "2017-04-01",
                                                                    new TestAddress(
                                                                            "The Leeming Building",
                                                                            "Leeds",
                                                                            "Vicar Lane",
                                                                            "England",
                                                                            "LS2 7JF"
                                                                    )
                                                            ),
                                                            new TestOfficer(
                                                                    "JOHN, Doe",
                                                                    "director",
                                                                    "2017-04-01",
                                                                    new TestAddress(
                                                                            "The Leeming Building",
                                                                            "Leeds",
                                                                            "Vicar Lane",
                                                                            "England",
                                                                            "LS2 7JF"
                                                                    )
                                                            )
                                                    })
                                            },
                                    20
                            ))));
  }

  @Test
  void addFlagToConsiderOnlyActiveCompanies() throws Exception {
    stubTruProxyAPI("/Search?Query=BBC%20LIMITED", ACTIVE_AND_DISSOLVED_COMPANIES);

    stubTruProxyAPI("/Officers?CompanyNumber=06500244", ANTLES_KERRI_ACTIVE_OFFICER);

    this.mockMvc
            .perform(
                    post("/search?activeOnly=true")
                            .with(
                                    request -> {
                                      request.addHeader("Content-Type", "application/json");
                                      request.addHeader("x-api-key", "test-api-key");

                                      return request;
                                    })
                            .content(
                                    objectMapper.writeValueAsString(
                                            new TestCompanySearchRequest("BBC LIMITED", null)
                                    )))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(
                    content().json(
                            objectMapper.writeValueAsString(
                                    new TestCompanySearchResponse(
                                            new TestCompany[]{
                                                    new TestCompany(
                                                            "06500244",
                                                            "ltd",
                                                            "BBC LIMITED",
                                                            "active",
                                                            "2008-02-11",
                                                            new TestAddress("Retford", "DN22 0AD", "Boswell Cottage Main Street", "North Leverton", "England"),
                                                            new TestOfficer[]{
                                                                    new TestOfficer(
                                                                            "ANTLES, Kerri",
                                                                            "director",
                                                                            "2017-04-01",
                                                                            new TestAddress("The Leeming Building", "Leeds", "Vicar Lane", "England", "LS2 7JF")
                                                                    )
                                                            })
                                            },
                                            20
                                    ))));
  }

  @Test
  void addFlagToConsiderCompaniesWithAnyStatus() throws Exception {
    stubTruProxyAPI("/Search?Query=BBC%20LIMITED", ACTIVE_AND_DISSOLVED_COMPANIES);

    stubTruProxyAPI("/Officers?CompanyNumber=06500244", ANTLES_KERRI_ACTIVE_OFFICER);

    stubTruProxyAPI("/Officers?CompanyNumber=1827388", ANTLES_KERRI_ACTIVE_OFFICER);

    this.mockMvc
            .perform(
                    post("/search?activeOnly=false")
                            .with(
                                    request -> {
                                      request.addHeader("Content-Type", "application/json");
                                      request.addHeader("x-api-key", "test-api-key");

                                      return request;
                                    })
                            .content(
                                    objectMapper.writeValueAsString(
                                            new TestCompanySearchRequest("BBC LIMITED", null)
                                    )))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(
                    content().json(
                            objectMapper.writeValueAsString(
                                    new TestCompanySearchResponse(
                                            new TestCompany[]{
                                                    new TestCompany(
                                                            "06500244",
                                                            "ltd",
                                                            "BBC LIMITED",
                                                            "active",
                                                            "2008-02-11",
                                                            new TestAddress("Retford", "DN22 0AD", "Boswell Cottage Main Street", "North Leverton", "England"),
                                                            new TestOfficer[]{
                                                                    new TestOfficer(
                                                                            "ANTLES, Kerri",
                                                                            "director",
                                                                            "2017-04-01",
                                                                            new TestAddress("The Leeming Building", "Leeds", "Vicar Lane", "England", "LS2 7JF")
                                                                    )
                                                            }),
                                                    new TestCompany(
                                                            "1827388",
                                                            "ltd",
                                                            "BBC UNLIMITED",
                                                            "dissolved",
                                                            "2008-02-11",
                                                            new TestAddress("Retford", "DN22 0AD", "Boswell Cottage Main Street", "North Leverton", "England"),
                                                            new TestOfficer[]{
                                                                    new TestOfficer(
                                                                            "ANTLES, Kerri",
                                                                            "director",
                                                                            "2017-04-01",
                                                                            new TestAddress("The Leeming Building", "Leeds", "Vicar Lane", "England", "LS2 7JF")
                                                                    )
                                                            })

                                            },
                                            20
                                    ))));
  }

  @Test
  void onlyActiveOfficersShouldBeIncluded() throws Exception {
    stubTruProxyAPI("/Search?Query=06500244", ACTIVE_COMPANY);

    stubTruProxyAPI("/Officers?CompanyNumber=06500244", ACTIVE_AND_INACTIVE_OFFICERS);


    this.mockMvc
            .perform(
                    post("/search")
                            .with(
                                    request -> {
                                      request.addHeader("Content-Type", "application/json");
                                      request.addHeader("x-api-key", "test-api-key");

                                      return request;
                                    })
                            .content(
                                    objectMapper.writeValueAsString(
                                            new TestCompanySearchRequest("BBC LIMITED", "06500244"))))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(
                    content().json(
                            objectMapper.writeValueAsString(
                                    new TestCompanySearchResponse(
                                            new TestCompany[]{
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
                                                                    "England"
                                                            ),
                                                            new TestOfficer[]{
                                                                    new TestOfficer(
                                                                            "ANTLES, Kerri",
                                                                            "director",
                                                                            "2017-04-01",
                                                                            new TestAddress(
                                                                                    "The Leeming Building",
                                                                                    "Leeds",
                                                                                    "Vicar Lane",
                                                                                    "England",
                                                                                    "LS2 7JF"
                                                                            )
                                                                    )
                                                            })
                                                    },
                                            20))));
  }

  @Test
  void noCompaniesShouldBeReturnedWhenTruApiReturnsNoSearchItems() throws Exception {
    stubTruProxyAPI("/Search?Query=BBC%20LIMITED", """
            	{
            	  "page_number": 1,
            	  "kind": "search#companies",
            	  "total_results": 0,
            	  "items": []
            	}
            """);

    this.mockMvc.perform(post("/search").with(request -> {
      request.addHeader("Content-Type", "application/json");
      request.addHeader("x-api-key", "test-api-key");
      return request;
    }).content(objectMapper.writeValueAsString(new TestCompanySearchRequest("BBC LIMITED")))).andDo(print()).andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(new TestCompanySearchResponse(new TestCompany[]{}, 0))));
  }

  @Test
  void whenNoOfficersExistForACompanyReturnAnEmptyOfficersList() throws Exception {
    stubTruProxyAPI("/Search?Query=BBC%20LIMITED", ACTIVE_COMPANY);

    stubTruProxyAPI("/Officers?CompanyNumber=06500244", NO_OFFICERS);

    this.mockMvc.perform(post("/search").with(request -> {
      request.addHeader("Content-Type", "application/json");
      request.addHeader("x-api-key", "test-api-key");
      return request;
    }).content(
            objectMapper.writeValueAsString(
                    new TestCompanySearchRequest("BBC LIMITED"))))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(
                    objectMapper.writeValueAsString(
                            new TestCompanySearchResponse(
                                    new TestCompany[]{
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
                                                            "England"
                                                    ),
                                                    new TestOfficer[]{}
                                            )
                                    },
                                    20))));
  }

  @Test
  void whenOfficersItemsArrayIsNullTreatItAsEmpty() throws Exception {
    stubTruProxyAPI("/Search?Query=BBC%20LIMITED", ACTIVE_COMPANY);

    stubTruProxyAPI("/Officers?CompanyNumber=06500244", """
            {
              "etag": "6dd2261e61776d79c2c50685145fac364e75e24e",
              "links": {
                "self": "/company/10241297/officers"
              },
              "kind": "officer-list",
              "items_per_page": 35
            }
            """);

    this.mockMvc.perform(post("/search").with(request -> {
      request.addHeader("Content-Type", "application/json");
      request.addHeader("x-api-key", "test-api-key");
      return request;
    }).content(objectMapper.writeValueAsString(new TestCompanySearchRequest("BBC LIMITED")))).andDo(print()).andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(new TestCompanySearchResponse(new TestCompany[]{new TestCompany("06500244", "ltd", "BBC LIMITED", "active", "2008-02-11", new TestAddress("Retford", "DN22 0AD", "Boswell Cottage Main Street", "North Leverton", "England"), new TestOfficer[]{}),}, 20))));
  }

  private void stubTruProxyAPI(String url, String activeAndDissolvedCompanies) {
    this.truProxyAPIWireMockServer.stubFor(get(url).withHeader("x-api-key",
            equalTo("test-api-key")).willReturn(aResponse().withHeader("Content-Type", "application/json").withBody(activeAndDissolvedCompanies)));
  }
}

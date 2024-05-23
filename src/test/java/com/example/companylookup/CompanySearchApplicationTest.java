package com.example.companylookup;

import com.example.companylookup.json.*;
import com.example.companylookup.lookup.repository.CompanySearchRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.maciejwalkowiak.wiremock.spring.ConfigureWireMock;
import com.maciejwalkowiak.wiremock.spring.EnableWireMock;
import com.maciejwalkowiak.wiremock.spring.InjectWireMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
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
class CompanySearchApplicationTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private CompanySearchRepository companySearchRepository;

  @InjectWireMock("tru-proxy-API")
  private WireMockServer truProxyAPIWireMockServer;

  @BeforeEach
  void setUp() {
    companySearchRepository.deleteAll();
  }

  @Test
  void shouldReturnHealthCheckMessage() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/healthcheck/")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("Healthy!")));
  }

  @Test
  void getCompaniesByCompanyName() throws Exception {
    stubTruProxyAPI("/Search?Query=BBC%20LIMITED", ACTIVE_COMPANY);
    stubTruProxyAPI("/Officers?CompanyNumber=06500244", ANTLES_KERRI_ACTIVE_OFFICER);

    whenCalling(
            postRequest("/search", new TestCompanySearchRequest("BBC LIMITED")),
            expect(new TestCompany(
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
                    })));
  }

  @Test
  void searchByCompanyNumberWhenBothNameAndNumberAreSupplied() throws Exception {
    stubTruProxyAPI("/Search?Query=06500244", ACTIVE_COMPANY);
    stubTruProxyAPI("/Officers?CompanyNumber=06500244", TWO_ACTIVE_OFFICERS);

    whenCalling(
            postRequest("/search", new TestCompanySearchRequest("BBC LIMITED", "06500244")),
            expect(
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
                            })));
  }

  @Test
  void addFlagToConsiderOnlyActiveCompanies() throws Exception {
    stubTruProxyAPI("/Search?Query=BBC%20LIMITED", ACTIVE_AND_DISSOLVED_COMPANIES);
    stubTruProxyAPI("/Officers?CompanyNumber=06500244", ANTLES_KERRI_ACTIVE_OFFICER);

    whenCalling(
            postRequest("/search?activeOnly=true", new TestCompanySearchRequest("BBC LIMITED", null)),
            expect(
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
                            })));
  }

  @Test
  void addFlagToConsiderCompaniesWithAnyStatus() throws Exception {
    stubTruProxyAPI("/Search?Query=BBC%20LIMITED", ACTIVE_AND_DISSOLVED_COMPANIES);
    stubTruProxyAPI("/Officers?CompanyNumber=06500244", ANTLES_KERRI_ACTIVE_OFFICER);
    stubTruProxyAPI("/Officers?CompanyNumber=1827388", ANTLES_KERRI_ACTIVE_OFFICER);

    whenCalling(
            postRequest("/search?activeOnly=false", new TestCompanySearchRequest("BBC LIMITED", null)),
            expect(
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
                            })));
  }

  @Test
  void onlyActiveOfficersShouldBeIncluded() throws Exception {
    stubTruProxyAPI("/Search?Query=06500244", ACTIVE_COMPANY);

    stubTruProxyAPI("/Officers?CompanyNumber=06500244", ACTIVE_AND_INACTIVE_OFFICERS);


    whenCalling(
            postRequest("/search", new TestCompanySearchRequest("BBC LIMITED", "06500244")),
            expect(
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
                            })));
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

    this.mockMvc.perform(postRequest("/search", new TestCompanySearchRequest("BBC LIMITED"))).andDo(print()).andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(new TestCompanySearchResponse(new TestCompany[]{}, 0))));
  }

  @Test
  void whenNoOfficersExistForACompanyReturnAnEmptyOfficersList() throws Exception {
    stubTruProxyAPI("/Search?Query=BBC%20LIMITED", ACTIVE_COMPANY);

    stubTruProxyAPI("/Officers?CompanyNumber=06500244", NO_OFFICERS);

    whenCalling(
            postRequest("/search", new TestCompanySearchRequest("BBC LIMITED")),
            expect(
                    new TestCompany(
                            "06500244",
                            "ltd",
                            "BBC LIMITED",
                            "active",
                            "2008-02-11",
                            new TestAddress("Retford", "DN22 0AD", "Boswell Cottage Main Street", "North Leverton", "England"),
                            new TestOfficer[]{})));
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



    whenCalling(
            postRequest("/search", new TestCompanySearchRequest("BBC LIMITED")),
            expect(
                    new TestCompany(
                            "06500244",
                            "ltd",
                            "BBC LIMITED",
                            "active",
                            "2008-02-11",
                            new TestAddress("Retford", "DN22 0AD", "Boswell Cottage Main Street", "North Leverton", "England"),
                            new TestOfficer[]{})));
  }

  private MockHttpServletRequestBuilder postRequest(String onEndpoint, TestCompanySearchRequest withBody) throws JsonProcessingException {
    return post(onEndpoint)
            .with(
                    request -> {
                      request.addHeader("Content-Type", "application/json");
                      request.addHeader("x-api-key", "test-api-key");

                      return request;
                    })
            .content(
                    objectMapper.writeValueAsString(
                            withBody
                    ));
  }

  private static TestCompany[] expect(TestCompany... testCompany) {
    return testCompany;
  }

  private void stubTruProxyAPI(String url, String body) {
    this.truProxyAPIWireMockServer.stubFor(
            get(url).withHeader("x-api-key", equalTo("test-api-key"))
                    .willReturn(
                            aResponse()
                                    .withHeader("Content-Type", "application/json")
                                    .withBody(body)));
  }

  private void whenCalling(MockHttpServletRequestBuilder httpServlet, TestCompany... testCompanies) throws Exception {
    this.mockMvc
            .perform(httpServlet)
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(
                    content().json(
                            objectMapper.writeValueAsString(
                                    new TestCompanySearchResponse(
                                            testCompanies,
                                            20
                                    ))));
  }

}

package com.example.springexample.controller;

import com.example.springexample.controller.response.TrueProxyAPICompanyResponse;
import com.example.springexample.domain.Address;
import com.example.springexample.domain.Company;
import com.example.springexample.domain.CompanySearchResponse;
import com.example.springexample.domain.Officer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("search")
public class SearchController {

	private final RestTemplate restTemplate;
	private final String truProxyApiUrl;

	public SearchController(RestTemplate restTemplate, @Value("${tru-proxy-api.url}") String truProxyApiUrl) {
		this.restTemplate = restTemplate;
		this.truProxyApiUrl = truProxyApiUrl;
	}

	@PostMapping("/")
	public @ResponseBody CompanySearchResponse search() {
		return toCompanySearchResponse(searchForTrueProxyAPICompany());
	}

	private static CompanySearchResponse toCompanySearchResponse(TrueProxyAPICompanyResponse trueProxyAPICompanyResponse) {
		return new CompanySearchResponse(new Company[]{
				new Company(
						"06500244",
						"ltd",
						"BBC LIMITED",
						"active",
						"2008-02-11",
						new Address(
								"Retford",
								"DN22 0AD",
								"Boswell Cottage Main Street",
								"North Leverton",
								"England"),
						new Officer[]{
								new Officer(
										"BOXALL, Sarah Victoria",
										"secretary",
										"2008-02-11",
										new Address(
												"5",
												"London",
												"Cranford Close",
												"England",
												"SW20 0DP"
										)
								)
						}
				),
		}, 1);
	}

	public TrueProxyAPICompanyResponse searchForTrueProxyAPICompany() {
		return this.restTemplate.getForObject(
				truProxyApiUrl + "/Search?Query={searchTerm}",
				TrueProxyAPICompanyResponse.class,
				"BBC");
	}

}
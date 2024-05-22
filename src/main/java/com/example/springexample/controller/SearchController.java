package com.example.springexample.controller;

import com.example.springexample.domain.Address;
import com.example.springexample.domain.Company;
import com.example.springexample.domain.CompanySearchResponse;
import com.example.springexample.domain.Officer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("search")
public class SearchController {

	@PostMapping("/")
	public @ResponseBody CompanySearchResponse search() {
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
		});
	}

}
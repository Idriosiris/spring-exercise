package com.example.companylookup.stubs;

public class TruProxyAPIData {
  public static final String ACTIVE_COMPANY = """
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
          """;

  public static final String ACTIVE_AND_DISSOLVED_COMPANIES = """
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
                    },
                    {
                        "company_status": "dissolved",
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
                        "company_number": "1827388",
                        "title": "BBC UNLIMITED",
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
                    }
                ]
              }
          """;

  public static final String ANTLES_KERRI_ACTIVE_OFFICER = """
           {
                       "etag": "6dd2261e61776d79c2c50685145fac364e75e24e",
                       "links": {
                           "self": "/company/10241297/officers"
                       },
                       "kind": "officer-list",
                       "items_per_page": 35,
                       "items": [
                           {
                               "address": {
                                   "premises": "The Leeming Building",
                                   "postal_code": "LS2 7JF",
                                   "country": "England",
                                   "locality": "Leeds",
                                   "address_line_1": "Vicar Lane"
                               },
                               "name": "ANTLES, Kerri",
                               "appointed_on": "2017-04-01",
                               "resigned_on": "2018-02-12",
                               "officer_role": "director",
                               "links": {
                                   "officer": {
                                       "appointments": "/officers/4R8_9bZ44w0_cRlrxoC-wRwaMiE/appointments"
                                   }
                               },
                               "date_of_birth": {
                                   "month": 6,
                                   "year": 1969
                               },
                               "occupation": "Finance And Accounting",
                               "country_of_residence": "United States",
                               "nationality": "American"
                           }]
                     }
          """;

  public static final String TWO_ACTIVE_OFFICERS = """
          {
            "etag": "6dd2261e61776d79c2c50685145fac364e75e24e",
            "links": {
              "self": "/company/10241297/officers"
            },
            "kind": "officer-list",
            "items_per_page": 35,
            "items": [
              {
                "address": {
                  "premises": "The Leeming Building",
                  "postal_code": "LS2 7JF",
                  "country": "England",
                  "locality": "Leeds",
                  "address_line_1": "Vicar Lane"
                },
                "name": "ANTLES, Kerri",
                "appointed_on": "2017-04-01",
                "resigned_on": "2018-02-12",
                "officer_role": "director",
                "links": {
                  "officer": {
                    "appointments": "/officers/4R8_9bZ44w0_cRlrxoC-wRwaMiE/appointments"
                  }
                },
                "date_of_birth": {
                  "month": 6,
                  "year": 1969
                },
                "occupation": "Finance And Accounting",
                "country_of_residence": "United States",
                "nationality": "American"
              },
              {
                "address": {
                  "premises": "The Leeming Building",
                  "postal_code": "LS2 7JF",
                  "country": "England",
                  "locality": "Leeds",
                  "address_line_1": "Vicar Lane"
                },
                "name": "JOHN, Doe",
                "appointed_on": "2017-04-01",
                "resigned_on": "2018-02-12",
                "officer_role": "director",
                "links": {
                  "officer": {
                    "appointments": "/officers/4R8_9bZ44w0_cRlrxoC-wRwaMiE/appointments"
                  }
                },
                "date_of_birth": {
                  "month": 6,
                  "year": 1969
                },
                "occupation": "Finance And Accounting",
                "country_of_residence": "United States",
                "nationality": "American"
              }
            ]
          }
          """;
  public static final String ACTIVE_AND_INACTIVE_OFFICERS = """
          {
            "etag": "6dd2261e61776d79c2c50685145fac364e75e24e",
            "links": {
              "self": "/company/10241297/officers"
            },
            "kind": "officer-list",
            "items_per_page": 35,
            "items": [
              {
                "address": {
                  "premises": "The Leeming Building",
                  "postal_code": "LS2 7JF",
                  "country": "England",
                  "locality": "Leeds",
                  "address_line_1": "Vicar Lane"
                },
                "name": "ANTLES, Kerri",
                "appointed_on": "2017-04-01",
                "resigned_on": "2018-02-12",
                "officer_role": "director",
                "links": {
                  "officer": {
                    "appointments": "/officers/4R8_9bZ44w0_cRlrxoC-wRwaMiE/appointments"
                  }
                },
                "date_of_birth": {
                  "month": 6,
                  "year": 1969
                },
                "occupation": "Finance And Accounting",
                "country_of_residence": "United States",
                "nationality": "American"
              },
              {
                "address": {
                  "premises": "The Leeming Building",
                  "postal_code": "LS2 7JF",
                  "country": "England",
                  "locality": "Leeds",
                  "address_line_1": "Vicar Lane"
                },
                "name": "JOHN, Doe",
                "appointed_on": "2017-04-01",
                "officer_role": "director",
                "links": {
                  "officer": {
                    "appointments": "/officers/4R8_9bZ44w0_cRlrxoC-wRwaMiE/appointments"
                  }
                },
                "date_of_birth": {
                  "month": 6,
                  "year": 1969
                },
                "occupation": "Finance And Accounting",
                "country_of_residence": "United States",
                "nationality": "American"
              }
            ]
          }
          """;

  public static final String NO_OFFICERS = """
          {
            "etag": "6dd2261e61776d79c2c50685145fac364e75e24e",
            "links": {
              "self": "/company/10241297/officers"
            },
            "kind": "officer-list",
            "items_per_page": 35,
            "items": []
          }
          """;

}

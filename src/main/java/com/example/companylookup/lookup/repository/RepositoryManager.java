package com.example.companylookup.lookup.repository;

import com.example.companylookup.lookup.dto.response.PostCompanySearchResponse;
import com.example.companylookup.lookup.repository.entity.CompanySearchEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class RepositoryManager {
  private final CompanySearchRepository companySearchRepository;
  private final ObjectMapper objectMapper;

  public RepositoryManager(
          CompanySearchRepository companySearchRepository,
          ObjectMapper objectMapper) {
    this.companySearchRepository = companySearchRepository;
    this.objectMapper = objectMapper;
  }


  public void saveCompanySearch(String companyNumber, PostCompanySearchResponse response) throws JsonProcessingException {
    CompanySearchEntity entity = new CompanySearchEntity();

    entity.setId(companyNumber);
    entity.setResponse(objectMapper.writeValueAsString(response));

    companySearchRepository.save(entity);
  }
}

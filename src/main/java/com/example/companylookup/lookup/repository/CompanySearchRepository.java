package com.example.companylookup.lookup.repository;

import com.example.companylookup.lookup.repository.entity.CompanySearchEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CompanySearchRepository extends CrudRepository<CompanySearchEntity, Long> {
  boolean existsById(String id);

  Optional<CompanySearchEntity> findById(String id);

  <S extends CompanySearchEntity> S save(S entity);
}
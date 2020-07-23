package com.finartz.study.service;

import com.finartz.study.entity.Company;

import java.util.List;

public interface CompanyService {
    Company get(Long id);
    Company getByName(String name);
    Company add(Company company);
    Company update(Company company);
    void delete(Long id);
    List<Company> getAll();
}

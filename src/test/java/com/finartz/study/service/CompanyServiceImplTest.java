package com.finartz.study.service;

import com.finartz.study.entity.Airport;
import com.finartz.study.entity.Company;
import com.finartz.study.repository.CompanyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompanyServiceImplTest {

    @Autowired
    CompanyService companyService;

    @MockBean
    CompanyRepository companyRepository;

    @Test
    void whenGiveValidName_thenCompanyShouldBeFound() {
        Company company = Company.builder().name("THY").build();
        Mockito.doReturn(company).when(companyRepository).findByName("THY");

        Company returnedCompany = companyService.getByName("THY");

        Assertions.assertEquals(returnedCompany.getName(), company.getName());
    }

    @Test
    void whenFetchAll_thenReturnAllRecords(){
        Company company1 = Company.builder().name("THY").build();
        Company company2 = Company.builder().name("PGS").build();

        List<Company> companyList = Arrays.asList(company1, company2);
        Mockito.when(companyRepository.findAll()).thenReturn(companyList);

        List<Company> fetchedList = companyService.getAll();
        Assertions.assertEquals(companyList.size(), fetchedList.size());
    }

    @Test
    void whenAddCompany_thenReturnSavedRecord() {
        Company company = Company.builder().name("THY").build();
        Mockito.doReturn(company).when(companyRepository).save(ArgumentMatchers.any());

        Company returnedCompany = companyService.add(company);

        assertEquals(company.getName(), returnedCompany.getName());
    }

}
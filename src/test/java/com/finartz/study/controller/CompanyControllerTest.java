package com.finartz.study.controller;

import com.finartz.study.entity.Airport;
import com.finartz.study.entity.Company;
import com.finartz.study.service.AirportService;
import com.finartz.study.service.CompanyService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CompanyController.class)
class CompanyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @Test
    void whenGetAllCompanies_thenReturnCompanies() throws Exception {

        Company company = Company.builder().name("THY").build();

        List<Company> companies = Arrays.asList(company);

        Mockito.when(companyService.getAll()).thenReturn(companies);

        mockMvc.perform(get("/companies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is(company.getName())));
    }

    @Test
    void whenGetByName_thenReturnAirport() throws Exception {

        Company company = Company.builder().name("THY").build();

        Mockito.when(companyService.getByName("THY")).thenReturn(company);

        mockMvc.perform(get("/companies/name/THY")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
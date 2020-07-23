package com.finartz.study.repository;

import com.finartz.study.entity.Company;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CompanyRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void whenFindByName_thenReturnCompany() {

        // given
        Company company = Company.builder().name("THY").build();
        entityManager.persist(company);
        entityManager.flush();

        // when
        Company fetchedCompany = companyRepository.findByName("THY");

        // then
        assertEquals(fetchedCompany.getName(), company.getName());

    }
}
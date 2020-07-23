package com.finartz.study.controller;

import com.finartz.study.entity.Company;
import com.finartz.study.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getAll(){
        return ResponseEntity.ok(companyService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> get(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(companyService.get(id));
        } catch (EntityNotFoundException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Company Not Found", e);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Company> getByName(@PathVariable("name") String name){
        try{
            return ResponseEntity.ok(companyService.getByName(name));
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<Company> add(@RequestBody Company company){
        return ResponseEntity.ok(companyService.add(company));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        companyService.delete(id);
    }

    @PutMapping
    public ResponseEntity<Company> update(@RequestBody Company company){
        return ResponseEntity.ok(companyService.update(company));
    }
}

package com.ait.react.controller;

import com.ait.react.Repository.EmployeeRepository;
import com.ait.react.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/employee")
public class EmployController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping(value="/test")
    public List<Employee> test(){
        return  employeeRepository.findAll();
    }
    @GetMapping(value="/test/{id}")
    public Optional<Employee> test1(@PathVariable("id") int id){
        return  employeeRepository.findById(id);
    }
    @GetMapping(value="/test2")
    public List<Employee> test2(){
        return  employeeRepository.findByName("luan");
    }
    @GetMapping(value="/test3")
    public List<Employee> test3(){
        return  employeeRepository.findByNameLike("%luan%");
    }
    @PostMapping(value= "/create")
        public ResponseEntity<String> create(@RequestBody Employee data){
        try{
            System.out.println("Add");
            employeeRepository.save(data);
            return  new ResponseEntity<>("Save Success", HttpStatus.OK);
        } catch(Exception e){
            System.out.println(e);
            return  new ResponseEntity<>(""+e,HttpStatus.BAD_REQUEST);
        }
        }
        @GetMapping(value="/list")
    public Map<String,Object> list(){
        HashMap<String,Object> reponse = new HashMap<String,Object>();
        try{
            List<Employee>  employeeList;
            employeeList = e
        }
        }
}

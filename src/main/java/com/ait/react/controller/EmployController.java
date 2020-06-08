package com.ait.react.controller;

import com.ait.react.Repository.EmployeeRepository;
import com.ait.react.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/employee")
public class EmployController {
    @Autowired
    EmployeeRepository employeeRepository;

//    @GetMapping(value="/test")
//    public List<Employee> test(){
//        return  employeeRepository.findAll();
//    }
//    @GetMapping(value="/test/{id}")
//    public Optional<Employee> test1(@PathVariable("id") int id){
//        return  employeeRepository.findById(id);
//    }
//    @GetMapping(value="/test2")
//    public List<Employee> test2(){
//        return  employeeRepository.findByName("luan");
//    }
//    @GetMapping(value="/test3")
//    public List<Employee> test3(){
//        return  employeeRepository.findByNameLike("%luan%");
//    }

//    @PostMapping(value= "/create")
//        public ResponseEntity<String> create(@RequestBody Employee data){
//        try{
//            System.out.println("Add");
//            employeeRepository.save(data);
//            return  new ResponseEntity<>("Save Success", HttpStatus.OK);
//        } catch(Exception e){
//            System.out.println(e);
//            return  new ResponseEntity<>(""+e,HttpStatus.BAD_REQUEST);
//        }
//        }
@PostMapping(value="/create")
public Map<String, Object> create(@Valid @RequestBody Employee data){

    HashMap<String, Object> response = new HashMap<String, Object>();

    try {

        Optional<Employee> validEmail = employeeRepository.findByEmail(data.getEmail());

        if(validEmail.isPresent()) {
            response.put("message", "The email "+data.getEmail()+" is already registered ");
            response.put("success", false);
            return response;
        }
        else {
            employeeRepository.save(data);
            response.put("message", "Successful save");
            response.put("success", true);
            return response;
        }


    } catch (Exception e) {
        // TODO: handle exception
        response.put("message", e.getMessage());
        response.put("success",false);
        return response;
    }

}






    @GetMapping(value="/list")
        public Map<String, Object> list(){

            HashMap<String,Object> response = new HashMap<String,Object>();

            try {
                List<Employee> employeeList;
                employeeList = employeeRepository.findAll();
                response.put("message","Successful load");
                response.put("list",employeeList);
                response.put("success",true);
                return response;

            } catch (Exception e) {
                response.put("message",e.getMessage());
                response.put("success ",false);
                return response;
            }

        }
    @GetMapping(value = "get/{id}" )
    public Map<String, Object> data(@PathVariable("id") Integer id){

        HashMap<String,Object> response = new HashMap<String,Object>();

        try {

            Optional<Employee> employee = employeeRepository.findById(id);

            if (employee.isPresent()) {
                response.put("message","Successful load");
                response.put("data",employee);
                response.put("success",true);
                return response;
            }
            else {
                response.put("message","Not found data");
                response.put("data",null);
                response.put("success",false);
                return response;
            }

        } catch (Exception e){
            response.put("message",""+e.getMessage());
            response.put("success",false);
            return response;
        }
    }
    @PutMapping(value="/update/{id}")
    public Map<String, Object> update(@PathVariable("id") Integer id,
    @RequestBody Employee employee){
        HashMap<String,Object> reponse = new HashMap<String, Object>();
        try{
            employee.setId(id);
            employeeRepository.save(employee);
            reponse.put("message","Success update");
            reponse.put("success",true);
            return reponse;
        }catch (Exception e){
            reponse.put("message",e.getMessage());
            reponse.put("success",false);
            return  reponse;
        }
    }
    @DeleteMapping(value = "/delete/{id}")
    public Map<String, Object> delete(@PathVariable("id") Integer id){
        HashMap<String,Object> reponse = new HashMap<String, Object>();
        try{
            employeeRepository.deleteById(id);
            reponse.put("message","Success update");
            reponse.put("success",true);
            return reponse;

        } catch (Exception e){
            reponse.put("message",e.getMessage());
            reponse.put("success",false);
            return  reponse;
        }
    }
}

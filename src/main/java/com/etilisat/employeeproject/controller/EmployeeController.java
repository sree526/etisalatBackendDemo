package com.etilisat.employeeproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etilisat.employeeproject.entities.Employee;
import com.etilisat.employeeproject.exception.RecordNotFoundException;
import com.etilisat.employeeproject.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
    EmployeeService service;
 
    @GetMapping("/paginate")
    public ResponseEntity<Page<Employee>> getAllEmployees(@RequestParam int page,@RequestParam int size) {
        Page<Employee> list = service.getAllEmployees(page,size);
 
        return new ResponseEntity<Page<Employee>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployeesWithoutPagination() {
        List<Employee> list = service.getAllEmployeesWithoutPagination();
 
        return new ResponseEntity<List<Employee>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) 
                                                    throws RecordNotFoundException {
        Employee entity = service.getEmployeeById(id);
 
        return new ResponseEntity<Employee>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    @GetMapping("/managers/{id}")
    public ResponseEntity<List<Long>> getManagersById(@PathVariable("id") int id) 
                                                    throws RecordNotFoundException {
        List<Long> entity = service.getManagerById(id);
 
        return new ResponseEntity<List<Long>>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<Employee> createOrUpdateEmployee(@RequestBody Employee employee)
                                                    throws RecordNotFoundException {
    	System.out.println(employee);
        Employee updated = service.createOrUpdateEmployee(employee);
        return new ResponseEntity<Employee>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteEmployeeById(@PathVariable("id") Long id) 
                                                    throws RecordNotFoundException {
        service.deleteEmployeeById(id);
        return HttpStatus.OK;
    }
 
	
}

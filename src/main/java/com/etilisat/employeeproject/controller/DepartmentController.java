package com.etilisat.employeeproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.etilisat.employeeproject.entities.Department;
import com.etilisat.employeeproject.exception.RecordNotFoundException;
import com.etilisat.employeeproject.services.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
    DepartmentService service;
 
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> list = service.getAllDepartments();
 
        return new ResponseEntity<List<Department>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    @GetMapping("/paginate")
    public ResponseEntity<Page<Department>> getAllDepartments(@RequestParam int page,@RequestParam int size) {
        Page<Department> list = service.getAllDepartments(page,size);
 
        return new ResponseEntity<Page<Department>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
 
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long id) 
                                                    throws RecordNotFoundException {
        Department entity = service.getDepartmentId(id);
 
        return new ResponseEntity<Department>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<Department> createOrUpdateDepartment(@RequestBody Department department)
                                                    throws RecordNotFoundException {
        Department updated = service.createOrUpdateDepartment(department);
        return new ResponseEntity<Department>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteDepartmentById(@PathVariable("id") Long id) 
                                                    throws RecordNotFoundException {
        service.deleteDepartmentById(id);
        return HttpStatus.OK;
    }
 
}

package com.etilisat.employeeproject.services;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etilisat.employeeproject.entities.Department;
import com.etilisat.employeeproject.entities.Employee;
import com.etilisat.employeeproject.exception.RecordNotFoundException;
import com.etilisat.employeeproject.repositories.DepartmentRepository;
import com.etilisat.employeeproject.repositories.EmployeeRepository;

@Service
public class DepartmentService {

	@Autowired
    DepartmentRepository repository;
     
    public List<Department> getAllDepartments()
    {
    	//Pageable elements = PageRequest.of(start, noofelements);
    	List<Department> employeelist = repository.findAll();
         
        if(employeelist.size() > 0) {
          return employeelist;
        } else {
            return new ArrayList<Department>();
        }
    }
    public Page<Department> getAllDepartments(int page,int size)
    {
    	//Pageable elements = PageRequest.of(start, noofelements);
    	Page<Department> departmentList =  repository.findAll(PageRequest.of(page,size));
         
          return departmentList;

    }
     
    public Department getDepartmentId(Long id) throws RecordNotFoundException 
    {
        Optional<Department> department = repository.findById(id);
         
        if(department.isPresent()) {
            return department.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
     
    public Department createOrUpdateDepartment(Department entity) throws RecordNotFoundException 
    {
        Optional<Department> department = repository.findById(entity.getDepartmentId());
         
        if(department.isPresent()) 
        {
            Department newEntity = department.get();
            newEntity.setDepartmentName(entity.getDepartmentName());
            newEntity.setManagerId(entity.getManagerId());
            newEntity = repository.save(newEntity); 
            return newEntity;
        } else {
            entity = repository.save(entity);
             
            return entity;
        }
    } 
     
    public void deleteDepartmentById(Long id) throws RecordNotFoundException 
    {
        Optional<Department> department = repository.findById(id);
        try {
        if(department.isPresent()) 
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No department record exist for given id");
        }
        }catch(Exception e) {
        	throw new RecordNotFoundException("Cannot delete department as it's associated with employees");
        }
    } 
}

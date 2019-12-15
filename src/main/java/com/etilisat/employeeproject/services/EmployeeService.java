package com.etilisat.employeeproject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.etilisat.employeeproject.entities.Employee;
import com.etilisat.employeeproject.exception.RecordNotFoundException;
import com.etilisat.employeeproject.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
    EmployeeRepository repository;
     
    public Page<Employee> getAllEmployees(int page,int size)
    {
    	//Pageable elements = PageRequest.of(start, noofelements);
    	Page<Employee> employeelist =  repository.findAll(PageRequest.of(page,size));
         
          return employeelist;

    }
    public List<Employee> getAllEmployeesWithoutPagination()
    {
    	//Pageable elements = PageRequest.of(start, noofelements);
    	List<Employee> employeelist =  repository.findAll();
         
          return employeelist;

    }
     
    public Employee getEmployeeById(Long id) throws RecordNotFoundException 
    {
        Optional<Employee> employee = repository.findById(id);
         
        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
    public List<Long> getManagerById(int id) throws RecordNotFoundException{
    	List<Employee> employees = repository.findBymanagerId(id);
    	
    	return employees.stream()
                .map( e -> e.getEmployeeId() )
                .distinct()
                .collect(Collectors.toList()); 
    }
     
    public Employee createOrUpdateEmployee(Employee entity) throws RecordNotFoundException 
    {
        Optional<Employee> employee = repository.findById(entity.getEmployeeId());
         System.out.println(entity.getEmail() + "" +employee.isPresent());
        if(employee.isPresent()) 
        {
            Employee newEntity = employee.get();
            newEntity.setEmail(entity.getEmail());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
            newEntity.setDepartment(entity.getDepartment());
            newEntity.setEmployeeId(entity.getEmployeeId());
            newEntity.setHireDate(entity.getHireDate());
            newEntity.setManagerId(entity.getManagerId());
            newEntity.setPhoneNumber(entity.getPhoneNumber());
            newEntity.setSalary(entity.getSalary());
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
            entity = repository.save(entity);
             
            return entity;
        }
    } 
     
    public void deleteEmployeeById(Long id) throws RecordNotFoundException 
    {
        Optional<Employee> employee = repository.findById(id);
         System.out.println("Is employee present" + employee.isPresent());
        if(employee.isPresent()) 
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    } 
}

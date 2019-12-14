package com.etilisat.employeeproject.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.etilisat.employeeproject.entities.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	public Page<Employee> findAll(Pageable pageable);
	public List<Employee> findBymanagerId(int id);

}

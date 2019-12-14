package com.etilisat.employeeproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etilisat.employeeproject.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

}

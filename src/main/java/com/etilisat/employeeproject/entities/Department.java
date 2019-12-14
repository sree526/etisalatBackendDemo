package com.etilisat.employeeproject.entities;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="DEPARTMENTS")
public class Department {

	@Id
	@Column(name="department_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long departmentId;
	
	@Column(name="department_name")
	private String departmentName;
	
	@Column(name="manager_id")
	private int managerId;
	
//	@OneToMany(cascade = CascadeType.ALL,mappedBy="department",fetch = FetchType.LAZY)
//	private Set<Employee> employees;
	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

//	public Set<Employee> getEmployees() {
//		return employees;
//	}
//
//	public void setEmployees(Set<Employee> employees) {
//		this.employees = employees;
//	}
	
}

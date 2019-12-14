delete from departments;
INSERT INTO departments (department_id,department_name,manager_id) values
(1,'CSE',1),(2,'ECE',2);

delete from employees;
INSERT INTO 
	EMPLOYEES (employee_id,first_name, last_name,salary, email_id,department_id,hire_date,manager_id,phone_number) 
VALUES
  	(1,'Lokesh', 'Gupta',5000, 'howtodoinjava@gmail.com',1,'2019-10-12',0,'9791162795'),
  	(2,'John', 'Doe',1000, 'xyz@email.com',2,'2019-11-12',3,'1231231231'),
  	(3,'John1', 'Doe1',500, 'xyz1@email.com',1,'2019-11-13',1,'1231231232'),
	 (4,'John2', 'Doe2',12345, 'xyz2@email.com',1,'2019-11-14',1,'1231231233');
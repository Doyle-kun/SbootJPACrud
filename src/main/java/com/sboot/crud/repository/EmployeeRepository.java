package com.sboot.crud.repository;


import org.springframework.data.repository.CrudRepository;

import com.sboot.crud.model.Employee;


public interface EmployeeRepository extends CrudRepository<Employee, Long>{

}

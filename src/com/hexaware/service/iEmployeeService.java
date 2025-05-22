package com.hexaware.service;

import java.util.List;

import com.hexaware.entity.employee;
import com.hexaware.exception.EmployeeNotFoundException;
import com.hexaware.exception.InvalidInputException;

public interface iEmployeeService {
	employee getEmployeeById(int id) throws EmployeeNotFoundException,InvalidInputException;
	List<employee> getAllEmployees();
	boolean addEmployee(employee employee) throws InvalidInputException;
	boolean updateEmployee(employee employee) throws InvalidInputException;
	boolean removeEmployee(int id) throws InvalidInputException;
}

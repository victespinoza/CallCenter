package ar.com.callcenter.service;

import ar.com.callcenter.employ.Employee;

public interface EmployeeService {

	public Employee getInactiveEmployee();
	public void addEmployee(Employee employee);
	public boolean existInactiveEmployee();
	
}

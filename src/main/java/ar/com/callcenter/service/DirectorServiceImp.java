package ar.com.callcenter.service;

import ar.com.callcenter.employ.Employee;
import ar.com.callcenter.external.CallCenter;

public class DirectorServiceImp implements EmployeeService{

	public Employee getInactiveEmployee() {
		
		for(Employee director : CallCenter.getInstance().getDirectorList()){
			if(!director.isBusy()){
				return director;
			}
		}
		return null;
	}

	public void addEmployee(Employee employee) {
		
		CallCenter.getInstance().getDirectorList().add(employee);
	}

	public boolean existInactiveEmployee() {
		for(Employee director : CallCenter.getInstance().getDirectorList()){
			if(!director.isBusy()){
				return true;
			}
		}
		return false;
	}

}

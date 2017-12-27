package ar.com.callcenter.service;

import ar.com.callcenter.employ.Employee;
import ar.com.callcenter.external.CallCenter;

public class SupervisorServiceImp implements EmployeeService{

	public Employee getInactiveEmployee() {
		
		for(Employee supervisor : CallCenter.getInstance().getSupervisorList()){
			if(!supervisor.isBusy()){
				return supervisor;
			}
		}
		return null;
	}

	public void addEmployee(Employee employee) {
		
		CallCenter.getInstance().getSupervisorList().add(employee);
	}

	public boolean existInactiveEmployee() {

		for(Employee supervisor : CallCenter.getInstance().getSupervisorList()){
			if(!supervisor.isBusy()){
				return true;
			}
		}
		return false;
	}

}

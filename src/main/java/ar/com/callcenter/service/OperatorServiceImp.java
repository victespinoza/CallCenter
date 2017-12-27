package ar.com.callcenter.service;


import ar.com.callcenter.employ.Employee;
import ar.com.callcenter.external.CallCenter;

public class OperatorServiceImp implements EmployeeService{
	
	public Employee getInactiveEmployee() {
		for(Employee operator : CallCenter.getInstance().getOperatorList()){
			if(!operator.isBusy()){
				return operator;
			}
		}
		return null;
	}

	public void addEmployee(Employee employee) {
		CallCenter.getInstance().getOperatorList().add(employee);
		
	}

	public boolean existInactiveEmployee() {
		for(Employee operator : CallCenter.getInstance().getOperatorList()){
			if(!operator.isBusy()){
				return true;
			}
		}
		return false;
	}
	

}

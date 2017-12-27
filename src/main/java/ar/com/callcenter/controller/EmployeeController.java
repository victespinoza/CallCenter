package ar.com.callcenter.controller;


import ar.com.callcenter.employ.Employee;
import ar.com.callcenter.external.CallCenter;
import ar.com.callcenter.external.Connection;
import ar.com.callcenter.service.DirectorServiceImp;
import ar.com.callcenter.service.EmployeeService;
import ar.com.callcenter.service.OperatorServiceImp;
import ar.com.callcenter.service.SupervisorServiceImp;

public class EmployeeController {
	
	private EmployeeService operatorService=null;
	private EmployeeService supervisorService=null;
	private EmployeeService directorService=null;
	
	public EmployeeController(){
		if(operatorService==null){
			operatorService = new OperatorServiceImp();
		}
		if(supervisorService==null){
			supervisorService = new SupervisorServiceImp();
		}
		if(directorService==null){
			directorService = new DirectorServiceImp();
		}
	}
	
	public synchronized Employee getInactiveEmployee(){
		Employee inactiveEmployee = null;
		inactiveEmployee = operatorService.getInactiveEmployee();
		if(inactiveEmployee==null){
			inactiveEmployee = supervisorService.getInactiveEmployee();			
		}
		if(inactiveEmployee==null){
			inactiveEmployee = directorService.getInactiveEmployee();			
		}
		
		return inactiveEmployee;
	}
	
	public boolean existInactiveEmployee(){
		return operatorService.existInactiveEmployee() 
				|| supervisorService.existInactiveEmployee()
				|| directorService.existInactiveEmployee();
	}
	
	
	public Employee getEmployeeAndAsignCall(){
		Connection conn = CallCenter.getInstance().getCanal().getFreeConnection();
		CallCenter.getInstance().modifyBusyConnection(true,conn);
		Employee inactiveEmployee = getInactiveEmployee();
		CallCenter.getInstance().modifyBusyEmployee(true, inactiveEmployee);
		inactiveEmployee.setAsignedCall( CallCenter.getInstance().getCallList().removeLast());
		inactiveEmployee.setConn(conn);
		inactiveEmployee.setBusy(true);
		System.out.println("Empleado desocupado : "+inactiveEmployee.getNameEmployee());
		return inactiveEmployee;
	}
	
	
}

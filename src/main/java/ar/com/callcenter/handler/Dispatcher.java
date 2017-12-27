package ar.com.callcenter.handler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ar.com.callcenter.call.Call;
import ar.com.callcenter.controller.EmployeeController;
import ar.com.callcenter.employ.Employee;
import ar.com.callcenter.external.CallCenter;
import ar.com.callcenter.external.Canal;

public class Dispatcher {
	private EmployeeController employeeController;
	private Canal canal = null;
	
	public Dispatcher(){
		this.employeeController = new EmployeeController();
		canal = CallCenter.getInstance().getCanal();
	}
	
	
	public void dispatchCall(){
		int sizeInitialCallList = CallCenter.getInstance().getCallList().size();
		CallCenter.getInstance().setCallList(filterCall());
		List<Employee> freeEmployeeList = new ArrayList<Employee>();
		
		while(employeeController.existInactiveEmployee() && canal.areFreeConnection()){
			freeEmployeeList.add(employeeController.getEmployeeAndAsignCall());
		}
		if(freeEmployeeList.size() < sizeInitialCallList){
			System.out.println("Solo se van a poder atender "+freeEmployeeList.size()+" llamadas ");
		}
		System.out.println("Quedo pendiente "+CallCenter.getInstance().getCallList().size()+" llamada/s \n");
		for(Employee employee : freeEmployeeList){
			Thread thread = new Thread(employee);
			thread.start();
		}
	}
	
	public LinkedList<Call> filterCall(){
		LinkedList<Call> callIncoming = new LinkedList<Call>();
		int sizeCallList = CallCenter.getInstance().getCallList().size(); 
		System.out.println("Se reciben "+sizeCallList+" llamadas");
		if(sizeCallList > CallCenter.MAX_NUMBER_OF_CONNECTION){
			for(int i=0; i<CallCenter.MAX_NUMBER_OF_CONNECTION; i++){
				callIncoming.addFirst(CallCenter.getInstance().getCallList().removeFirst());
			}
			System.out.println("Se rechazaron "+(sizeCallList-CallCenter.MAX_NUMBER_OF_CONNECTION)+" llamadas");
		}
		else{
			callIncoming = CallCenter.getInstance().getCallList();
		}
		return callIncoming;
	}
	
	
}

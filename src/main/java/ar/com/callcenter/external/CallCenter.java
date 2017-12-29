package ar.com.callcenter.external;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ar.com.callcenter.call.Call;
import ar.com.callcenter.employ.Director;
import ar.com.callcenter.employ.Employee;
import ar.com.callcenter.employ.Operator;
import ar.com.callcenter.employ.Supervisor;

public class CallCenter {

	private List<Employee> operatorList;
	private List<Employee> supervisorList;
	private List<Employee> directorList;
	private Canal canal;
	private LinkedList<Call> callList;
	public static final int MAX_NUMBER_OF_CONNECTION = 10;
	
	private CallCenter(){
		operatorList = new ArrayList<Employee>();
		supervisorList = new ArrayList<Employee>();
		directorList = new ArrayList<Employee>();
		callList = new LinkedList<Call>();
		canal = new Canal(MAX_NUMBER_OF_CONNECTION);
	}        

    private static class Holder {
       private static final CallCenter INSTANCE = new CallCenter();
    }

    public static CallCenter getInstance() {
        return Holder.INSTANCE;
    }
	
	public void init(){
		operatorList = new ArrayList<Employee>();
	}

	public List<Employee> getOperatorList() {
		return operatorList;
	}

	public void setOperatorList(List<Employee> operatorList) {
		this.operatorList = operatorList;
	}
	
	
	public LinkedList<Call> getCallList() {
		return callList;
	}

	public void setCallList(LinkedList<Call> callList) {
		this.callList = callList;
	}

	public List<Employee> getSupervisorList() {
		return supervisorList;
	}

	public void setSupervisorList(List<Employee> supervisorList) {
		this.supervisorList = supervisorList;
	}

	public List<Employee> getDirectorList() {
		return directorList;
	}

	public void setDirectorList(List<Employee> directorList) {
		this.directorList = directorList;
	}

	public void addOperator(Operator operator){
		operatorList.add(operator);
	}
	
	public void addSupervisor(Supervisor supervisor){
		supervisorList.add(supervisor);
	}
	
	public void addDirector(Director director){
		directorList.add(director);
	}

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}
	
	public void modifyBusyConnection(boolean isBusy, Connection connection) {
		for(Connection conn : canal.getConnectionList()){
			if(conn.getId()==connection.getId()){
				conn.setBusy(isBusy);
			}
		}
	}
	
	public void modifyBusyEmployee(boolean isBusy, Employee employee){
		
		for(Employee operator : getEmployeeList(employee)){
			if(operator.getDni()==employee.getDni()){
				operator.setBusy(isBusy);
			}
		}
	}
	
	public List<Employee> getEmployeeList(Employee employee){
		if(employee instanceof Operator){
			return operatorList;
		}
		if(employee instanceof Supervisor){
			return supervisorList;
		}
		if(employee instanceof Director){
			return directorList;
		}
		return new ArrayList<Employee>();
	}
}

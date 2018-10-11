package ar.com.callcenter.employ;

import org.apache.log4j.Logger;

import ar.com.callcenter.call.Call;
import ar.com.callcenter.external.CallCenter;
import ar.com.callcenter.external.Connection;
import ar.com.callcenter.log4j.LoggerUtil;

public abstract class Employee extends Thread{

	protected String nameEmployee;
	protected Integer dni;
	protected boolean isBusy;
	protected Call asignedCall;
	protected Connection asignedconnection;
	protected CallCenter callCenter;
	private Logger accessLog;
	
	public Employee(String nameEmployee, Integer dni){
		this.nameEmployee = nameEmployee;
		this.dni = dni;
		this.isBusy = false;
		this.callCenter = CallCenter.getInstance();
		accessLog = Logger.getLogger(LoggerUtil.LOGGER_NAME);
	}
	
	public String getNameEmployee() {
		return nameEmployee;
	}
	public void setNameEmployee(String nameEmployee) {
		this.nameEmployee = nameEmployee;
	}
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	public synchronized boolean isBusy() {
		return isBusy;
	}
	public synchronized void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}	
	
	public Call getAsignedCall() {
		return asignedCall;
	}

	public synchronized void setAsignedCall(Call asignedCall) {
		this.asignedCall = asignedCall;
	}	
	public Connection getConn() {
		return asignedconnection;
	}

	public synchronized void setConn(Connection conn) {
		this.asignedconnection = conn;		
	}

	public synchronized void answerIncomingCall(){
		CallCenter.getInstance().modifyBusyEmployee(true,this);
		accessLog.info(nameEmployee+" - contestando llamada - duracion: "+asignedCall.getDurationInSeconds()+"s - conn: "+asignedconnection.getId());
//		System.out.println(nameEmployee+" - contestando llamada - duracion: "+asignedCall.getDurationInSeconds()+"s - conn: "+asignedconnection.getId());
		try {
			Thread.sleep(asignedCall.getDurationInSeconds()*1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		CallCenter.getInstance().modifyBusyEmployee(false,this);
		CallCenter.getInstance().modifyBusyConnection(false,asignedconnection);
		accessLog.info(nameEmployee+" - finalizando llamada");
//		System.out.println(nameEmployee+" - finalizando llamada");
	}
	
	public synchronized void run(){
		answerIncomingCall();
	}
}

package ar.com.callcenter.concurrence.test;

import java.util.LinkedList;

import ar.com.callcenter.call.Call;
import ar.com.callcenter.employ.Director;
import ar.com.callcenter.employ.Operator;
import ar.com.callcenter.employ.Supervisor;
import ar.com.callcenter.external.CallCenter;
import ar.com.callcenter.handler.Dispatcher;

public class DispatchTest {
	
	private static LinkedList<Call> callList;
	private static Dispatcher dispatcher;
	private static final int NUMBER_OF_CALL=5;
	
	private static CallCenter callCenter;

	public static void initTest(){
		callList = new LinkedList<Call>();
		callCenter = CallCenter.getInstance();
		dispatcher = new Dispatcher();
	}
	
	public static void addCall(){
		for(int i=0; i<NUMBER_OF_CALL; i++){
			callList.addFirst(new Call());
		}
		callCenter.setCallList(callList);
	}
	
	public static void addEmployee(){
		callCenter.addOperator(new Operator("Juan", 1));
		callCenter.addOperator(new Operator("Pedro", 2));
		callCenter.addOperator(new Operator("Lucas", 3));
		callCenter.addSupervisor(new Supervisor("Sup Pablo", 8));
		callCenter.addDirector(new Director("Dir Raul", 10));
	}
	
	public static void main(String args[]) {
		initTest();
		addCall();
		addEmployee();		
		dispatcher.dispatchCall();
	}
}

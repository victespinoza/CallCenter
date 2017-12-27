package ar.com.callcenter.junit.test;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.com.callcenter.call.Call;
import ar.com.callcenter.employ.Director;
import ar.com.callcenter.employ.Operator;
import ar.com.callcenter.employ.Supervisor;
import ar.com.callcenter.external.CallCenter;

public class CallCenterTest {

	private LinkedList<Call> callList;
	private CallCenter callCenter;
	private final int NUMBER_OF_CALL=5;
	
	@Before
	public void beforeTest(){
		callList = new LinkedList<Call>();
		callCenter = CallCenter.getInstance();
		callCenter.addOperator(new Operator("Op - Juan", 1));
		callCenter.addOperator(new Operator("Op - Pedro", 2));
		callCenter.addSupervisor(new Supervisor("Sup -Luis", 3));
		callCenter.addDirector(new Director("Dir - Julian", 4));
		for(int i=0; i<NUMBER_OF_CALL; i++){
			callList.addFirst(new Call());
		}
		callCenter.setCallList(callList);
	}
	
	@Test
	public void existOperatorTest(){
		Assert.assertTrue(!callCenter.getOperatorList().isEmpty());		
	}
	@Test
	public void existSupervisorTest(){
		Assert.assertTrue(!callCenter.getSupervisorList().isEmpty());		
	}
	@Test
	public void existDirectorTest(){
		Assert.assertTrue(!callCenter.getDirectorList().isEmpty());		
	}
	@Test
	public void sizeOperatorListTest(){
		Assert.assertEquals(callCenter.getOperatorList().size(), 2);
	}
	@Test
	public void sizeConnectionListTest(){
		Assert.assertEquals(callCenter.getCanal().getConnectionList().size(),CallCenter.MAX_NUMBER_OF_CONNECTION);
	}
	@Test
	public void sizeCallListTest(){
		Assert.assertEquals(callCenter.getCallList().size(),NUMBER_OF_CALL);
	}
}

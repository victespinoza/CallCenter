package ar.com.callcenter.external;

import java.util.ArrayList;
import java.util.List;

public class Canal {

	private List<Connection> connectionList;
	
	public Canal(int maxConnection){
		if(connectionList==null){
			connectionList = new ArrayList<Connection>();
		}
		for(int i=0; i<maxConnection; i++){
			connectionList.add(new Connection(i+1));
		}
	}

	public List<Connection> getConnectionList() {
		return connectionList;
	}
	
	public Connection getFreeConnection(){
		for(Connection connection : connectionList){
			if(!connection.isBusy()){				
				return connection;
			}
		}
		return null;
	}
	
	public boolean areFreeConnection(){
		for(Connection connection : connectionList){
			if(!connection.isBusy()){
				return true;
			}
		}
		return false;
	}

}

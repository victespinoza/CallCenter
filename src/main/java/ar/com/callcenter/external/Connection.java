package ar.com.callcenter.external;

public class Connection {

	private boolean busy;
	private int id;
	
	public Connection(Integer id){
		this.busy = false;
		this.id = id;
	}

	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}

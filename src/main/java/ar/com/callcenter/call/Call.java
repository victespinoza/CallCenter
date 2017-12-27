package ar.com.callcenter.call;

import java.sql.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Call {
	volatile int duration;
	volatile Date start;
	volatile Date end;
	volatile  int MIN_DURATION = 5; //time in seconds
	volatile  int MAX_DURATION = 10;
	
	public Call(){
		this.duration = ThreadLocalRandom.current().nextInt(MIN_DURATION, MAX_DURATION + 1);
	}
	
	public int getDurationInSeconds(){
		return this.duration;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
}

package pti.sb_flight_mvc.dto;

import java.util.ArrayList;
import java.util.List;

public class RouteDto {
	
	private List<String> route;
	private List<Long> waitingTimes;
	
	
	public RouteDto() {
		this.route = new ArrayList<>();
		this.waitingTimes = new ArrayList<Long>();
	}

	public List<String> getRoute() {
		return route;
	}

	public void setRoute(List<String> route) {
		this.route = route;
	}

	public List<Long> getWaitingTimes() {
		return waitingTimes;
	}

	public void setWaitingTimes(List<Long> waitingTimes) {
		this.waitingTimes = waitingTimes;
	}

	
	
	
	
	
	
}

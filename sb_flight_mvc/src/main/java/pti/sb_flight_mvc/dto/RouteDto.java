package pti.sb_flight_mvc.dto;

import java.util.ArrayList;
import java.util.List;

public class RouteDto {
	
	private List<String> route;
	
	
	public RouteDto() {
		this.route = new ArrayList<>();
		
	}

	public List<String> getRoute() {
		return route;
	}

	public void setRoute(List<String> route) {
		this.route = route;
	}

	
}

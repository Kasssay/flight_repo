package pti.sb_flight_mvc.dto;

import java.util.List;

public class DtoPack {
	
	private boolean flightTimeVisibility;
	
	private List<FlightDto> flights;

	public DtoPack(boolean flightTimeVisibility, List<FlightDto> flights) {
		super();
		this.flightTimeVisibility = flightTimeVisibility;
		this.flights = flights;
	}

	public boolean isFlightTimeVisibility() {
		return flightTimeVisibility;
	}

	public void setFlightTimeVisibility(boolean flightTimeVisibility) {
		this.flightTimeVisibility = flightTimeVisibility;
	}

	public List<FlightDto> getFlights() {
		return flights;
	}

	public void setFlights(List<FlightDto> flights) {
		this.flights = flights;
	}
	
	
	
}

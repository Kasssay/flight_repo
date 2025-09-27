package pti.sb_flight_mvc.dto;

import java.time.LocalDateTime;

public class FlightDto {
	
	private String depCity;
	private LocalDateTime depTime;
	private String arrCity;
	private LocalDateTime arrTime;
	private String flightNumber;
	private String captain;
	
	
	public FlightDto(String depCity, LocalDateTime depTime, String arrCity, LocalDateTime arrTime, String flightNumber,
			String captain) {
		super();
		this.depCity = depCity;
		this.depTime = depTime;
		this.arrCity = arrCity;
		this.arrTime = arrTime;
		this.flightNumber = flightNumber;
		this.captain = captain;
	}


	public String getDepCity() {
		return depCity;
	}


	public void setDepCity(String depCity) {
		this.depCity = depCity;
	}


	public LocalDateTime getDepTime() {
		return depTime;
	}


	public void setDepTime(LocalDateTime depTime) {
		this.depTime = depTime;
	}


	public String getArrCity() {
		return arrCity;
	}


	public void setArrCity(String arrCity) {
		this.arrCity = arrCity;
	}


	public LocalDateTime getArrTime() {
		return arrTime;
	}


	public void setArrTime(LocalDateTime arrTime) {
		this.arrTime = arrTime;
	}


	public String getFlightNumber() {
		return flightNumber;
	}


	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}


	public String getCaptain() {
		return captain;
	}


	public void setCaptain(String captain) {
		this.captain = captain;
	}
	
	
	
	
	
	
}

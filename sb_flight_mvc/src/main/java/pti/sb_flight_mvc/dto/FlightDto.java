package pti.sb_flight_mvc.dto;

import java.time.Duration;
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
	
	public long getDuration() {
		
		long result = Duration.between(depTime, arrTime).toMinutes();
		
		return result;
		
	}
	
	@Override
	public String toString() {
		return "FlightDto [depCity=" + depCity + ", depTime=" + depTime + ", arrCity=" + arrCity + ", arrTime="
				+ arrTime + ", flightNumber=" + flightNumber + ", captain=" + captain + "]";
	}
	
	
	
	
}

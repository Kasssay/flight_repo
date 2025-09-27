package pti.sb_flight_mvc.model;

import java.time.LocalDateTime;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(value="flights")
public class Flight {
	
	@Column(value="departure_city")
	private String depCity;
	
	@Column(value="departure_time")
	private LocalDateTime depTime;
	
	@Column(value="arrival_city")
	private String arrCity;
	
	@Column(value="arrival_time")
	private LocalDateTime arrTime;
	
	@Column(value="flight_number")
	private String flightNumber;
	
	@Column(value="captain")
	private String captain;

	
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

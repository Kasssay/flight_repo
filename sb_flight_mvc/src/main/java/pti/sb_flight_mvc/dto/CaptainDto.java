package pti.sb_flight_mvc.dto;



public class CaptainDto {
	
	private String captain;
	private long flighttime;
	
	
	public CaptainDto(String captain, long flighttime) {
		super();
		this.captain = captain;
		this.flighttime = flighttime;
	}


	public String getCaptain() {
		return captain;
	}


	public void setCaptain(String captain) {
		this.captain = captain;
	}


	public long getFlighttime() {
		return flighttime;
	}


	public void setFlighttime(long flighttime) {
		this.flighttime = flighttime;
	}
	
	public void addFlightTime(long flighttime) {
		this.flighttime += flighttime;
	}
	
	
	@Override
	public String toString() {
		return "CaptainDto [captain=" + captain + ", flighttime=" + flighttime + "]";
	}
	
	
	


}

	


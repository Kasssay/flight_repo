package pti.sb_flight_mvc.service;



import java.time.Duration;
import java.util.ArrayList;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import pti.sb_flight_mvc.db.FlightRepository;
import pti.sb_flight_mvc.dto.CaptainDto;
import pti.sb_flight_mvc.dto.DtoPack;
import pti.sb_flight_mvc.dto.FlightDto;
import pti.sb_flight_mvc.model.Flight;

@Service
public class AppService {
	
	private FlightRepository flightRepository;
	

	@Autowired
	public AppService(FlightRepository flightRepository) {
		super();
		this.flightRepository = flightRepository;
	}

	//ex 1.2
	
	public DtoPack getDtoPack(boolean order, boolean flightTimeVisibility) {
		
		DtoPack dtoPack = new DtoPack(flightTimeVisibility, getOrderedFlightDtoList(order));
		
		return dtoPack;
	
	}
	
	//ex 2.1
	
	public List<CaptainDto> getCaptainDtos(){
		
		
		
		List<CaptainDto> captainDtos = new ArrayList<>();
		
		List<FlightDto> flightDtos = getOrderedFlightDtoList(null);
		
		for(int index = 0; index < flightDtos.size(); index++) {
			
			FlightDto flightDto = flightDtos.get(index);
		
			CaptainDto captainDto = new CaptainDto(
					flightDto.getCaptain(),
					flightDto.getDuration());
			
				captainDtos.add(captainDto);
			
		}	
		
		for(int outerIndex = 0; outerIndex < captainDtos.size()-1 ; outerIndex++) {
			for(int innenIndex = outerIndex + 1; innenIndex < captainDtos.size(); innenIndex++) {
				
				if(captainDtos.get(outerIndex).getCaptain().equals(captainDtos.get(innenIndex).getCaptain())){
					System.out.println("if");
					captainDtos.get(outerIndex).addFlightTime(captainDtos.get(innenIndex).getFlighttime());
					captainDtos.remove(innenIndex);
				}
			}
		}
		

		return captainDtos;	
	}
	
	
	//-------------------private metódusok
	
	
	//ex1.1 -1.2
		private List<FlightDto> getOrderedFlightDtoList(Boolean order){
			
			List<FlightDto> flightDtos = new ArrayList<>();
			
			Iterable<Flight> flightDtosIterable = flightRepository.findAll();
			
			for(Flight flight : flightDtosIterable) {
				
				FlightDto flightDto = new FlightDto(
						flight.getDepCity(),
						flight.getDepTime(),
						flight.getArrCity(),
						flight.getArrTime(),
						flight.getFlightNumber(),
						flight.getCaptain());
				
				flightDtos.add(flightDto);
			}
					
			if(order != null && order != false){
				
				orderingByDepTime(flightDtos);
				
			}
				
			return flightDtos;			
		}

		
	//Rendezés indulási idő szerint a flightDto-kat
	private List<FlightDto> orderingByDepTime(List<FlightDto> flightDtos){
		
		for(int outerIndex = 0; outerIndex < flightDtos.size()-1; outerIndex++) {
				
			for(int innenIndex = outerIndex + 1; innenIndex < flightDtos.size(); innenIndex++) {
					
				if(flightDtos.get(innenIndex).getDepTime().isBefore(flightDtos.get(outerIndex).getDepTime())) {
					
					FlightDto save = flightDtos.get(outerIndex);
				    flightDtos.set(outerIndex, flightDtos.get(innenIndex));
				    flightDtos.set(innenIndex, save);
									
					innenIndex--;
				}			
			}
		}
			
		return flightDtos;	
	}
	
	
	
	
	
}

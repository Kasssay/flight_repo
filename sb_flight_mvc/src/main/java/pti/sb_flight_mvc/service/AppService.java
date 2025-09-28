package pti.sb_flight_mvc.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import pti.sb_flight_mvc.db.FlightRepository;
import pti.sb_flight_mvc.dto.CaptainDto;
import pti.sb_flight_mvc.dto.DtoPack;
import pti.sb_flight_mvc.dto.FlightDto;
import pti.sb_flight_mvc.dto.RouteDto;
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
					captainDtos.get(outerIndex).addFlightTime(captainDtos.get(innenIndex).getFlighttime());
					captainDtos.remove(innenIndex);
				}
			}
		}
		
		return captainDtos;	
	}
	
	//ex 3.1
	public List<RouteDto> CaptainsRoutes(){
		
		// -vissza adja  az összes pilotának az utvonalát.
			//a. - összehasonlÍsuk a flightDto-kat és -a1. az egyik érkezési városa a másik indulási városa.
													//-a2.  és időben az első  leszálási ideje hamarabb volt mint a másadik érkezési ideje.
													//-a3. és ugzanaz a captain-nel van.
													//-a4. ha miden feltétel teljesül hozza adjuk  városokat az utvonal listához.
		//b    b1 .- ha már van utvonal csak az érkezési várost kell hozzaadni
		
		List<RouteDto> routes = new ArrayList<>();
		
		
		List<FlightDto> flightDtos = getOrderedFlightDtoList(null);
		
		for(int outerIndex = 0; outerIndex < flightDtos.size(); outerIndex++) {
					
			RouteDto routeDto = new RouteDto();
			FlightDto flightDtoOuter = flightDtos.get(outerIndex);
			
			for(int innenIndex = 0; innenIndex < flightDtos.size(); innenIndex++) {
				
				FlightDto flightDtoInnen = flightDtos.get(innenIndex);
				
				//a3.
				if(flightDtoInnen.getCaptain().equals(flightDtoOuter.getCaptain())) {
					
					//a2.
					if(flightDtoOuter.getArrTime().isBefore(flightDtoInnen.getDepTime())){
										
						//a1.
						if(flightDtoOuter.getArrCity().equals(flightDtoInnen.getDepCity())) {
					  
							//b1.
							if(routeDto.getRoute().size() < 1) {
								routeDto.getRoute().add(flightDtoOuter.getCaptain());
								routeDto.getRoute().add(flightDtoOuter.getDepCity());
								routeDto.getRoute().add(flightDtoOuter.getArrCity());
								
								innenIndex = 0;   //mert újra kell vizsgálni az egész flightDto-t mert változott az utolsó elemje a listánknak.
							
							
							// Ha a vizsgálandó járat start városa megeggyezik az utvonal utolsó városával akkor hozza adódik az utvonalhoz.
								if(flightDtoInnen.getDepCity().equals(routeDto.getRoute().get(routeDto.getRoute().size() -1))) {
								
									routeDto.getRoute().add(flightDtoInnen.getArrCity());
									
									
									innenIndex = 0;
								}
							}		
						}
					
						//ha  már van elemje a  listánknak akkor annak az utolsó elemit hasónlitsuk össze benti flightDto-val.
						else if(routeDto.getRoute().size() > 0 && flightDtoInnen.getDepCity().equals(routeDto.getRoute().get(routeDto.getRoute().size() -1))){
							routeDto.getRoute().add(flightDtoInnen.getArrCity());
							innenIndex = 0;
						}
					}
				}			
			}
			routes.add(routeDto);
		}
			
		return routes;
	}
	
	
	//ex 3.2
	
	//a. bejárjuk a járatokat és elmentünk minden utvonalat.
	//b. majd kiválasszuk azt az utvonalat amelyikre szükségünk van.
	
	
	public RouteDto routePlanner(String depCity , String arrCity) {
		
		RouteDto result = new RouteDto();
		
		List<RouteDto> routeDtos = new ArrayList<>();
		
		
		//a.
		List<FlightDto> flightDtos = getOrderedFlightDtoList(null);
		
		for(int outerIndex = 0; outerIndex < flightDtos.size(); outerIndex++) {
			 
			FlightDto flightDtoOuter = flightDtos.get(outerIndex);
			RouteDto actualRouteDto = new RouteDto();
			
			for(int innenIndex = 0; innenIndex < flightDtos.size(); innenIndex++) {
							
					FlightDto flightDtoInnen = flightDtos.get(innenIndex);
					
					//ha az érkezési idő az indulási idő után van
					if(flightDtoOuter.getDepTime().isBefore(flightDtoInnen.getArrTime())) {
						
						//ha az érkezési város egyenlő egy másik flightDto indulási városával
						if(flightDtoOuter.getArrCity().equals(flightDtoInnen.getDepCity())) {
							
							actualRouteDto.getRoute().add(flightDtoOuter.getDepCity());
							actualRouteDto.getRoute().add(flightDtoOuter.getArrCity());
							actualRouteDto.getRoute().add(flightDtoInnen.getArrCity());
							
							actualRouteDto.getWaitingTimes().add(getDuration(flightDtoOuter.getArrTime(), flightDtoInnen.getDepTime()));
	
	
							}																												//az utolsó elem a listában
						else if(actualRouteDto.getRoute().size() > 0 && flightDtoInnen.getDepCity().equals(actualRouteDto.getRoute().get(actualRouteDto.getRoute().size() -1))) {
							
							actualRouteDto.getRoute().add(flightDtoInnen.getArrCity());
							actualRouteDto.getWaitingTimes().add(getDuration(flightDtoOuter.getArrTime(), flightDtoInnen.getDepTime()));
						
						}
						
						//ha körbe értünk az utvonallal.
						if(actualRouteDto.getRoute().size() > 0 && flightDtoInnen.getArrCity().equals(actualRouteDto.getRoute().get(0))) {
							
							break;
						}
					
					}	
			
			}		
			routeDtos.add(actualRouteDto);
		}
		
		//ellenzőrzés
		for(RouteDto routeDto : routeDtos) {
			System.out.println("--------");
			for(String city : routeDto.getRoute()) {
				System.out.println(city);
			}
		}
		//-------
		
		//b.
		
		for(RouteDto routeDto : routeDtos) {
			
			if(routeDto.getRoute().contains(depCity) && routeDto.getRoute().contains(arrCity)) {
				
				result = routeDto;
			}
		}
		
		
		return result;
	
	}
	
	
	

	//-------------------private metódusok
		
	//ex1.1 -1.2
	
		private long getDuration(LocalDateTime dep , LocalDateTime arr) {
			
			return Duration.between(arr,dep).toMinutes(); 
			
		}
	
	
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

package pti.sb_flight_mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.sb_flight_mvc.dto.CaptainDto;
import pti.sb_flight_mvc.dto.DtoPack;
import pti.sb_flight_mvc.dto.RouteDto;
import pti.sb_flight_mvc.service.AppService;

@Controller
public class AppController {

	private AppService service;

	@Autowired
	public AppController(AppService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/flights")
	public String getFlights(Model model) {
		
		DtoPack dtoPack = service.getDtoPack(true, false);
		model.addAttribute("dtoPack", dtoPack);
		
		return "flights.html";
		
	}
	
	@GetMapping("/flightsWithTime")
	public String getFlightsWithTimeDuration(Model model) {
		
		DtoPack dtoPack = service.getDtoPack(false, true);
		model.addAttribute("dtoPack", dtoPack);
		
		return "flights.html";
	}
	
	@GetMapping("/captains")
	public String getCaptains(Model model) {
		
		List<CaptainDto> captainDtos = service.getCaptainDtos();
		model.addAttribute("captainDtos",captainDtos);
			
		return "captains.html";
		
	}
	
	//ex 3.1
	@GetMapping("/routes")
	public String getRoutes(Model model) {
		
		List<RouteDto> routesDtos = service.CaptainsRoutes();
		model.addAttribute("routes", routesDtos);	
		
		return "routes.html";
	}
	
	//ex 3.2
	@GetMapping("/plan")
	public String getPlanForm() {
		
		return "plan.html";
	}
	
	
	@PostMapping("/plans")
	public String getPlan(
			Model model,
			@RequestParam("depCity") String depCity,
			@RequestParam("arrCity") String arrCity) {

		
		RouteDto routeDto = service.routePlanner(depCity,arrCity);
		model.addAttribute("routeDto", routeDto);
		
		
		return "plan.html";
	}
	
	
	
	
	
}

package pti.sb_flight_mvc.db;

import org.springframework.data.repository.CrudRepository;

import pti.sb_flight_mvc.model.Flight;

public interface FlightRepository extends CrudRepository<Flight,Integer>{

}

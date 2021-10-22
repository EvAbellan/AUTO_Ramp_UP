package virtual.busisness.cars.boundary;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("Cars")
public class CarsResouce {
	
	@Inject
	CarsService cs;
	
	
	@GET
	public List<String> getAllCars(){
		return cs.getAllCars();
	}

}

package virtual.busisness.cars.boundary;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class CarsService {

	public List<String> getAllCars(){
		return  new LinkedList<String>();
	}
}

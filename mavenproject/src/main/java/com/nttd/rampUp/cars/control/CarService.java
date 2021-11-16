package com.nttd.rampUp.cars.control;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.nttd.rampUp.LogInterceptor;
import com.nttd.rampUp.LogStarter;
import com.nttd.rampUp.cars.entity.Car;

/**
 * @author pabellap
 *
 */
@Stateless
@Interceptors(LogInterceptor.class)
public class CarService {

	public  Logger LOGGER = Logger.getLogger(LogStarter.class);
	
	
	
	
	@PersistenceContext(name= "postgresql")
	EntityManager entityManager;

	
	/**Get all cars from database
	 * @return List<Car> List of all cars in the database
	 */
	public List<Car> getAllCars(){
		
		
		List<Car> carList = this.entityManager.createNamedQuery("findAll", Car.class).getResultList();
		
		
		return carList;
	}
	
	/** Get a car by id
	 *
	 * @param id primary key of that car
	 * @return Car with such id or an empty car if the entity does not exist
	 */
	public Car getCar(long id) {
		LOGGER.info(String.format("Entring getCar(id= %d: long)",id));
		Car car = entityManager.find(Car.class, id);
		if(car != null) {
			LOGGER.info(String.format("Exit getCar(id= %d:long) return car --> brand= %s:String, country= %s:String", car.getId(),car.getBrand(),car.getCountry()));
			return car;
		}
		LOGGER.info(String.format("Exit getCar(id= %d:long) return new Car() --> car not found", id));
		return new Car();
			
	}

	
	/**Creation of a car
	 * @param car the car to create
	 * @return that car or an empty car if the primary key already exist
	 */
	public Car createCar(Car car) {
		LOGGER.info(String.format("Entring createCar(Car car) -> Id= %d:long, Brand= %s:String, Country= %s:String", car.getId(),car.getBrand(),car.getCountry()));
		if(car.getId() > 0) {
				
			if (entityManager.find(Car.class, car.getId()) == null) {
				
				entityManager.persist(car);
				LOGGER.info("Exit createCar(car=car:Car) return car --> created car");
				return car;
			}
		}
		LOGGER.info(String.format("Exit createCar(car=car:Car) return new Car() --> the car already exist, id= %d:long", car.getId()));
		return new Car();
	}
	
	/**Update an existed car
	 * @param car car with the news parameters
	 * @return car the car uptaded or an empty car if the car does not
	 * exist in the database
	 */
	public Car updateCar(Car car) {
		
		LOGGER.info(String.format("Entring updateCar(car = car:Car) -> Id: %d, Brand: %s, Country: %s", car.getId(),car.getBrand(),car.getCountry()));
		if(entityManager.find(Car.class, car.getId()) != null) {
			
			entityManager.merge(car);
			LOGGER.info("Exit updateCar(car= car:Car) return car --> updated car");
			return car;
		}
		
		LOGGER.info(String.format("Exit updateCar(car= car:Car) return new Car() --> id= %d:long not found", car.getId()));
		return new Car();
	}
	
	/**Delete an existed car
	 * @param id the primary key of that car
	 * @return car the car deleted 
	 */
	public Car deleteCar(long id) {
		
		LOGGER.info(String.format("Entring deleteCar(id= %d:long)", id));

		Car car = entityManager.find(Car.class, id);
		
		if (car != null) {
			entityManager.remove(car);
			LOGGER.info(String.format("Exit deleteCar(id= %d:long) return car --> car deleted", id));
			return car;
		}
		LOGGER.info(String.format("Exit deleteCar(id= %d:long) return new Car() --> id not found", id));
		return new Car();
	}
}

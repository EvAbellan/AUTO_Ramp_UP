package com.nttd.rampUp.cars.control;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.nttd.rampUp.cars.entity.Car;

/**
 * @author pabellap
 *
 */
@Stateless
public class CarService {

	@PersistenceContext(name= "postgresql")
	EntityManager entityManager;

	
	/**
	 * @return List<Car> 
	 */
	public List<Car> getAllCars(){
		
		return  this.entityManager.createNamedQuery("findAll", Car.class).getResultList();
	}
	
	/** 
	 * <p> Get the Car </p>
	 * @param id 
	 * @return 
	 */
	public Car getCar(long id) {
		
		return entityManager.find(Car.class, id);
	}

	
	/**
	 * @param car
	 * @return
	 */
	public Car createCar(Car car) {
		
		if (entityManager.find(car.getClass(), car.getId()) == null) {
			
			entityManager.persist(car);
		}
		
		return car;
	}
	
	/**
	 * @param car
	 * @return car
	 */
	public Car updateCar(Car car) {
		
		
		if(entityManager.find(car.getClass(), car.getId()) != null) {
			
			entityManager.merge(car);
		}
		
		
		return car;
	}
	
	/**
	 * @param id
	 * @return
	 */
	public Car deleteCar(long id) {
		
		
		Car car = entityManager.find(Car.class, id);
		
		if (car != null) {
			entityManager.remove(car);
		}
		
		
		return car;
	}
}

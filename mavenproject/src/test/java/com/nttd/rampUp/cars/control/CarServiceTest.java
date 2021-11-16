package com.nttd.rampUp.cars.control;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.nttd.rampUp.cars.entity.Car;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

	@InjectMocks
	CarService carService;
	
	@Mock
	EntityManager entityManager;
	
	@Mock
	TypedQuery<Car> typedQuery;
	
	
	
	
	@Test
	public void testGetAllCars() {
		//given 
		List<Car> allCars = new LinkedList<Car>();
		Car car1 = new Car(); car1.setId(1);
		Car car2 = new Car(); car2.setId(2);
		Car car3 = new Car(); car3.setId(3);
		allCars.add(car1); allCars.add(car2); allCars.add(car3);
		
		//when
		when(entityManager.createNamedQuery("findAll",Car.class)).thenReturn(typedQuery);
		when(typedQuery.getResultList()).thenReturn(allCars);
		List<Car> result = carService.getAllCars();
	
		
		//then
		assertEquals(allCars, result);
		
	}

	@Test
	public void testGetCarByValidID() {
		//given
		long id = 1;
		Car car = new Car(); 
		car.setId(id);
		
		//when
		when(entityManager.find(Car.class, id)).thenReturn(car);
		Car carResult = carService.getCar(id);
		
		//then
		assertEquals(car.getId(), carResult.getId());
	}
	
	@Test
	public void testGetCarByWrongID() {
		//given
		long id = 1;
		Car car = new Car();
		//when
		when(entityManager.find(Car.class, id)).thenReturn(car);
		Car carResult = carService.getCar(id);
		
		//then
		assertEquals(car.getId(), carResult.getId());
	}

	@Test
	public void testCreateNewCar() {
		//given
		Car car = new Car(1,"BMW", "Españita");
		
		//when
		when(entityManager.find(car.getClass(), car.getId())).thenReturn(null);
		Car carResult = carService.createCar(car);
		
		//then
		assertEquals(car.getId(), carResult.getId());
		assertEquals(car.getBrand(), carResult.getBrand());
		assertEquals(car.getCountry(), carResult.getCountry());
		
		
	}
	
	@Test
	public void testCreateExistingCar() {
		//given
		Car car = new Car(1,"BMW", "Españita");
		//when
		when(entityManager.find(Car.class, car.getId())).thenReturn(new Car());
		Car carReseult = carService.createCar(car);
		
		//then
		assertNotEquals(car.getId(), carReseult.getId());
		
		
		
	}

	@Test
	public void testUpdateExistingCar() {
		//given
		Car car = new Car(1,"BMW", "Españita");
		Car car1 = new Car(1, "BMW", "Germany");
		
		//when
		when(entityManager.find(Car.class, car.getId())).thenReturn(car1);
		Car carResult = carService.updateCar(car1);
		
		//then
		assertEquals(car1.getId(), carResult.getId());
		
	}

	@Test
	public void testUpdateNonExistingCar() {
		//given
		Car car = new Car(1, "BMW", "Germany");
		
		//when
		when(entityManager.find(Car.class, car.getId())).thenReturn(null);
		Car carResult = carService.updateCar(car);
		
		//then
		assertNotEquals(car.getId(), carResult.getId());
		
	}
	
	
	@Test
	public void testDeleteExistingCar() {
		//given
		long id = 1;
		Car car = new Car();
		car.setId(id);
		
		//when
		when(entityManager.find(Car.class, id)).thenReturn(car);
		Car removedCar = carService.deleteCar(id);
		
		//then
		assertEquals(car, removedCar);
	}

	@Test
	public void testDeleteNonExistingCar() {
		//given
		long id = 1;
		Car car = new Car();
		car.setId(id);
		
		//when
		when(entityManager.find(Car.class, id)).thenReturn(null);
		Car removedCar = carService.deleteCar(id);
		
		//then
		assertEquals(removedCar.getId(), 0);
	}
}

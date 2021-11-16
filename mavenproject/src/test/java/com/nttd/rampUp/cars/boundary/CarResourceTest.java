package com.nttd.rampUp.cars.boundary;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nttd.rampUp.cars.control.CarService;
import com.nttd.rampUp.cars.entity.Car;


@RunWith(MockitoJUnitRunner.class)
public class CarResourceTest {

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
		Car car1 = new Car();
		Car car2 = new Car();
		Car car3 = new Car();
		car1.setId(1);
		car2.setId(2);
		car3.setId(3);
		allCars.add(car1);
		allCars.add(car2);
		allCars.add(car3);
		
		//when
		when(entityManager.createNamedQuery("findAll",Car.class)).thenReturn(typedQuery);
		when(typedQuery.getResultList()).thenReturn(allCars);
		Response response = Response.ok(carService.getAllCars()).build();
		
		//then
		assertEquals(response.getEntity(),allCars);
		
	}

	@Test
	public void testGetExistingCar() {
		//given 
		long id = 1;
		Car car = new Car();
		car.setId(id);
		//when
		when(carService.getCar(id)).thenReturn(car);
		Response response = Response.ok(carService.getCar(id)).build();
		Car result = (Car) response.getEntity();
		//then
		assertEquals(result.getId(), car.getId());
	}

	@Test
	public void testGetNonExistingCar() {
		//given 
		long id = 1;
		Car car = new Car();
		car.setId(id);
		//when
		when(carService.getCar(id)).thenReturn(null);
		Response response = Response.ok(carService.getCar(id)).build();
		Car result = (Car) response.getEntity();
		
		//then
		assertNotEquals(result.getId(), car.getId());
	}

//	@Test
//	public void testCreateNonExistingCar() {
//		//given
//		Car car = new Car(1,"BMW","Españita");
//		
//		//when
//		doNothing().when(entityManager).persist(car);
//		when(entityManager.find(Car.class, car.getId())).thenReturn(null);
//		when(carService.createCar(car)).thenReturn(car);
//		Response response = Response.ok(carService.createCar(car)).build();
//		Car result = (Car) response.getEntity();
//	
//		//then
//		assertEquals(result.getId(), car.getId());
//		assertEquals(result.getBrand(), car.getBrand());
//		assertEquals(result.getCountry(), car.getCountry());
//	}

	@Test
	public void testCreateExistingCar() {
		//given
		Car car = new Car(1,"BMW","Españita");
		
		//when
		doNothing().when(entityManager).persist(car);
		when(entityManager.find(Car.class, car.getId())).thenReturn(car);
		when(carService.createCar(car)).thenReturn(new Car());
		Response response = Response.ok(carService.createCar(car)).build();
		Car result = (Car) response.getEntity();
	
		//then
		assertNotEquals(result.getId(), car.getId());
	}
	@Test
	public void testUpdateExistingCar() {
		//given
		Car car = new Car(1,"BMW","Germany");
		
		//when
		when(carService.updateCar(car)).thenReturn(car);
		Response response = Response.ok(carService.updateCar(car)).build();
		Car result = (Car) response.getEntity();
	
		//then
		assertEquals(result.getId(), car.getId());
		assertEquals(result.getBrand(), car.getBrand());
		assertEquals(result.getCountry(), car.getCountry());
		
	}

//	@Test
//	public void testUpdateNonExistingCar() {
//		//given
//		Car car = new Car(1,"BMW","Germany");
//		
//		//when
//		when(entityManager.find(Car.class, car.getId())).thenReturn(null);
//		when(carService.updateCar(car)).thenReturn(new Car());
//		Response response = Response.ok(carService.updateCar(car)).build();
//		Car result = (Car) response.getEntity();
//		
//		//then
//		assertNotEquals(car.getId(), result.getId());
//		
//	}
	@Test
	public void testDeleteExistingCar() {
		//given
		Car car = new Car(1,"BMW", "Españita");
		
		//when
		when(carService.deleteCar(car.getId())).thenReturn(car);
		Response response = Response.ok(carService.deleteCar(car.getId())).build();
		Car result = (Car) response.getEntity();
		
		//then
		assertEquals(result.getId(), car.getId());
		assertEquals(result.getBrand(), car.getBrand());
		assertEquals(result.getCountry(), car.getCountry());
	}

	@Test
	public void testDeleteNonExistingCar() {
		//given
		Car car = new Car();
		car.setId(1);
		
		//when
		when(carService.deleteCar(car.getId())).thenReturn(new Car());
		Response response = Response.ok(carService.deleteCar(car.getId())).build();
		Car result = (Car) response.getEntity();
		
		//then
		assertNotEquals(result.getId(), car.getId());
	}
}

package com.nttd.rampUp.cars.boundary;

import javax.ws.rs.Produces;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.nttd.rampUp.cars.control.CarService;
import com.nttd.rampUp.cars.entity.Car;


/**
 * @author pabellap
 *
 */
@Stateless
@Path("Cars")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarResource {
	
	@Inject
	CarService carService;
	
	/**
	 * @return
	 */
	@GET
	public Response getAllCars(){
		
		return Response.ok(this.carService.getAllCars()).build();
	}
	
	/**
	 * @param id
	 * @return
	 */
	@GET
	@Path("{id}")
	public Response getCar(@PathParam("id") long id) {
		
		return Response.ok(this.carService.getCar(id)).build();
	}
	
	/**
	 * @param car
	 * @return
	 */
	@POST
	public Response createCar(Car car) {
		return Response.ok(this.carService.createCar(car)).build();
	}
	
	/**
	 * @param car
	 * @return
	 */
	@PUT
	public Response updateCar(Car car) {
		
		return Response.ok(this.carService.updateCar(car)).build();
	}
	
	/**
	 * @param id
	 * @return
	 */
	@DELETE
	@Path("{id}")
	public Response deleteCar(@PathParam("id") long id) {
		
		return Response.ok(this.carService.deleteCar(id)).build();
		
	}
}

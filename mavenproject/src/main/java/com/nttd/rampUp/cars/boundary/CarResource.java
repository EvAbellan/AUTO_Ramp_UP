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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * @author pabellap
 *
 */
@Stateless
@Path("Cars")
@Api(tags={"Car Resource"},value = "/car", description = "Operations about cars")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarResource {
	
	@Inject
	CarService carService;
	
	/**Get all cars from the database
	 * @return Json with all cars in the database
	 */
	@GET
	@ApiOperation(value = "Get all cars in the base data",
		    notes = "Multiple status values can be provided with comma seperated strings",
		    response = Car.class,
		    responseContainer = "List")
	public Response getAllCars(){
		
		return Response.ok(this.carService.getAllCars()).build();
	}
	
	/**Get a car by id
	 * @param id primary key of that car
	 * @return Car with such id
	 */
	@GET
	@Path("{id}")
	@ApiOperation(value = "Find a car by Id",
    notes = "If the id don not exist it will return an empty car instead of that car",
    response = Car.class,
    responseContainer = "Car")
	public Response getCar(@ApiParam(value = "Id of the car to find", required = true) @PathParam("id") long id) {
		
		return Response.ok(this.carService.getCar(id)).build();
	}
	
	/**Creation of a car 
	 * @param car json with all the necessary parameters to build a car
	 * @return the car created
	 */
	@POST
	@ApiOperation(value = "Creates a car",
    notes = "If the id of car already exist, return an empty car instead of the new car",
    response = Car.class,
    responseContainer = "Car")
	public Response createCar(@ApiParam(value = "The Car object to create", required = true)Car car) {
		return Response.ok(this.carService.createCar(car)).build();
	}
	
	/**Update an existed car
	 * @param car json with the news parameters
	 * @return the car updated
	 */
	@PUT
	@ApiOperation(value = "Updates a car",
    notes = "If the id of car do not exist, return an empty car instead of the updated car",
    response = Car.class,
    responseContainer = "Car")
	public Response updateCar(@ApiParam(value = "The Car object to update", required = true)Car car) {
		
		return Response.ok(this.carService.updateCar(car)).build();
	}
	
	/** Delete an existed car
	 * @param id the primary key of that car
	 * @return the car deleted 
	 */
	@DELETE
	@Path("{id}")
	@ApiOperation(value = "Deletes a car",
    notes = "If the id of car already exist, return an empty car instead of the deleted car",
    response = Car.class,
    responseContainer = "Car")
	public Response deleteCar(@ApiParam(value = "Id of the car to delete", required = true)@PathParam("id") long id) {
		
		return Response.ok(this.carService.deleteCar(id)).build();
		
	}
}

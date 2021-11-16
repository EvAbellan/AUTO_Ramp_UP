package com.nttd.rampUp;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;


/**
 * Configures a JAX-RS endpoint. Delete this class, if you are not exposing
 * JAX-RS resources in your application.
 *
 * @author airhacks.com
 */
@ApplicationPath("/api")
public class JAXRSConfiguration extends Application {
	
	public JAXRSConfiguration() {
		BeanConfig beanConfig = new BeanConfig();
		 
		beanConfig.setVersion("1.0.0");
		beanConfig.setTitle("Cars Storage");
		beanConfig.setBasePath("/rampUp/api");
		beanConfig.setResourcePackage("com.nttd.rampUp");
		beanConfig.setScan(true);
	}

}

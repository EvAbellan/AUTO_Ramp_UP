package com.nttd.rampUp;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.log4j.PropertyConfigurator;

@Startup
@Singleton
public class LogStarter {
	
	@PostConstruct
	public void configureLogging() {
		PropertyConfigurator.configure(LogStarter.class.getClassLoader().getResource("log4j.properties").getFile());
	}

}

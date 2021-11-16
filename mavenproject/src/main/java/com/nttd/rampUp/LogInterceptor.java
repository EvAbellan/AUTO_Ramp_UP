package com.nttd.rampUp;

import java.util.Date;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LogInterceptor {

	private static final Logger logger = LogManager.getLogger(LogInterceptor.class);

	@AroundInvoke
	public Object log(InvocationContext ctx) throws Exception {
	    logger.info("START of " + ctx.getMethod() + " " + new Date());
	    try {
	        return ctx.proceed();
	    } catch (Exception e) {
	        logger.warn("Error calling ctx.proceed in modifyGreeting()");
	        return null;
	    } finally {
	        logger.info("END of " + ctx.getMethod() + " " + new Date());
	    }
	}
}

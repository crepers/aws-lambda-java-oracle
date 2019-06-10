package com.wcjung.dbSample;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class HelloLambda  implements RequestHandler<Object, String> {
	
	private static final Logger LOGGER = LogManager.getLogger(HelloLambda.class);

	 @Override
	    public String handleRequest(Object input, Context context) {
		// System.out: One log statement but with a line break (AWS Lambda writes two events to CloudWatch).
        System.out.println("log data from stdout \n this is continuation of system.out");

       // System.err: One log statement but with a line break (AWS Lambda writes two events to CloudWatch).
        System.err.println("log data from stderr. \n this is a continuation of system.err");

      
        LOGGER.error("log data from log4j err. \n this is a continuation of log4j.err");

        // Return will include the log stream name so you can look
        // up the log later.
        return String.format("Hello %s. log stream = %s", input, context.getLogStreamName());
	}
}

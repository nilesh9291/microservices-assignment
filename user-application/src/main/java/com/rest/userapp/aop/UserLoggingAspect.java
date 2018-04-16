package com.rest.userapp.aop;


import java.util.Arrays;
import java.util.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.util.StopWatch.TaskInfo;

@Component
@Aspect
public class UserLoggingAspect {
	private Logger logger = Logger.getLogger(getClass().getName());
	
//	@Around("execution(* com.rest.userapp.controllers.*.*(..))")
//	//@Around("execution(* com.concretepage.service.*.*(..))")
//	public void userAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
//		System.out.println("@Around: Before calculation-"+ new Date());
//		joinPoint.proceed();
//		System.out.println("@Around: After calculation-"+ new Date());
//	}
	
	@Around("execution(* com.rest.userapp.controllers.*.*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("logAround() is running!");

		StopWatch stopWatch = new StopWatch();
		stopWatch.start(joinPoint.toShortString());
		logger.info("Intercepted Method Name : " + joinPoint.getSignature());
		logger.info("Intercepted Method Arguments : " + Arrays.toString(joinPoint.getArgs()));

		boolean isExceptionThrown = false;
		try {
			// execute the profiled method
			return joinPoint.proceed();
		} catch (RuntimeException e) {
			isExceptionThrown = true;
			throw e;
		} finally {
			stopWatch.stop();
			TaskInfo taskInfo = stopWatch.getLastTaskInfo();
			// Log the method's profiling result
			String profileMessage = taskInfo.getTaskName() + ": " + taskInfo.getTimeMillis() + " ms"
					+ (isExceptionThrown ? " (thrown Exception)" : "");
			logger.info(profileMessage);
		}
	}
	
//	@Around("execution(* com.rest.userapp.controllers.*.*(..))")
//	public Object userAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
//    	logger.info("logAround() is running!");
//    	logger.info("Method Invoked: " + joinPoint.getSignature());
//    	logger.info("Intercepted Method Arguments : " + Arrays.toString(joinPoint.getArgs()));
//		return joinPoint.proceed();
//		//System.out.println("@Around: After calculation-"+ new Date());
//	}
}
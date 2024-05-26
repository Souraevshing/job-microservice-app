package com.jobapp.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MonitorPerformance {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitorPerformance.class);

    @Around("execution(* com.jobapp.demo.service.JobService.*(..))")
    public Object monitorAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            LOGGER.error("Exception in method {}: {}", proceedingJoinPoint.getSignature().getName(), throwable.getMessage());
            throw throwable;
        } finally {
            long end = System.currentTimeMillis();
            LOGGER.info("Time taken by {} is {} ms", proceedingJoinPoint.getSignature().getName(), (end - start));
        }
        return result;
    }
}

package com.jobapp.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UseLogger {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(UseLogger.class);

    @Before("execution(* com.jobapp.demo.service.JobService.addJob(..))")
    private void addJob(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature().getName());
    }

    @Before("execution(* com.jobapp.demo.service.JobService.getAllJobs(..))")
    private void getAllJobs(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature().getName());
    }

    @Before("execution(* com.jobapp.demo.service.JobService.getJobById(..))")
    private void getJobById(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature().getName());
    }

    @Before("execution(* com.jobapp.demo.service.JobService.updateJob(..))")
    private void updateJob(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature().getName());
    }

    @Before("execution(* com.jobapp.demo.service.JobService.deleteJob(..))")
    private void deleteJob(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature().getName());
    }

    @Before("execution(* com.jobapp.demo.service.JobService.save(..))")
    private void save(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature().getName());
    }

    @Before("execution(* com.jobapp.demo.service.JobService.searchJobByKeyword(..))")
    private void searchJobByKeyword(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature().getName());
    }

    @After("execution(* com.jobapp.demo.service.JobService.addJob(..))")
    private void addJobAfter(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature().getName());
        LOGGER.info("DONE ADD JOB");
    }

    @After("execution(* com.jobapp.demo.service.JobService.getAllJobs(..))")
    private void getAllJobsAfter(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature().getName());
        LOGGER.info("DONE GET ALL JOBS");
    }

    @After("execution(* com.jobapp.demo.service.JobService.getJobById(..))")
    private void getJobByIdAfter(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature().getName());
        LOGGER.info("GET JOB BY ID DONE");
    }

    @After("execution(* com.jobapp.demo.service.JobService.updateJob(..))")
    private void updateJobAfter(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature().getName());
        LOGGER.info("DONE update JOB");
    }

    @After("execution(* com.jobapp.demo.service.JobService.deleteJob(..))")
    private void deleteJobAfter(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature().getName());
        LOGGER.info("DONE DELETE JOB");
    }

    @After("execution(* com.jobapp.demo.service.JobService.save(..))")
    private void saveAfter(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature().getName());
        LOGGER.info("DONE SAVE JOB");
    }

    @After("execution(* com.jobapp.demo.service.JobService.searchJobByKeyword(..))")
    private void searchJobByKeywordAfter(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature().getName());
        LOGGER.info("FILTER JOB DONE");
    }

    @AfterReturning(pointcut = "execution(* com.jobapp.demo.service.JobService.*(..))", returning = "result")
    private void logAfterReturning(JoinPoint joinPoint, Object result) {
        LOGGER.info("Method {} executed successfully", joinPoint.getSignature().getName());
        LOGGER.info("Return value: {}", result);
    }

    @AfterThrowing(pointcut = "execution(* com.jobapp.demo.service.JobService.*(..))", throwing = "error")
    private void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        LOGGER.error("Exception in method: {}", joinPoint.getSignature().getName());
        LOGGER.error("Exception: {}", error.getMessage());
    }
}

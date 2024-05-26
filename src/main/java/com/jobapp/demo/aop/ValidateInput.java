package com.jobapp.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidateInput {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateInput.class);

    @Around("execution(* com.jobapp.demo.service.JobService.getJobById(..)) && args(postId)")
    public Object validateGetJobByIdArgument(ProceedingJoinPoint proceedingJoinPoint, int postId) throws Throwable {
        if(postId<0) {
            LOGGER.info("Updating post id");
            postId = -postId;
        LOGGER.info("Updated post id {} with {}",postId,-postId);
        }
        return proceedingJoinPoint.proceed(new Object[]{postId});
    }
}

package com.example.oap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.example.dao.logs.LogDetailsRepository;
import com.example.entity.LogDetails;

@Aspect
@Component
public class TrackDAOAOPAspect {
    
    /** journalist. */
    @Autowired
    private LogDetailsRepository logDetailsDAO;
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(com.example.oap.TrackTime)")
    //@Around("execution(* com.example.service.UserServiceImpl.findAll(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        logger.info("[TRACKDAO-AOPAspect] Time Taken by {} is {}", joinPoint, timeTaken);
        logDetailsDAO.saveAndFlush(new LogDetails("UserData Time " +  timeTaken));
        
        return result;
    }

}

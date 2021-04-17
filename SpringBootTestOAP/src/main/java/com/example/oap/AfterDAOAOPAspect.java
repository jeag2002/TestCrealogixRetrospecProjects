package com.example.oap;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.example.dao.UserDAO;
import com.example.dao.logs.LogDetailsRepository;
import com.example.entity.LogDetails;
import com.example.entity.UserDetails;

/**
 * The Class AfterDAOAOPAspect.
 */
@DependsOn("springApplicationContextHolder")
@Configuration
@Aspect
public class AfterDAOAOPAspect {
    
    /** The logger. */
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /** journalist. */
    @Autowired
    private LogDetailsRepository logDetailsDAO;
    
    /**
     * After process.
     *
     * @param joinPoint the join point
     * @param result the result
     */
    @AfterReturning(value = "execution(* com.example.dao.*.*(..))",
            returning = "result")
    public void afterProcess(JoinPoint joinPoint, Object result) {
        logger.info("[AFTER-RESULT-DAOAOP] {} returned with value {}", joinPoint, result);
        logDetailsDAO.saveAndFlush(new LogDetails("UserData activity"));
    }
    /**
     * After.
     *
     * @param joinPoint the join point
     */
    @After(value = "execution(* com.example.dao.*.*(..))")
    public void after(JoinPoint joinPoint) {
        logger.info("[AFTER-DAOAOP] after execution of {}", joinPoint);
    }
}

package by.epam.mtlcwtchr.ecafe.service.validation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AccessCheckingAspect {

    @Pointcut("@annotation(RequiredPromoted)")
    private void requiredPromotedPointcut(){}

    @Before("requiredPromotedPointcut()")
    public void checkAccess(JoinPoint joinPoint){
        System.out.println("Checking access");
    }

}

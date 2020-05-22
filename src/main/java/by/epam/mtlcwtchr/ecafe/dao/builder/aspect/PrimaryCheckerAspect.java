package by.epam.mtlcwtchr.ecafe.dao.builder.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PrimaryCheckerAspect {

    private static final String[] primaryContainment = new String[]{"SELECT", "INSERT", "UPDATE", "DELETE"};

    @Pointcut("@annotation(by.epam.mtlcwtchr.ecafe.dao.builder.annotation.Primary)")
    private void primaryCheck(){}

    @Around("primaryCheck()")
    public Object checkPrimaryQueryPartUsage(ProceedingJoinPoint pjp) throws Throwable {
        return pjp.proceed();
    }

}

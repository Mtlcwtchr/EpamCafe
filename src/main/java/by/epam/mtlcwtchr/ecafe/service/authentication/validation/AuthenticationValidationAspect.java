package by.epam.mtlcwtchr.ecafe.service.authentication.validation;

import by.epam.mtlcwtchr.ecafe.config.ValidationServiceConfiguration;
import by.epam.mtlcwtchr.ecafe.service.exception.UserAuthenticationServiceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AuthenticationValidationAspect {


    @Pointcut("execution(public * " +
            "by.epam.mtlcwtchr.ecafe.service.authentication.IAuthenticationService.*(String, String))")
    private void signInValidationPointcut(){}

    @Pointcut("execution(public * " +
            "by.epam.mtlcwtchr.ecafe.service.authentication.IAuthenticationService.*(String, String, String, String))")
    private void signUpValidationPointcut(){}


    @Around("signInValidationPointcut()")
    public Object signInValidation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final Object[] args = proceedingJoinPoint.getArgs();
        if(!args[0].toString().matches(ValidationServiceConfiguration.INSTANCE.getUsernamePattern())||
                !args[1].toString().matches(ValidationServiceConfiguration.INSTANCE.getPasswordPattern())){
            throw new UserAuthenticationServiceException(
                    "Invalid argument: "
                    + (!args[0].toString().matches(ValidationServiceConfiguration.INSTANCE.getUsernamePattern()) ?
                    "username " + args[0].toString() + " " : "")
                    + (!args[1].toString().matches(ValidationServiceConfiguration.INSTANCE.getPasswordPattern()) ?
                    "password " + args[1].toString() + " " : "")) ;
        } else {
            return proceedingJoinPoint.proceed();
        }
    }

    @Around("signUpValidationPointcut()")
    public Object signUpValidation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final Object[] args = proceedingJoinPoint.getArgs();
        if(!args[0].toString().matches(ValidationServiceConfiguration.INSTANCE.getUsernamePattern())||
                !args[1].toString().matches(ValidationServiceConfiguration.INSTANCE.getPasswordPattern())||
                !args[2].toString().matches(ValidationServiceConfiguration.INSTANCE.getEmailPattern())||
                !args[3].toString().matches(ValidationServiceConfiguration.INSTANCE.getPhonePattern())){
            throw new UserAuthenticationServiceException(
                    "Invalid argument: "
                    + (!args[0].toString().matches(ValidationServiceConfiguration.INSTANCE.getUsernamePattern()) ?
                    "username " + args[0].toString() + " " : "")
                    + (!args[1].toString().matches(ValidationServiceConfiguration.INSTANCE.getPasswordPattern()) ?
                    "password " + args[1].toString() + " " : "")
                    + (!args[2].toString().matches(ValidationServiceConfiguration.INSTANCE.getEmailPattern()) ?
                    "email " + args[2].toString() + " " : "")
                    + (!args[3].toString().matches(ValidationServiceConfiguration.INSTANCE.getPhonePattern()) ?
                    "phone " + args[3].toString() + " " : ""));
        } else {
            return proceedingJoinPoint.proceed();
        }
    }

}

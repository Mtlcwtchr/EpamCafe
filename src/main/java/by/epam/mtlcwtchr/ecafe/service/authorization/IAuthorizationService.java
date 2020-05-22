package by.epam.mtlcwtchr.ecafe.service.authorization;

import by.epam.mtlcwtchr.ecafe.bean.Actor;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.mtlcwtchr.ecafe.service.exception.AuthorizationServiceException;
import by.epam.mtlcwtchr.ecafe.verification.CheckedArguments;

public interface IAuthorizationService {

    @CheckedArguments(requiredArrayOfArgsLength = {2, 5})
    @ExceptionableBeingLogged
    Actor authorizate(String... args) throws AuthorizationServiceException;

}

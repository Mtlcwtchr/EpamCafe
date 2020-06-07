package by.epam.mtlcwtchr.ecafe;

import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;

@FunctionalInterface
public interface OutPerformer {

    void doSomething() throws ServiceException;

}

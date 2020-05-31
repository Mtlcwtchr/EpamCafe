package by.epam.mtlcwtchr.ecafe.service;

import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.verification.CheckedArguments;

import java.util.List;
import java.util.Optional;

public interface IEntityService<T> {

    @ExceptionableBeingLogged("Service")
    List<T> getList() throws ServiceException;

    @CheckedArguments
    @ExceptionableBeingLogged
    Optional<T> findAny(Object key) throws ServiceException;

    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    Optional<T> update(T entity) throws ServiceException;

    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    Optional<T> save(T entity) throws ServiceException;

    @ExceptionableBeingLogged("Service")
    boolean delete(int id) throws ServiceException;

}

package by.epam.mtlcwtchr.ecafe.service;

import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.mtlcwtchr.ecafe.verification.CheckedArguments;

import java.util.List;
import java.util.Optional;

public interface IEntityService<T> {

    @ExceptionableBeingLogged("Service")
    List<T> getList() throws Exception;
    @ExceptionableBeingLogged("Service")
    Optional<T> find(int id) throws Exception;
    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    Optional<T> find(String name) throws Exception;
    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    Optional<T> update(T entity) throws Exception;
    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    Optional<T> save(T entity) throws Exception;
    @ExceptionableBeingLogged("Service")
    boolean delete(int id) throws Exception;


}

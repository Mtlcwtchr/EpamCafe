package by.epam.mtlcwtchr.ecafe.dao.repository;

import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.mtlcwtchr.ecafe.verification.annotation.CheckedArguments;

import java.util.List;
import java.util.Optional;

public interface IEntityRepository<T> {

    @ExceptionableBeingLogged("Data access object")
    List<T> getList() throws Exception;
    @ExceptionableBeingLogged("Data access object")
    Optional<T> find(int id) throws Exception;
    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    Optional<T> find(String name) throws Exception;
    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    Optional<T> save(T entity) throws Exception;
    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    Optional<T> update(T entity) throws Exception;
    @ExceptionableBeingLogged("Data access object")
    boolean delete(int id) throws Exception;

}

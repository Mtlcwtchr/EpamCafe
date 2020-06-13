package by.epam.mtlcwtchr.ecafe.dao.repository;

import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.mtlcwtchr.ecafe.verification.annotation.CheckedArguments;

import java.util.Optional;

public interface IRelationRepository<T> {

    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    Optional<T> get(T entity) throws Exception;
    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    Optional<T> update(T entity) throws Exception;

}

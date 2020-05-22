package by.epam.mtlcwtchr.ecafe.service;

import by.epam.mtlcwtchr.ecafe.bean.Client;
import by.epam.mtlcwtchr.ecafe.bean.User;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.verification.CheckedArguments;

import java.util.List;
import java.util.Optional;

public interface IClientService extends IEntityService<Client> {

    List<Client> getList() throws ServiceException;
    Optional<Client> find(int id) throws ServiceException;
    Optional<Client> find(String name) throws ServiceException;
    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    Optional<Client> find(User user) throws ServiceException;
    Optional<Client> update(Client client) throws ServiceException;
    Optional<Client> save(Client client) throws ServiceException;
    boolean delete(int id) throws ServiceException;

}

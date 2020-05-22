package by.epam.mtlcwtchr.ecafe.service;

import by.epam.mtlcwtchr.ecafe.bean.User;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface IUserService extends IEntityService<User> {

    List<User> getList() throws ServiceException;
    Optional<User> find(int id) throws ServiceException;
    Optional<User> find(String username) throws ServiceException;
    Optional<User> update(User user) throws ServiceException;
    Optional<User> save(User user) throws ServiceException;
    boolean delete(int id) throws ServiceException;

}

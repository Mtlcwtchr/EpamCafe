package by.epam.mtlcwtchr.ecafe.service;

import by.epam.mtlcwtchr.ecafe.bean.Meal;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface IMealService extends IEntityService<Meal> {


    List<Meal> getList() throws ServiceException;
    Optional<Meal> find(int id) throws ServiceException;
    Optional<Meal> find(String name) throws ServiceException;
    Optional<Meal> update(Meal meal) throws ServiceException;
    Optional<Meal> save(Meal meal) throws ServiceException;
    boolean delete(int id) throws ServiceException;


}

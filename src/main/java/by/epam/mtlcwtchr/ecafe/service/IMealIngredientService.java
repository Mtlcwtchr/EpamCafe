package by.epam.mtlcwtchr.ecafe.service;

import by.epam.mtlcwtchr.ecafe.bean.Ingredient;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface IMealIngredientService extends IEntityService<Ingredient> {

    List<Ingredient> getList() throws ServiceException;
    Optional<Ingredient> find(int id) throws ServiceException;
    Optional<Ingredient> find(String name) throws ServiceException;
    Optional<Ingredient> update(Ingredient ingredient) throws ServiceException;
    Optional<Ingredient> save(Ingredient ingredient) throws ServiceException;
    boolean delete(int id) throws ServiceException;

}

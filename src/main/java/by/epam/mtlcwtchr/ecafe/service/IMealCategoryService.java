package by.epam.mtlcwtchr.ecafe.service;

import by.epam.mtlcwtchr.ecafe.bean.Category;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface IMealCategoryService extends IEntityService<Category> {


    List<Category> getList() throws ServiceException;
    Optional<Category> find(int id) throws ServiceException;
    Optional<Category> find(String name) throws ServiceException;
    Optional<Category> update(Category category) throws ServiceException;
    Optional<Category> save(Category category) throws ServiceException;
    boolean delete(int id) throws ServiceException;


}

package by.epam.mtlcwtchr.ecafe.service;

import by.epam.mtlcwtchr.ecafe.bean.Order;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface IOrderService extends IEntityService<Order> {

    List<Order> getList() throws ServiceException;
    List<Order> getList(String clientName) throws ServiceException;
    Optional<Order> find(int id) throws ServiceException;
    Optional<Order> find(String clientName) throws ServiceException;
    Optional<Order> update(Order order) throws ServiceException;
    Optional<Order> save(Order order) throws ServiceException;
    boolean delete(int id) throws ServiceException;

}

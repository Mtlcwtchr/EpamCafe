package by.epam.mtlcwtchr.ecafe.dao.repository;

import by.epam.mtlcwtchr.ecafe.bean.Meal;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface IMealRepository extends IEntityRepository<Meal> {

    List<Meal> getList() throws DAOException;
    Optional<Meal> find(int id) throws DAOException;
    Optional<Meal> find(String name) throws DAOException;
    Optional<Meal> save(Meal entity) throws DAOException;
    Optional<Meal> update(Meal entity) throws DAOException;
    boolean delete(int id) throws DAOException;

}

package by.epam.mtlcwtchr.ecafe.dao.repository;

import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.entity.Reservation;

import java.util.List;
import java.util.Optional;

public interface IReservationRepository extends IEntityRepository<Reservation> {

    List<Reservation> getList() throws DAOException;
    Optional<Reservation> find(int id) throws DAOException;
    Optional<Reservation> find(String clientPhone) throws DAOException;
    Optional<Reservation> save(Reservation reservation) throws DAOException;
    Optional<Reservation> update(Reservation reservation) throws DAOException;
    boolean delete(int id) throws DAOException;

}

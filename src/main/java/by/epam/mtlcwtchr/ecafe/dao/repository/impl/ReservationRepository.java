package by.epam.mtlcwtchr.ecafe.dao.repository.impl;

import by.epam.mtlcwtchr.ecafe.dao.builder.PreparedStatementBuilder;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.Limiter;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LimiterMapGenerator;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LogicConcatenator;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.impl.ConnectionPool;
import by.epam.mtlcwtchr.ecafe.dao.repository.IReservationRepository;
import by.epam.mtlcwtchr.ecafe.entity.Hall;
import by.epam.mtlcwtchr.ecafe.entity.Reservation;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ReservationRepository implements IReservationRepository {

    @Override
    public List<Reservation> getList() throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select("epam_cafe.reservation as r",
                            "r.id", "r.reservation_date", "r.contact_time", "r.contact_phone",
                            "h.id", "h.guests_number", "h.hall_name", "h.hall_description")
                    .joining("epam_cafe.hall as h", "fk_hall_id", "h.id")
                    .build(connection)){
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    if(!resultSet.first()){
                        return List.of();
                    } else{
                        ArrayList<Reservation> list = new ArrayList<>();
                        do{
                            list.add(new Reservation(
                                    resultSet.getInt(1),
                                    new Hall(
                                            resultSet.getInt(5),
                                            resultSet.getInt(6),
                                            resultSet.getString(7),
                                            resultSet.getString(8)
                                    ),
                                    resultSet.getDate(2),
                                    resultSet.getTime(3),
                                    resultSet.getString(4)));
                        } while (resultSet.next());
                        return List.copyOf(list);
                    }
                } catch (SQLException ex){
                    throw new DAOException(ex);
                }
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Reservation> find(int id) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select("epam_cafe.reservation as r",
                            "r.id", "r.reservation_date", "r.contact_time", "r.contact_phone",
                            "h.id", "h.guests_number")
                    .joining("epam_cafe.hall as h", "fk_hall_id", "h.id")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, "r.id"), LogicConcatenator.AND)
                    .build(connection, Optional.of(id))){
                return getReservation(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Reservation> find(String clientPhone) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select("epam_cafe.reservation as r",
                            "r.id", "r.reservation_date", "r.contact_time", "r.contact_phone",
                            "h.id", "h.guests_number", "h.hall_name", "h.hall_description")
                    .joining("epam_cafe.hall as h", "fk_hall_id", "h.id")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, "u.phone"), LogicConcatenator.AND)
                    .build(connection, Optional.of(clientPhone))){
                return getReservation(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @NotNull
    private Optional<Reservation> getReservation(PreparedStatement preparedStatement) throws DAOException {
        try(ResultSet resultSet = preparedStatement.executeQuery()){
            if(!resultSet.first()){
                return Optional.empty();
            } else{
                return Optional.of(new Reservation(
                        resultSet.getInt(1),
                        new Hall(
                                resultSet.getInt(5),
                                resultSet.getInt(6),
                                resultSet.getString(7),
                                resultSet.getString(8)
                        ),
                        resultSet.getDate(2),
                        resultSet.getTime(3),
                        resultSet.getString(4)));
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Reservation> save(Reservation reservation) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .insert("epam_cafe.reservation",
                            "fk_hall_id", "reservation_date", "contact_time", "contact_phone")
                    .build(connection,
                            Optional.of(reservation.getReservedHall().getId()),
                            Optional.of(reservation.getReservationDate()),
                            Optional.of(reservation.getContactTime()),
                            Optional.of(reservation.getContactPhone()))){
                    preparedStatement.execute();
                    return getCreated();
                } catch (SQLException ex){
                    throw new DAOException(ex);
                }
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        }

    private Optional<Reservation> getCreated() throws DAOException{
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select("epam_cafe.reservation as r",
                            "r.id", "r.reservation_date", "r.contact_time", "r.contact_phone",
                            "h.id", "h.guests_number", "h.hall_name", "h.hall_description", "h.hall_name", "h.hall_description")
                    .joining("epam_cafe.hall as h", "fk_hall_id", "h.id")
                    .whereMaxId("epam_cafe.reservation as r", "r.id")
                    .build(connection)){
                return getReservation(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Reservation> update(Reservation reservation) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .update("epam_cafe.reservation",
                            "id", "fk_hall_id", "reservation_date", "contact_time", "contact_phone")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, "id"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(reservation.getId()),
                            Optional.of(reservation.getReservedHall().getId()),
                            Optional.of(reservation.getReservationDate()),
                            Optional.of(reservation.getContactTime()),
                            Optional.of(reservation.getContactPhone()),
                            Optional.of(reservation.getId()))){
                preparedStatement.execute();
                return Optional.of(reservation);
            } catch (SQLException ex){
                throw new DAOException(ex);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }

    @Override
    public boolean delete(int id) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .delete("epam_cafe.reservation")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, "id"), LogicConcatenator.AND)
                    .build(connection, Optional.of(id))){
                return preparedStatement.execute();
            } catch (SQLException ex){
                throw new DAOException(ex);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }
}

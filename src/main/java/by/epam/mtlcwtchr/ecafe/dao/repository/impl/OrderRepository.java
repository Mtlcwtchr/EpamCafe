package by.epam.mtlcwtchr.ecafe.dao.repository.impl;

import by.epam.mtlcwtchr.ecafe.entity.*;
import by.epam.mtlcwtchr.ecafe.dao.builder.PreparedStatementBuilder;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.Limiter;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LimiterMapGenerator;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LogicConcatenator;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.impl.ConnectionPool;
import by.epam.mtlcwtchr.ecafe.dao.repository.IOrderRepository;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.mtlcwtchr.ecafe.verification.CheckedArguments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepository implements IOrderRepository {

    @Override
    public List<Order> getList() throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select("epam_cafe.order AS o",
                            "u.id", "username", "password", "email", "phone", "isPromoted",
                            "c.id", "name", "loyalty_points", "bonuses", "isBanned",
                            "o.id", "order_datetime", "isPaid", "isPrepared", "isTaken")
                    .joining("epam_cafe.client as c", "o.fk_client_id", "c.id")
                    .joining("epam_cafe.user as u", "c.fk_user_id", "u.id")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,"o.isActive", "u.isActive"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(true),
                            Optional.of(true))){
                    System.out.println(preparedStatement);
                    return getOrders(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public List<Order> getList(String clientName) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select("epam_cafe.order AS o",
                            "u.id", "username", "password", "email", "phone", "isPromoted",
                            "c.id", "name", "loyalty_points", "bonuses", "isBanned",
                            "o.id", "order_datetime", "isPaid", "isPrepared", "isTaken")
                    .joining("epam_cafe.client as c", "o.fk_client_id", "c.id")
                    .joining("epam_cafe.user as u", "c.fk_user_id", "u.id")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,"o.isActive", "u.isActive", "c.name"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(true),
                            Optional.of(clientName),
                            Optional.of(true))){
                    return getOrders(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Order> find(int id) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select("epam_cafe.order AS o",
                            "u.id", "username", "password", "email", "phone", "isPromoted",
                            "c.id", "name", "loyalty_points", "bonuses", "isBanned",
                            "o.id", "order_datetime", "isPaid", "isPrepared", "isTaken")
                    .joining("epam_cafe.client as c", "o.fk_client_id", "c.id")
                    .joining("epam_cafe.user as u", "c.fk_user_id", "u.id")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,"o.isActive", "u.isActive", "o.id"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(true),
                            Optional.of(id),
                            Optional.of(true))){
                    return getOrder(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Order> find(String clientName) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select("epam_cafe.order AS o",
                            "u.id", "username", "password", "email", "phone", "isPromoted",
                            "c.id", "name", "loyalty_points", "bonuses", "isBanned",
                            "o.id", "order_datetime", "isPaid", "isPrepared", "isTaken")
                    .joining("epam_cafe.client as c", "o.fk_client_id", "c.id")
                    .joining("epam_cafe.user as u", "c.fk_user_id", "u.id")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,"o.isActive", "u.isActive", "c.name"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(true),
                            Optional.of(clientName),
                            Optional.of(true))){
                    return getOrder(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Order> save(Order order) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .insert("epam_cafe.order", "fk_client_id", "order_datetime", "isPaid", "isPrepared", "isTaken")
                    .build(connection,
                            Optional.of(order.getCustomer().getId()),
                            Optional.of(order.getOrderDate()),
                            Optional.of(order.isPaid()),
                            Optional.of(order.isPrepared()),
                            Optional.of(order.isTaken()))){
                    preparedStatement.execute();
                    return getCreated();
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Order> update(Order order) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .update("epam_cafe.order", "id", "fk_client_id", "order_datetime", "isPaid", "isPrepared", "isTaken")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,"isActive",  "id"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(order.getId()),
                            Optional.of(order.getCustomer().getId()),
                            Optional.of(order.getOrderDate()),
                            Optional.of(order.isPaid()),
                            Optional.of(order.isPrepared()),
                            Optional.of(order.isTaken()),
                            Optional.of(order.getId()),
                            Optional.of(true))){
                    preparedStatement.execute();
                    return Optional.of(order);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public boolean delete(int id) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .update("epam_cafe.order AS m", "isActive")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,"isActive", "id"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(false),
                            Optional.of(id),
                            Optional.of(true))){
                return preparedStatement.execute();
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    private Optional<Order> getCreated() throws DAOException{
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select("epam_cafe.order AS o",
                            "u.id", "username", "password", "email", "phone", "isPromoted",
                            "c.id", "name", "loyalty_points", "bonuses", "isBanned",
                            "o.id", "order_datetime", "isPaid", "isPrepared", "isTaken")
                    .joining("epam_cafe.client as c", "o.fk_client_id", "c.id")
                    .joining("epam_cafe.user as u", "c.fk_user_id", "u.id")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,"o.isActive", "u.isActive"), LogicConcatenator.AND)
                    .whereMaxId("epam_cafe.order", "o.id")
                    .build(connection,
                            Optional.of(true),
                            Optional.of(true))){
                return getOrder(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @ExceptionableBeingLogged("Data access object")
    private Optional<Order> getOrder(PreparedStatement preparedStatement) throws DAOException{
        try(ResultSet resultSet = preparedStatement.executeQuery()){
            if(!resultSet.first()){
                return Optional.empty();
            } else{
                return Optional.of(new Order(
                        resultSet.getInt(12),
                        new Client(new User(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getBoolean(6)),
                                resultSet.getInt(7),
                                resultSet.getString(8),
                                resultSet.getInt(9),
                                resultSet.getInt(10),
                                resultSet.getBoolean(11)),
                        resultSet.getTimestamp(13),
                        resultSet.getBoolean(14),
                        resultSet.getBoolean(15),
                        resultSet.getBoolean(16)
                ));
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @ExceptionableBeingLogged("Data access object")
    private List<Order> getOrders(PreparedStatement preparedStatement) throws DAOException {
        try(ResultSet resultSet = preparedStatement.executeQuery()){
            if(!resultSet.first()){
                return List.of();
            } else{
                ArrayList<Order> list = new ArrayList<>();
                do{
                    list.add(new Order(
                            resultSet.getInt(12),
                            new Client(new User(
                                    resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4),
                                    resultSet.getString(5),
                                    resultSet.getBoolean(6)),
                                    resultSet.getInt(7),
                                    resultSet.getString(8),
                                    resultSet.getInt(9),
                                    resultSet.getInt(10),
                                    resultSet.getBoolean(11)),
                            resultSet.getTimestamp(13),
                            resultSet.getBoolean(14),
                            resultSet.getBoolean(15),
                            resultSet.getBoolean(16)
                    ));
                } while (resultSet.next());
                return List.copyOf(list);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }


}

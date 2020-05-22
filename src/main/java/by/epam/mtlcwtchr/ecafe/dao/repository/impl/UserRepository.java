package by.epam.mtlcwtchr.ecafe.dao.repository.impl;

import by.epam.mtlcwtchr.ecafe.bean.User;
import by.epam.mtlcwtchr.ecafe.dao.builder.PreparedStatementBuilder;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.Limiter;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LimiterMapGenerator;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LogicConcatenator;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.impl.ConnectionPool;
import by.epam.mtlcwtchr.ecafe.dao.repository.IUserRepository;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.mtlcwtchr.ecafe.verification.CheckedArguments;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserRepository implements IUserRepository {

    @Override
    public List<User> getList() throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select("epam_cafe.user", "id", "username", "password", "email", "phone", "isPromoted")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,"isActive"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(true))){
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    if(!resultSet.first()){
                        return List.of();
                    } else{
                        ArrayList<User> list = new ArrayList<>();
                        do{
                            list.add(new User(
                                    resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4),
                                    resultSet.getString(5),
                                    resultSet.getBoolean(6)
                            ));
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
    public Optional<User> find(int id) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select("epam_cafe.user", "id", "username", "password", "email", "phone", "isPromoted")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,"isActive", "id"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(id),
                            Optional.of(true))){
                 return getUser(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<User> find(String username) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select("epam_cafe.user", "id", "username", "password", "email", "phone", "isPromoted")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,"isActive", "username"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(true),
                            Optional.of(username))){
                    return getUser(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<User> save(User user) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .insert("epam_cafe.user", "username", "password", "email", "phone")
                    .build(connection,
                            Optional.of(user.getUsername()),
                            Optional.of(user.getPassword()),
                            Optional.of(user.getEmail()),
                            Optional.of(user.getPhone()))){
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
    public Optional<User> update(User user) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .update("epam_cafe.user", "id", "username", "password", "email", "phone", "isPromoted")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,"isActive", "id"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(user.getId()),
                            Optional.of(user.getUsername()),
                            Optional.of(user.getPassword()),
                            Optional.of(user.getEmail()),
                            Optional.of(user.getPhone()),
                            Optional.of(user.isPromoted()),
                            Optional.of(user.getId()),
                            Optional.of(true))){
                    preparedStatement.execute();
                    return Optional.of(user);
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
                    .update("epam_cafe.user", "isActive")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,"isActive","id"), LogicConcatenator.AND)
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
    private Optional<User> getCreated() throws DAOException {
        try (Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()) {
            try (PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select("epam_cafe.user", "id", "username", "password", "email", "phone", "isPromoted")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,"isActive"), LogicConcatenator.AND)
                    .whereMaxId("epam_cafe.user")
                    .build(connection,
                            Optional.of(true))) {
                return getUser(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }

    @NotNull
    @ExceptionableBeingLogged("Data access object")
    private Optional<User> getUser(PreparedStatement preparedStatement) throws DAOException {
        try(ResultSet resultSet = preparedStatement.executeQuery()){
            if(!resultSet.first()){
                return Optional.empty();
            } else{
                return Optional.of(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getBoolean(6)));
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }


}

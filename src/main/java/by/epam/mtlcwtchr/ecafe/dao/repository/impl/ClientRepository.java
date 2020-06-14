package by.epam.mtlcwtchr.ecafe.dao.repository.impl;

import by.epam.mtlcwtchr.ecafe.entity.Client;
import by.epam.mtlcwtchr.ecafe.entity.User;
import by.epam.mtlcwtchr.ecafe.dao.builder.PreparedStatementBuilder;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.Limiter;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LimiterMapGenerator;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LogicConcatenator;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.impl.ConnectionPool;
import by.epam.mtlcwtchr.ecafe.dao.repository.IClientRepository;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.mtlcwtchr.ecafe.verification.annotation.CheckedArguments;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class ClientRepository implements IClientRepository {

    private static final String sourceTableName = "epam_cafe.client";
    private static final String sourceTableNameAlias = " AS c";
    private static final String[] selectionColumnNames =
            new String[]{"u.id", "username", "password", "email", "phone", "isPromoted",
                    "c.id", "name", "loyalty_points", "bonuses", "isBanned"};
    private static final String joiningTableName = "epam_cafe.user AS u";
    private static final String joinForeignKeyName = "c.fk_user_id";
    private static final String foreignTableKeyName = "u.id";
    private static final String[] insertionColumnNames =
            new String[]{"name", "fk_user_id"};
    private static final String[] updatingColumnNames =
            new String[]{"id", "name", "loyalty_points", "bonuses", "isBanned", "fk_user_id"};
    private static final String idColumnName = "c.id";
    private static final String userIdColumnName = "u.id";
    private static final String nameColumnName = "c.name";

    @Override
    public List<Client> getList() throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(sourceTableName + sourceTableNameAlias, selectionColumnNames)
                    .joining(joiningTableName, joinForeignKeyName, foreignTableKeyName)
                    .build(connection)){
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    if(!resultSet.first()){
                        return List.of();
                    } else{
                        ArrayList<Client> list = new ArrayList<>();
                        do{
                            list.add(new Client(new User(
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
                                    resultSet.getBoolean(11)));
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
    public Optional<Client> find(int id) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(sourceTableName + sourceTableNameAlias, selectionColumnNames)
                    .joining(joiningTableName, joinForeignKeyName, foreignTableKeyName)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, idColumnName), LogicConcatenator.AND)
                    .build(connection, Optional.of(id))){
                return getClient(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Client> find(String name) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(sourceTableName + sourceTableNameAlias, selectionColumnNames)
                    .joining(joiningTableName, joinForeignKeyName, foreignTableKeyName)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, nameColumnName), LogicConcatenator.AND)
                    .build(connection, Optional.of(name))){
                    return getClient(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Client> find(User user) throws DAOException {
        if(Objects.isNull(user)) return Optional.empty();
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(sourceTableName + sourceTableNameAlias, selectionColumnNames)
                    .joining(joiningTableName, joinForeignKeyName, foreignTableKeyName)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, userIdColumnName), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(user.getId()))){
                    return getClient(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Client> save(Client client) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .insert(sourceTableName,  insertionColumnNames)
                    .build(connection,
                            Optional.of(client.getName()),
                            Optional.of(client.getUser().getId())
                            )){
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
    public Optional<Client> update(Client client) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .update(sourceTableName + sourceTableNameAlias,  updatingColumnNames)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,idColumnName), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(client.getId()),
                            Optional.of(client.getName()),
                            Optional.of(client.getLoyaltyPoints()),
                            Optional.of(client.getBonuses()),
                            Optional.of(client.isBanned()),
                            Optional.of(client.getUser().getId()),
                            Optional.of(client.getId())
                    )){
                preparedStatement.execute();
                return Optional.of(client);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    private Optional<Client> getCreated() throws DAOException{
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(sourceTableName + sourceTableNameAlias, selectionColumnNames)
                    .joining(joiningTableName, joinForeignKeyName, foreignTableKeyName)
                    .whereMaxId(sourceTableName, idColumnName)
                    .build(connection)){
                return getClient(preparedStatement);
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
                    .delete(sourceTableName + sourceTableNameAlias)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,idColumnName), LogicConcatenator.AND)
                    .build(connection, Optional.of(id))){
                    return preparedStatement.execute();
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @NotNull
    @ExceptionableBeingLogged("Data access object")
    private Optional<Client> getClient(PreparedStatement preparedStatement) throws DAOException{
        try(ResultSet resultSet = preparedStatement.executeQuery()) {
            if (!resultSet.first()) {
                return Optional.empty();
            } else {
                return Optional.of(new Client(new User(
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
                        resultSet.getBoolean(11)));
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

}

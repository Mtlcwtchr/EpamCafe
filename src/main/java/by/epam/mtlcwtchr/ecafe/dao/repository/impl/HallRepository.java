package by.epam.mtlcwtchr.ecafe.dao.repository.impl;

import by.epam.mtlcwtchr.ecafe.dao.builder.PreparedStatementBuilder;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.Limiter;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LimiterMapGenerator;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LogicConcatenator;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.impl.ConnectionPool;
import by.epam.mtlcwtchr.ecafe.dao.repository.IHallRepository;
import by.epam.mtlcwtchr.ecafe.entity.Hall;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HallRepository implements IHallRepository {

    private static final String sourceTableName = "epam_cafe.hall";
    private static final String sourceTableNameAlias =  " AS h";
    private static final String[] selectionColumnNames =
            new String[]{"h.id", "h.guests_number", "hall_name", "hall_description"};
    private static final String[] insertionColumnNames =
            new String[]{ "guests_number", "hall_name", "hall_description"};
    private static final String[] insertionColumnNamesIncludeId =
            new String[]{ "id", "guests_number", "hall_name", "hall_description"};
    private static final String[] updatingColumnNames =
            new String[]{"h.id", "h.guests_number", "hall_name", "hall_description"};
    private static final String idColumnName = "h.id";

    @Override
    public List<Hall> getList() throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(sourceTableName + sourceTableNameAlias, selectionColumnNames)
                    .build(connection)){
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    if(!resultSet.first()){
                        return List.of();
                    } else{
                        ArrayList<Hall> list = new ArrayList<>();
                        do{
                            list.add(new Hall(
                                    resultSet.getInt(1),
                                    resultSet.getInt(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4)
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
    public Optional<Hall> find(int id) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(sourceTableName + sourceTableNameAlias, selectionColumnNames)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, idColumnName), LogicConcatenator.AND)
                    .build(connection, Optional.of(id))){
                return getHall(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Hall> find(String name) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(sourceTableName + sourceTableNameAlias, selectionColumnNames)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, idColumnName), LogicConcatenator.AND)
                    .build(connection, Optional.of(Integer.parseInt(name)))){
                return getHall(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @NotNull
    private Optional<Hall> getHall(PreparedStatement preparedStatement) throws DAOException {
        try(ResultSet resultSet = preparedStatement.executeQuery()){
            if(!resultSet.first()){
                return Optional.empty();
            } else {
                return Optional.of(new Hall(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                ));
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Hall> save(Hall hall) throws DAOException {
        return hall.getId()==0 ? saveUnIncludeId(hall) : saveIncludeId(hall);
    }

    private Optional<Hall> saveUnIncludeId(Hall hall) throws DAOException {
        try (Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()) {
            try (PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .insert(sourceTableName, insertionColumnNames)
                    .build(connection,
                            Optional.of(hall.getGuestsNumber()),
                            Optional.of(hall.getName()),
                            Optional.of(hall.getDescription()))) {
                preparedStatement.execute();
                return getCreated();
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }

    private Optional<Hall> saveIncludeId(Hall hall) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .insert(sourceTableName, insertionColumnNamesIncludeId)
                    .build(connection,
                            Optional.of(hall.getId()),
                            Optional.of(hall.getGuestsNumber()),
                            Optional.of(hall.getName()),
                            Optional.of(hall.getDescription()))){
                preparedStatement.execute();
                return find(hall.getId());
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    private Optional<Hall> getCreated() throws DAOException{
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(sourceTableName + sourceTableNameAlias, selectionColumnNames)
                    .whereMaxId(sourceTableName, idColumnName)
                    .build(connection)){
                return getHall(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Hall> update(Hall hall) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .update(sourceTableName + sourceTableNameAlias, updatingColumnNames)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,idColumnName), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(hall.getId()),
                            Optional.of(hall.getGuestsNumber()),
                            Optional.of(hall.getName()),
                            Optional.of(hall.getDescription()),
                            Optional.of(hall.getId()))){
                preparedStatement.execute();
                return Optional.of(hall);
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
}

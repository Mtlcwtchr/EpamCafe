package by.epam.mtlcwtchr.ecafe.dao.repository.impl;

import by.epam.mtlcwtchr.ecafe.entity.Ingredient;
import by.epam.mtlcwtchr.ecafe.dao.builder.PreparedStatementBuilder;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.Limiter;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LimiterMapGenerator;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LogicConcatenator;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.impl.ConnectionPool;
import by.epam.mtlcwtchr.ecafe.dao.repository.IMealIngredientRepository;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.mtlcwtchr.ecafe.verification.annotation.CheckedArguments;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MealIngredientRepository implements IMealIngredientRepository {

    private static final String sourceTableName = "epam_cafe.meal_ingredient";
    private static final String[] selectionColumnNames =
            new String[]{"id", "name", "pic_url"};
    private static final String[] insertionColumnNames =
            new String[]{ "name", "pic_url"};
    private static final String[] updatingColumnNames =
            new String[]{"id", "name", "pic_url"};
    private static final String idColumnName = "id";
    private static final String nameColumnName = "name";

    @Override
    public List<Ingredient> getList() throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(sourceTableName, selectionColumnNames)
                    .build(connection)){
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    if(!resultSet.first()){
                        return List.of();
                    } else{
                        ArrayList<Ingredient> list = new ArrayList<>();
                        do{
                            list.add(new Ingredient(
                                    resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3)
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
    public Optional<Ingredient> find(int id) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(sourceTableName, selectionColumnNames)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, idColumnName), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(id))){
                    return getIngredient(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Ingredient> find(String name) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(sourceTableName, selectionColumnNames)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, nameColumnName), LogicConcatenator.AND)
                    .build(connection, Optional.of(name))){
                return getIngredient(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Ingredient> save(Ingredient ingredient) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .insert(sourceTableName, insertionColumnNames)
                    .build(connection,
                            Optional.of(ingredient.getName()),
                            Optional.of(ingredient.getPictureUrl()))){
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
    public Optional<Ingredient> update(Ingredient ingredient) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .update(sourceTableName, updatingColumnNames)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,idColumnName), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(ingredient.getId()),
                            Optional.of(ingredient.getName()),
                            Optional.of(ingredient.getPictureUrl()),
                            Optional.of(ingredient.getId()))){
                preparedStatement.execute();
                return Optional.of(ingredient);
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
                    .delete(sourceTableName)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, idColumnName), LogicConcatenator.AND)
                    .build(connection, Optional.of(id))){
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
    private Optional<Ingredient> getCreated() throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(sourceTableName, selectionColumnNames)
                    .whereMaxId(sourceTableName)
                    .build(connection)){
                return getIngredient(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @NotNull
    @ExceptionableBeingLogged("Data access object")
    private Optional<Ingredient> getIngredient(PreparedStatement preparedStatement) throws DAOException{
        try(ResultSet resultSet = preparedStatement.executeQuery()){
            if(!resultSet.first()){
                return Optional.empty();
            } else{
                return Optional.of(new Ingredient(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)));
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

}

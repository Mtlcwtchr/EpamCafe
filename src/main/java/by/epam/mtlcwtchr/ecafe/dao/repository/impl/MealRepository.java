package by.epam.mtlcwtchr.ecafe.dao.repository.impl;

import by.epam.mtlcwtchr.ecafe.entity.Category;
import by.epam.mtlcwtchr.ecafe.entity.Meal;
import by.epam.mtlcwtchr.ecafe.dao.builder.PreparedStatementBuilder;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.Limiter;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LimiterMapGenerator;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LogicConcatenator;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.impl.ConnectionPool;
import by.epam.mtlcwtchr.ecafe.dao.repository.IMealRepository;
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

public class MealRepository implements IMealRepository {

    private static final String sourceTableName = "epam_cafe.meal";
    private static final String sourceTableNameAlias = " AS m";
    private static final String[] selectionColumnNames =
            new String[]{"c.id", "c.name", "c.pic_url",
                    "m.id", "m.name", "m.price", "m.pic_url"};
    private static final String joiningTableName = "epam_cafe.meal_category as c";
    private static final String joinForeignKeyName = "m.fk_category_id";
    private static final String foreignTableKeyName = "c.id";
    private static final String[] insertionColumnNames =
            new String[]{"name", "price", "pic_url", "fk_category_id"};
    private static final String[] updatingColumnNames =
            new String[]{"id", "name", "price", "pic_url", "fk_category_id"};
    private static final String categoryIdColumnName = "c.id";
    private static final String idColumnName = "m.id";
    private static final String nameColumnName = "m.name";

    @Override
    public List<Meal> getList() throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(sourceTableName + sourceTableNameAlias, selectionColumnNames)
                    .joining(joiningTableName, joinForeignKeyName, foreignTableKeyName)
                    .build(connection)){
                return getMeals(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public List<Meal> getList(int categoryId) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(sourceTableName + sourceTableNameAlias, selectionColumnNames)
                    .joining(joiningTableName, joinForeignKeyName, foreignTableKeyName)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, categoryIdColumnName), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(categoryId))){
                return getMeals(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Meal> find(int id) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(sourceTableName + sourceTableNameAlias, selectionColumnNames)
                    .joining(joiningTableName, joinForeignKeyName, foreignTableKeyName)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,idColumnName), LogicConcatenator.AND)
                    .build(connection, Optional.of(id))){
                    return getMeal(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Meal> find(String name) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(sourceTableName + sourceTableNameAlias, selectionColumnNames)
                    .joining(joiningTableName, joinForeignKeyName, foreignTableKeyName)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,nameColumnName), LogicConcatenator.AND)
                    .build(connection, Optional.of(name))){
                    return getMeal(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Meal> save(Meal meal) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .insert(sourceTableName,  insertionColumnNames)
                    .build(connection,
                            Optional.of(meal.getName()),
                            Optional.of(meal.getPrice()),
                            Optional.of(meal.getPictureUrl()),
                            Optional.of(meal.getCategory().getId()))){
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
    public Optional<Meal> update(Meal meal) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .update(sourceTableName + sourceTableNameAlias,  updatingColumnNames)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, idColumnName), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(meal.getId()),
                            Optional.of(meal.getName()),
                            Optional.of(meal.getPrice()),
                            Optional.of(meal.getPictureUrl()),
                            Optional.of(meal.getCategory().getId()),
                            Optional.of(meal.getId()))){
                    preparedStatement.execute();
                    return Optional.of(meal);
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
    private Optional<Meal> getCreated() throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(sourceTableName + sourceTableNameAlias, selectionColumnNames)
                    .joining(joiningTableName, joinForeignKeyName, foreignTableKeyName)
                    .whereMaxId(sourceTableName + sourceTableNameAlias, idColumnName)
                    .build(connection)){
                    return getMeal(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @NotNull
    @ExceptionableBeingLogged("Data access object")
    private Optional<Meal> getMeal(PreparedStatement preparedStatement) throws DAOException{
        try(ResultSet resultSet = preparedStatement.executeQuery()){
            if(!resultSet.first()){
                return Optional.empty();
            } else{
                return Optional.of(new Meal(
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        new Category(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3)
                        ),
                        resultSet.getString(7)
                ));
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @NotNull
    @ExceptionableBeingLogged("Data access object")
    private List<Meal> getMeals(PreparedStatement preparedStatement) throws DAOException {
        try(ResultSet resultSet = preparedStatement.executeQuery()){
            if(!resultSet.first()){
                return List.of();
            } else{
                ArrayList<Meal> list = new ArrayList<>();
                do{
                    list.add(new Meal(
                            resultSet.getInt(4),
                            resultSet.getString(5),
                            resultSet.getInt(6),
                            new Category(
                                    resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3)
                            ),
                            resultSet.getString(7)
                    ));
                } while (resultSet.next());
                return List.copyOf(list);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }


}

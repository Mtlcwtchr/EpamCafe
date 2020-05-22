package by.epam.mtlcwtchr.ecafe.dao.repository.impl;

import by.epam.mtlcwtchr.ecafe.bean.Category;
import by.epam.mtlcwtchr.ecafe.bean.Meal;
import by.epam.mtlcwtchr.ecafe.dao.builder.PreparedStatementBuilder;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.Limiter;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LimiterMapGenerator;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LogicConcatenator;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.impl.ConnectionPool;
import by.epam.mtlcwtchr.ecafe.dao.repository.IMealRepository;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.mtlcwtchr.ecafe.verification.CheckedArguments;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MealRepository implements IMealRepository {

    @Override
    public List<Meal> getList() throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select("epam_cafe.meal AS m",
                            "c.id", "c.name", "c.pic_url",
                            "m.id", "m.name", "m.price", "m.pic_url")
                    .joining("epam_cafe.meal_category as c", "m.fk_category_id", "c.id")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,"m.isActive", "c.isActive"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(true),
                            Optional.of(true))){
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
                    .select("epam_cafe.meal AS m",
                            "c.id", "c.name", "c.pic_url",
                            "m.id", "m.name", "m.price", "m.pic_url")
                    .joining("epam_cafe.meal_category as c", "m.fk_category_id", "c.id")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,"m.isActive", "c.isActive", "m.id"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(true),
                            Optional.of(id),
                            Optional.of(true))){
                    System.out.println(preparedStatement);
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
                    .select("epam_cafe.meal AS m",
                            "c.id", "c.name", "c.pic_url",
                            "m.id", "m.name", "m.price", "m.pic_url")
                    .joining("epam_cafe.meal_category as c", "m.fk_category_id", "c.id")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,"m.isActive", "c.isActive", "m.name"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(true),
                            Optional.of(name),
                            Optional.of(true))){
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
                    .insert("epam_cafe.meal",  "name", "price", "pic_url", "fk_category_id")
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
                    .update("epam_cafe.meal",  "id", "name", "price", "pic_url", "fk_category_id")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, "isActive", "id"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(meal.getId()),
                            Optional.of(meal.getName()),
                            Optional.of(meal.getPrice()),
                            Optional.of(meal.getPictureUrl()),
                            Optional.of(meal.getCategory().getId()),
                            Optional.of(meal.getId()),
                            Optional.of(true))){
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
                    .update("epam_cafe.meal",  "isActive")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, "isActive", "id"), LogicConcatenator.AND)
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
    private Optional<Meal> getCreated() throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select("epam_cafe.meal AS m",
                            "c.id", "c.name", "c.pic_url",
                            "m.id", "m.name", "m.price", "m.pic_url")
                    .joining("epam_cafe.meal_category as c", "m.fk_category_id", "c.id")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,"m.isActive", "c.isActive"), LogicConcatenator.AND)
                    .whereMaxId("epam_cafe.meal", "m.id")
                    .build(connection,
                            Optional.of(true),
                            Optional.of(true))){
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

}

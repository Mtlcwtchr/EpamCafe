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
import by.epam.mtlcwtchr.ecafe.verification.CheckedArguments;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MealIngredientRepository implements IMealIngredientRepository {

    @Override
    public List<Ingredient> getList() throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select("epam_cafe.meal_ingredient", "id", "name", "pic_url")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,"isActive"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(true))){
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
                    .select("epam_cafe.meal_ingredient", "id", "name", "pic_url")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,"isActive", "id"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(id),
                            Optional.of(true))){
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
                    .select("epam_cafe.meal_ingredient", "id", "name", "pic_url")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,"isActive", "name"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(name),
                            Optional.of(true))){
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
                    .insert("epam_cafe.meal_ingredient", "name", "pic_url")
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
                    .update("epam_cafe.meal_ingredient", "id","name", "pic_url")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, "isActive", "id"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(ingredient.getId()),
                            Optional.of(ingredient.getName()),
                            Optional.of(ingredient.getPictureUrl()),
                            Optional.of(ingredient.getId()),
                            Optional.of(true))){
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
                    .update("epam_cafe.meal_ingredient", "isActive")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, "id"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(false),
                            Optional.of(id))){
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
                    .select("epam_cafe.meal_ingredient", "id", "name", "pic_url")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,"isActive"), LogicConcatenator.AND)
                    .whereMaxId("epam_cafe.meal_ingredient")
                    .build(connection,
                            Optional.of(true))){
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

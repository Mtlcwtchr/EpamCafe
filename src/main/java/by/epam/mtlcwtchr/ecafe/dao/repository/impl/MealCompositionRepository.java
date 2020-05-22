package by.epam.mtlcwtchr.ecafe.dao.repository.impl;

import by.epam.mtlcwtchr.ecafe.bean.Ingredient;
import by.epam.mtlcwtchr.ecafe.bean.Meal;
import by.epam.mtlcwtchr.ecafe.dao.builder.PreparedStatementBuilder;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.Limiter;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LimiterMapGenerator;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LogicConcatenator;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.impl.ConnectionPool;
import by.epam.mtlcwtchr.ecafe.dao.repository.IMealCompositionRepository;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.mtlcwtchr.ecafe.verification.CheckedArguments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MealCompositionRepository implements IMealCompositionRepository {

    @Override
    public Optional<Meal> get(Meal meal) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select("epam_cafe.meal_composition AS c", "i.id", "i.name", "i.pic_url", "ingredient_mass")
                    .joining("epam_cafe.meal_ingredient  AS i", "fk_meal_ingredient_id", "i.id")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,"i.isActive",  "fk_meal_id"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(true),
                            Optional.of(meal.getId()))){
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    if (resultSet.first()) {
                        do {
                            meal.addIngredient(new Ingredient(
                                    resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getInt(4)));
                        } while (resultSet.next());
                    }
                    return Optional.of(meal);
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
    public Optional<Meal> update(Meal meal) throws DAOException {
        if(clear(meal))
            throw new DAOException("Meal " + meal + " can not be updated");
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .insert("epam_cafe.meal_composition", "fk_meal_id", "fk_meal_ingredient_id", "ingredient_mass")
                    .beginBatch(connection)
                    .addBatch(generateBatch(meal))
                    .endBatch()){
                    preparedStatement.executeBatch();
                    return Optional.of(meal);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    private boolean clear(Meal meal) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .delete("epam_cafe.meal_composition")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, "fk_meal_id"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(meal.getId()))){
                return preparedStatement.execute();
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    private ArrayList<Optional<?>[]> generateBatch(Meal meal){
        ArrayList<Optional<?>[]> optionalArrayList = new ArrayList<>();
        for (Ingredient ingredient : meal.getIngredients()) {
            optionalArrayList.add(new Optional[]{Optional.of(meal.getId()),Optional.of(ingredient.getId()), Optional.of(ingredient.getMass())});
        }
        return optionalArrayList;
    }

}

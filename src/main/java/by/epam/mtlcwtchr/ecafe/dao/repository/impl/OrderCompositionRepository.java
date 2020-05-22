package by.epam.mtlcwtchr.ecafe.dao.repository.impl;

import by.epam.mtlcwtchr.ecafe.bean.Category;
import by.epam.mtlcwtchr.ecafe.bean.Meal;
import by.epam.mtlcwtchr.ecafe.bean.Order;
import by.epam.mtlcwtchr.ecafe.dao.builder.PreparedStatementBuilder;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.Limiter;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LimiterMapGenerator;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LogicConcatenator;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.impl.ConnectionPool;
import by.epam.mtlcwtchr.ecafe.dao.repository.IOrderCompositionRepository;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.mtlcwtchr.ecafe.verification.CheckedArguments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class OrderCompositionRepository implements IOrderCompositionRepository {

    @Override
    public Optional<Order> get(Order order) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select("epam_cafe.order_composition AS o",
                            "c.id", "c.name", "c.pic_url",
                            "m.id", "meal_name", "meal_price", "m.pic_url")
                    .joining("epam_cafe.meal  AS m", "o.fk_meal_id", "m.id")
                    .joining("epam_cafe.meal_category  AS c", "m.fk_category_id", "c.id")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS,"m.isActive", "c.isActive", "fk_order_id"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(true),
                            Optional.of(true),
                            Optional.of(order.getId()))){
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    if (resultSet.first()) {
                        do {
                            order.addMeal(new Meal(
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
                    }
                    return Optional.of(order);
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
    public Optional<Order> update(Order order) throws DAOException {
        if(clear(order))
            throw new DAOException("Meal " + order + " can not be updated");
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .insert("epam_cafe.order_composition", "fk_order_id", "fk_meal_id", "meal_name", "meal_price")
                    .beginBatch(connection)
                    .addBatch(generateBatch(order))
                    .endBatch()){
                    preparedStatement.executeBatch();
                    return Optional.of(order);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    private boolean clear(Order order) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .delete("epam_cafe.order_composition")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, "fk_order_id"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(order.getId()))){
                return preparedStatement.execute();
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    private ArrayList<Optional<?>[]> generateBatch(Order order){
        ArrayList<Optional<?>[]> optionalArrayList = new ArrayList<>();
        for (Meal meal : order.getMeals()) {
            optionalArrayList.add(new Optional[]{Optional.of(order.getId()),Optional.of(meal.getId()), Optional.of(meal.getName()), Optional.of(meal.getPrice())});
        }
        return optionalArrayList;
    }

}

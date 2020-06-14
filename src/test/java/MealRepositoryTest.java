import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.factory.DAOFactory;
import by.epam.mtlcwtchr.ecafe.dao.factory.DAOFactoryType;
import by.epam.mtlcwtchr.ecafe.entity.Category;
import by.epam.mtlcwtchr.ecafe.entity.Meal;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class MealRepositoryTest {

    private final Meal savingMeal = new Meal(45, "someMealName", 200,
            new Category(1, "No Category", "noCategoryPic"), "someUrl");

    @Test
    public void MealRepositoryTest_Save_Meal_Matching_Criteria() throws DAOException {
        final Optional<Meal> savedMeal =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getMealRepository().save(savingMeal);
        Assert.assertTrue(savedMeal.isPresent());
    }

    @Test(expected = DAOException.class)
    public void MealRepositoryTest_Save_Meal_Duplicate_Data() throws DAOException {
        DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getMealRepository().save(savingMeal);
    }

    @Test
    public void MealRepositoryTest_Find_Meal_Ex_Id() throws DAOException {
        final Optional<Meal> foundMeal =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getMealRepository().find(savingMeal.getId());
        Assert.assertTrue(foundMeal.isPresent());
    }

    @Test
    public void MealRepositoryTest_Find_Meal_Not_Ex_Id() throws DAOException {
        final Optional<Meal> foundMeal =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getMealRepository().find(100);
        Assert.assertFalse(foundMeal.isPresent());
    }

    @Test
    public void MealRepositoryTest_Find_Meal_Ex_Name() throws DAOException {
        final Optional<Meal> foundMeal =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getMealRepository().find(savingMeal.getName());
        Assert.assertTrue(foundMeal.isPresent());
    }

    @Test
    public void MealRepositoryTest_Find_Meal_Not_Ex_Name() throws DAOException {
        final Optional<Meal> foundMeal =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getMealRepository().find("invalidName");
        Assert.assertFalse(foundMeal.isPresent());
    }

}

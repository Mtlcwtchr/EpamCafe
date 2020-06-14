import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.factory.DAOFactory;
import by.epam.mtlcwtchr.ecafe.dao.factory.DAOFactoryType;
import by.epam.mtlcwtchr.ecafe.entity.Ingredient;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class IngredientRepositoryTest {

    private final Ingredient savingIngredient = new Ingredient(31, "someName", "someUrl");

    @Test
    public void IngredientRepositoryTest_Save_Ingredient_Matching_Criteria() throws DAOException {
        final Optional<Ingredient> savedIngredient =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getMealIngredientRepository().save(savingIngredient);
        Assert.assertTrue(savedIngredient.isPresent());
    }

    @Test(expected = DAOException.class)
    public void IngredientRepositoryTest_Save_Ingredient_Duplicate_Data() throws DAOException {
        DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getMealIngredientRepository().save(savingIngredient);
    }

    @Test
    public void IngredientRepositoryTest_Find_Ingredient_Ex_Id() throws DAOException {
        final Optional<Ingredient> foundIngredient =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getMealIngredientRepository().find(savingIngredient.getId());
        Assert.assertTrue(foundIngredient.isPresent());
    }

    @Test
    public void IngredientRepositoryTest_Find_Ingredient_Not_Ex_Id() throws DAOException {
        final Optional<Ingredient> foundIngredient =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getMealIngredientRepository().find(100);
        Assert.assertFalse(foundIngredient.isPresent());
    }

    @Test
    public void IngredientRepositoryTest_Find_Ingredient_Ex_Name() throws DAOException {
        final Optional<Ingredient> foundIngredient =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getMealIngredientRepository().find(savingIngredient.getName());
        Assert.assertTrue(foundIngredient.isPresent());
    }

    @Test
    public void IngredientRepositoryTest_Find_Ingredient_Not_Ex_Name() throws DAOException {
        final Optional<Ingredient> foundIngredient =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getMealIngredientRepository().find("invalidName");
        Assert.assertFalse(foundIngredient.isPresent());
    }

}

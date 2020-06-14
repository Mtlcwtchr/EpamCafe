import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.factory.DAOFactory;
import by.epam.mtlcwtchr.ecafe.dao.factory.DAOFactoryType;
import by.epam.mtlcwtchr.ecafe.entity.Category;
import by.epam.mtlcwtchr.ecafe.entity.Ingredient;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class CategoryRepositoryTest {

    private final Category savingCategory = new Category(44, "someName", "someUrl");

    @Test
    public void CategoryRepositoryTest_Save_Category_Matching_Criteria() throws DAOException {
        final Optional<Category> savedCategory =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getMealCategoryRepository().save(savingCategory);
        Assert.assertTrue(savedCategory.isPresent());
    }

    @Test(expected = DAOException.class)
    public void CategoryRepositoryTest_Save_Category_Duplicate_Data() throws DAOException {
        DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getMealCategoryRepository().save(savingCategory);
    }

    @Test
    public void CategoryRepositoryTest_Find_Category_Ex_Id() throws DAOException {
        final Optional<Category> foundCategory =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getMealCategoryRepository().find(savingCategory.getId());
        Assert.assertTrue(foundCategory.isPresent());
    }

    @Test
    public void CategoryRepositoryTest_Find_Category_Not_Ex_Id() throws DAOException {
        final Optional<Category> foundCategory =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getMealCategoryRepository().find(100);
        Assert.assertFalse(foundCategory.isPresent());
    }

    @Test
    public void CategoryRepositoryTest_Find_Category_Ex_Name() throws DAOException {
        final Optional<Category> foundCategory =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getMealCategoryRepository().find(savingCategory.getName());
        Assert.assertTrue(foundCategory.isPresent());
    }

    @Test
    public void CategoryRepositoryTest_Find_Category_Not_Ex_Name() throws DAOException {
        final Optional<Category> foundCategory =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getMealCategoryRepository().find("invalidName");
        Assert.assertFalse(foundCategory.isPresent());
    }

}

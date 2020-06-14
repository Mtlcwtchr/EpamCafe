import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.factory.DAOFactory;
import by.epam.mtlcwtchr.ecafe.dao.factory.DAOFactoryType;
import by.epam.mtlcwtchr.ecafe.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class UserRepositoryTest {

    private final User savingUser =
            new User(3,"someUsername", "somePassword", "someEmail", "somePhone");

    @Test
    public void UserRepositoryTest_Save_User_Matching_Criteria() throws DAOException {
        final Optional<User> savedUser =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getUserRepository().save(savingUser);
        Assert.assertTrue(savedUser.isPresent());
    }

    @Test(expected = DAOException.class)
    public void UserRepositoryTest_Save_User_Duplicate_Data() throws DAOException {
        DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getUserRepository().save(savingUser);
    }


    @Test
    public void UserRepositoryTest_Find_Ex_Id() throws DAOException {
        final Optional<User> foundUser =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getUserRepository().find(savingUser.getId());
        Assert.assertTrue(foundUser.isPresent());
    }

    @Test
    public void UserRepositoryTest_Find_Ex_Uname() throws DAOException {
        final Optional<User> foundUser =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getUserRepository().find(savingUser.getUsername());
        Assert.assertTrue(foundUser.isPresent());
    }

    @Test
    public void UserRepositoryTest_Find_Not_Ex_Id() throws DAOException {
        final Optional<User> notFound =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getUserRepository().find(3);
        Assert.assertFalse(notFound.isPresent());
    }

    @Test
    public void UserRepositoryTest_Find_Not_Ex_Uname() throws DAOException {
        final Optional<User> notFound =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getUserRepository().find(4);
        Assert.assertFalse(notFound.isPresent());
    }

    @Test
    public void UserRepositoryTest_Get_List() throws DAOException {
        final List<User> list =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getUserRepository().getList();
        Assert.assertFalse(list.isEmpty());
    }

}

import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.factory.DAOFactory;
import by.epam.mtlcwtchr.ecafe.dao.factory.DAOFactoryType;
import by.epam.mtlcwtchr.ecafe.entity.Client;
import by.epam.mtlcwtchr.ecafe.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class ClientRepositoryTest {

    private final Client savingClient = new Client(
            new User(2, "someUsername", "somePassword", "someEmail", "somePhone"),
            "someName");

    @Test
    public void ClientRepositoryTest_Save_Client_Matching_Criteria() throws DAOException {
        final Optional<Client> savedClient =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getClientRepository().save(savingClient);
        Assert.assertTrue(savedClient.isPresent());
    }

    @Test(expected = DAOException.class)
    public void ClientRepositoryTest_Save_Client_Duplicate_Data() throws DAOException {
        DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getClientRepository().save(savingClient);
    }

    @Test
    public void ClientRepositoryTest_Find_Client_Ex_Id() throws DAOException {
        final Optional<Client> foundClient =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getClientRepository().find(savingClient.getId());
        Assert.assertTrue(foundClient.isPresent());
    }

    @Test
    public void ClientRepositoryTest_Find_Client_Not_Ex_Id() throws DAOException {
        final Optional<Client> foudClient =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getClientRepository().find(3);
        Assert.assertFalse(foudClient.isPresent());
    }

    @Test
    public void ClientRepositoryTest_Find_Client_Ex_Name() throws DAOException {
        final Optional<Client> foundClient =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getClientRepository().find(savingClient.getName());
        Assert.assertTrue(foundClient.isPresent());
    }

    @Test
    public void ClientRepositoryTest_Find_Client_Not_Ex_Name() throws DAOException {
        final Optional<Client> foudClient =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getClientRepository().find("notValidName");
        Assert.assertFalse(foudClient.isPresent());
    }

    @Test
    public void ClientRepositoryTest_Find_Client_Ex_Uname() throws DAOException {
        final Optional<Client> foundClient =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getClientRepository().find(savingClient.getUser());
        Assert.assertTrue(foundClient.isPresent());
    }

    @Test
    public void ClientRepositoryTest_Find_Client_Not_Ex_Uname() throws DAOException {
        final Optional<Client> foudClient =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getClientRepository().find(new User("notValidUname", "notValidPassword"));
        Assert.assertFalse(foudClient.isPresent());
    }

    @Test
    public void ClientRepositoryTest_Get_List() throws DAOException {
        final List<Client> list = DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getClientRepository().getList();
        Assert.assertFalse(list.isEmpty());
    }




}

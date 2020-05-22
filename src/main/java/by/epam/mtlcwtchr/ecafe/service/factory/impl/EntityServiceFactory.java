package by.epam.mtlcwtchr.ecafe.service.factory.impl;

import by.epam.mtlcwtchr.ecafe.dao.factory.DAOFactory;
import by.epam.mtlcwtchr.ecafe.dao.factory.DAOFactoryType;
import by.epam.mtlcwtchr.ecafe.service.*;
import by.epam.mtlcwtchr.ecafe.service.factory.IEntityServiceFactory;
import by.epam.mtlcwtchr.ecafe.service.impl.*;

public class EntityServiceFactory implements IEntityServiceFactory {

    private final DAOFactory daoFactory;

    private EntityServiceFactory(DAOFactory daoFactory){
        this.daoFactory = daoFactory;
    }

    public static EntityServiceFactory getInstance(){
        return EntityServiceFactoryInstanceHandler.ENTITY_SERVICE_FACTORY_INSTANCE;
    }

    @Override
    public IUserService getUserService() {
        return new UserService(
                daoFactory.getUserRepository());
    }

    @Override
    public IClientService getClientService() {
        return new ClientService(
                daoFactory.getClientRepository());
    }

    @Override
    public IMealCategoryService getMealCategoryService() {
        return new MealCategoryService(
                daoFactory.getMealCategoryRepository());
    }

    @Override
    public IMealService getMealService() {
        return new MealService(
                daoFactory.getMealRepository(),
                daoFactory.getMealCompositionRepository());
    }

    @Override
    public IMealIngredientService getMealIngredientService() {
        return new MealIngredientService(
                daoFactory.getMealIngredientRepository());
    }

    @Override
    public IOrderService getOrderService() {
        return new OrderService(
                daoFactory.getOrderRepository(),
                daoFactory.getOrderCompositionRepository(),
                daoFactory.getMealCompositionRepository());
    }

    private static class EntityServiceFactoryInstanceHandler{

        private static final EntityServiceFactory ENTITY_SERVICE_FACTORY_INSTANCE =
                new EntityServiceFactory(DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory));

    }

}

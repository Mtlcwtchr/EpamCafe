package by.epam.mtlcwtchr.ecafe.service.factory;

import by.epam.mtlcwtchr.ecafe.service.*;

public abstract class IEntityServiceFactory {

    public abstract IUserService getUserService();
    public abstract IClientService getClientService();
    public abstract IMealCategoryService getMealCategoryService();
    public abstract IMealService getMealService();
    public abstract IMealIngredientService getMealIngredientService();
    public abstract IOrderService getOrderService();
    public abstract IClientCommentService getClientCommentService();
    public abstract IReservationService getReservationService();
    public abstract IHallService getHallService();

}

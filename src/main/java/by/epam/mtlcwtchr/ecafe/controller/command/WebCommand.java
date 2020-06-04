package by.epam.mtlcwtchr.ecafe.controller.command;

import by.epam.mtlcwtchr.ecafe.controller.command.impl.*;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public abstract class WebCommand implements IWebExecutable{

    private final ServletRequest request;
    private final ServletResponse response;

    public WebCommand(ServletRequest request, ServletResponse response){
        this.request = request;
        this.response = response;
    }

    public abstract void executeGet() throws ControllerException;
    public abstract void executePost() throws ControllerException;

    public static WebCommand of(WebCommandType webCommandType, ServletRequest request, ServletResponse response){
        return switch (webCommandType){
            case HOME_COMMAND -> new HomeWebCommand(request, response);
            case PROFILE_COMMAND -> new ProfileWebCommand(request, response);
            case SIGN_IN_COMMAND -> new SignInWebCommand(request, response);
            case SIGN_UP_COMMAND -> new SignUpWebCommand(request, response);
            case SIGN_OUT_COMMAND -> new SignOutWebCommand(request, response);
            case CHANGE_PROFILE_COMMAND -> new ChangeProfileWebCommand(request, response);
            case MEALS_COMMAND -> new MealsWebCommand(request, response);
            case CATEGORIES_COMMAND -> new CategoriesWebCommand(request, response);
            case ADD_MEAL_TO_ORDER_COMMAND -> new AddMealToOrderWebCommand(request, response);
            case LOAD_IMAGE_COMMAND -> new LoadImageWebCommand(request, response);
            case REMOVE_MEAL_FROM_ORDER_COMMAND -> new RemoveMealFromOrderWebCommand(request, response);
            case CLIENT_ORDER_COMMAND -> new ClientOrderWebCommand(request, response);
            case CLIENT_ORDERS_COMMAND -> new ClientOrdersWebCommand(request, response);
            case PAYMENT_COMMAND -> new PaymentWebCommand(request, response);
            case PLACE_ORDER_COMMAND -> new PlaceOrderWebCommand(request, response);
            case AORDERS_COMMAND -> new AdminOrdersWebCommand(request, response);
        };
    }

    public ServletRequest getRequest() {
        return request;
    }
    public ServletResponse getResponse() {
        return response;
    }

}

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
            case SIGN_IN_COMMAND -> new SignInWebCommand(request, response);
            case SIGN_UP_COMMAND -> new SignUpWebCommand(request, response);
            case SIGN_OUT_COMMAND -> new SignOutWebCommand(request, response);
            case HOME_COMMAND -> new HomeWebCommand(request, response);
            case MEALS_COMMAND -> new MealsWebCommand(request, response);
            case INGREDIENTS_COMMAND -> new IngredientsWebCommand(request, response);
            case CATEGORIES_COMMAND -> new CategoriesWebCommand(request, response);
        };
    }

    public ServletRequest getRequest() {
        return request;
    }
    public ServletResponse getResponse() {
        return response;
    }

}

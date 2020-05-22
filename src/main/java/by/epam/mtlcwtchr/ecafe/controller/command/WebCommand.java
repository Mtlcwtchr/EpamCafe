package by.epam.mtlcwtchr.ecafe.controller.command;

import by.epam.mtlcwtchr.ecafe.controller.command.impl.HomeWebCommand;
import by.epam.mtlcwtchr.ecafe.controller.command.impl.JoinWebCommand;
import by.epam.mtlcwtchr.ecafe.controller.command.impl.SignInWebCommand;
import by.epam.mtlcwtchr.ecafe.controller.command.impl.SignUpWebCommand;
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
            case SIGN_IN -> new SignInWebCommand(request, response);
            case SIGN_UP -> new SignUpWebCommand(request, response);
            case HOME -> new HomeWebCommand(request, response);
            case JOIN -> new JoinWebCommand(request, response);
        };
    }

    public ServletRequest getRequest() {
        return request;
    }
    public ServletResponse getResponse() {
        return response;
    }

}

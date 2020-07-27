package by.epam.mtlcwtchr.ecafe.controller.command;

import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;

public interface IWebExecutable {

    @ExceptionableBeingLogged
    void executeGet() throws ControllerException;
    @ExceptionableBeingLogged
    void executePost() throws ControllerException;

}

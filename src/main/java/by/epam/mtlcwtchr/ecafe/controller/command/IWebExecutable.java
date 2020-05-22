package by.epam.mtlcwtchr.ecafe.controller.command;

import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;

public interface IWebExecutable {

    @ExceptionableBeingLogged
    void executeGet() throws Exception;
    @ExceptionableBeingLogged
    void executePost() throws Exception;

}

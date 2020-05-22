package by.epam.mtlcwtchr.ecafe.service.command;

import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;

public interface IExecutable {

    @ExceptionableBeingLogged
    void execute() throws Exception;

}

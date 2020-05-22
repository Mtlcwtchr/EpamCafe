package by.epam.mtlcwtchr.ecafe.service.command.impl;

import by.epam.mtlcwtchr.ecafe.service.command.Command;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.exception.ParameterRequiredException;
import by.epam.mtlcwtchr.ecafe.service.authorization.impl.AuthorizationService;

import java.util.Arrays;

public class AuthorizateCommand extends Command {

    @Override
    public void execute() throws ServiceException {
        try {
            if (getCommandParams().getAllOfType(String.class).isEmpty()) {
                throw new ParameterRequiredException(
                        "Required parameter 'username' of type " + String.class
                        + " parameter 'password' of type " + String.class
                        + " or"
                        + " parameter 'username' of type " + String.class
                        + " parameter 'password' of type " + String.class
                        + " parameter 'email' of type " + String.class
                        + " parameter 'phone' of type " + String.class
                        + " parameter 'name' of type " + String.class);
            } else {
                initResult(AuthorizationService.getInstance().authorizate(Arrays.copyOf(
                                        getCommandParams().getAllOfType(String.class).toArray(),
                                        getCommandParams().getAllOfType(String.class).size(),
                                        String[].class)));
            }
        } catch (by.epam.mtlcwtchr.ecafe.service.exception.ServiceException ex){
            throw new ServiceException(ex);
        }
    }

}

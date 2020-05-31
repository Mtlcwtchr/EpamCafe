package by.epam.mtlcwtchr.ecafe.service.command.impl;

import by.epam.mtlcwtchr.ecafe.service.command.Command;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.exception.ParameterRequiredException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

public class GetUserCommand extends Command {

    @Override
    public void execute() throws ServiceException {
        try {
            if (getCommandParams().getOfType(String.class).isPresent()) {
                initResult(
                        EntityServiceFactory
                                .getInstance()
                                .getUserService()
                                .find((String)getCommandParams().getOfType(String.class).get()).orElseThrow());
            } else if (getCommandParams().getOfType(Integer.class).isPresent()) {
                initResult(
                        EntityServiceFactory
                                .getInstance()
                                .getUserService()
                                .find((Integer) getCommandParams().getOfType(Integer.class).get()).orElseThrow());
            } else throw new ParameterRequiredException(
                    "Required parameter 'name' of type "
                            + String.class
                            + " or 'id' of type "
                            + Integer.class);
        } catch (by.epam.mtlcwtchr.ecafe.service.exception.ServiceException ex){
            throw new ServiceException(ex);
        }
    }

}

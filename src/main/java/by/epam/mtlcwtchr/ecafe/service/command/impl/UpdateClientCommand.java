package by.epam.mtlcwtchr.ecafe.service.command.impl;

import by.epam.mtlcwtchr.ecafe.entity.Client;
import by.epam.mtlcwtchr.ecafe.service.command.Command;
import by.epam.mtlcwtchr.ecafe.service.validation.RequiredPromoted;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.exception.ParameterRequiredException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

@RequiredPromoted
public class UpdateClientCommand extends Command {

    @Override
    public void execute() throws ServiceException {
        try {
            if (getCommandParams().getOfType(Client.class).isPresent()) {
                initResult(EntityServiceFactory
                        .getInstance()
                        .getClientService()
                        .update((Client) getCommandParams().getOfType(Client.class).get()).orElseThrow());
            } else {
                throw new ParameterRequiredException("Required parameter 'client' of type " + Client.class);
            }
        } catch (by.epam.mtlcwtchr.ecafe.service.exception.ServiceException ex){
            throw new ServiceException(ex);
        }
    }

}

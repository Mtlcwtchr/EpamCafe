package by.epam.mtlcwtchr.ecafe.service.command.impl;

import by.epam.mtlcwtchr.ecafe.service.command.Command;
import by.epam.mtlcwtchr.ecafe.service.validation.RequiredPromoted;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.exception.ParameterRequiredException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

@RequiredPromoted
public class DeleteCategoryCommand extends Command {

    @Override
    public void execute() throws ServiceException {
        try {
            if (getCommandParams().getOfType(Integer.class).isPresent()) {
                initResult(EntityServiceFactory
                        .getInstance()
                        .getMealCategoryService()
                        .delete((Integer) getCommandParams().getOfType(Integer.class).get()));
            } else {
                throw new ParameterRequiredException("Required parameter 'id' of type " + Integer.class);
            }
        } catch (by.epam.mtlcwtchr.ecafe.service.exception.ServiceException ex){
            throw new ServiceException(ex);
        }
    }

}
package by.epam.mtlcwtchr.ecafe.service.command.impl;

import by.epam.mtlcwtchr.ecafe.service.command.Command;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

public class GetCategoriesCommand extends Command {

    @Override
    public void execute() throws ServiceException {
        try {
            initResult(
                    EntityServiceFactory
                            .getInstance()
                            .getMealCategoryService()
                            .getList());
        } catch (by.epam.mtlcwtchr.ecafe.service.exception.ServiceException ex){
            throw new ServiceException(ex);
        }
    }

}

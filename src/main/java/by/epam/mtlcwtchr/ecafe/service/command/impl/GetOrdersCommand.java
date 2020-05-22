package by.epam.mtlcwtchr.ecafe.service.command.impl;

import by.epam.mtlcwtchr.ecafe.service.command.Command;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

public class GetOrdersCommand extends Command {

    @Override
    public void execute() throws ServiceException {
        try {
            if (getCommandParams().getOfType(String.class).isPresent()) {
                initResult(
                        EntityServiceFactory
                        .getInstance()
                        .getOrderService()
                        .getList((String)getCommandParams().getOfType(String.class).get()));
            } else {
                initResult(
                        EntityServiceFactory
                        .getInstance()
                        .getOrderService()
                        .getList());
            }
        } catch (by.epam.mtlcwtchr.ecafe.service.exception.ServiceException ex){
            throw new ServiceException(ex);
        }
    }

}

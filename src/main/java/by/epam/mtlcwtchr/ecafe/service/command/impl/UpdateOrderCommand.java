package by.epam.mtlcwtchr.ecafe.service.command.impl;

import by.epam.mtlcwtchr.ecafe.bean.Order;
import by.epam.mtlcwtchr.ecafe.service.command.Command;
import by.epam.mtlcwtchr.ecafe.service.validation.RequiredPromoted;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.exception.ParameterRequiredException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

@RequiredPromoted
public class UpdateOrderCommand extends Command {

    @Override
    public void execute() throws ServiceException {
        try {
            if (getCommandParams().getOfType(Order.class).isPresent()) {
                initResult(EntityServiceFactory
                        .getInstance()
                        .getOrderService()
                        .update((Order) getCommandParams().getOfType(Order.class).get()));
            } else {
                throw new ParameterRequiredException("Required parameter 'order' of type " + Order.class);
            }
        } catch (by.epam.mtlcwtchr.ecafe.service.exception.ServiceException ex){
            throw new ServiceException(ex);
        }
    }

}
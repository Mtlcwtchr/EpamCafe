package by.epam.mtlcwtchr.ecafe.service.command.impl;

import by.epam.mtlcwtchr.ecafe.entity.Meal;
import by.epam.mtlcwtchr.ecafe.service.command.Command;
import by.epam.mtlcwtchr.ecafe.service.validation.RequiredPromoted;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.exception.ParameterRequiredException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

@RequiredPromoted
public class UpdateMealCommand extends Command {

    @Override
    public void execute() throws ServiceException {
        try {
            if (getCommandParams().getOfType(Meal.class).isPresent()) {
                initResult(EntityServiceFactory
                        .getInstance()
                        .getMealService()
                        .update((Meal) getCommandParams().getOfType(Meal.class).get()).orElseThrow());
            } else {
                throw new ParameterRequiredException("Required parameter 'meal' of type " + Meal.class);
            }
        } catch (by.epam.mtlcwtchr.ecafe.service.exception.ServiceException ex){
            throw new ServiceException(ex);
        }
    }

}

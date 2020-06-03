package by.epam.mtlcwtchr.ecafe.service.command.impl;

import by.epam.mtlcwtchr.ecafe.entity.Ingredient;
import by.epam.mtlcwtchr.ecafe.service.command.Command;
import by.epam.mtlcwtchr.ecafe.service.validation.RequiredPromoted;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.exception.ParameterRequiredException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

@RequiredPromoted
public class SaveIngredientCommand extends Command {

    @Override
    public void execute() throws ServiceException {
        try {
            if (getCommandParams().getOfType(Ingredient.class).isPresent()) {
                initResult(EntityServiceFactory
                        .getInstance()
                        .getMealIngredientService()
                        .save((Ingredient) getCommandParams().getOfType(Ingredient.class).get()).orElseThrow());
            } else {
                throw new ParameterRequiredException("Required parameter 'ingredient' of type " + Ingredient.class);
            }
        } catch (by.epam.mtlcwtchr.ecafe.service.exception.ServiceException ex){
            throw new ServiceException(ex);
        }
    }

}

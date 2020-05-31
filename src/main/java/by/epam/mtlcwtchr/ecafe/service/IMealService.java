package by.epam.mtlcwtchr.ecafe.service;

import by.epam.mtlcwtchr.ecafe.bean.Meal;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.exception.UnsupportedKeyTypeException;
import by.epam.mtlcwtchr.ecafe.verification.CheckedArguments;

import java.util.Optional;

public abstract class IMealService implements IEntityService<Meal> {

    @Override
    public Optional<Meal> findAny(Object key) throws ServiceException {
        return switch (SupportedKeyTypes.of(key.getClass())){
            case INTEGER -> find((Integer) key);
            case STRING -> find((String) key);
            default -> throw new UnsupportedKeyTypeException("Unsupported key type " + key.getClass() +
                    " expected " + Integer.class + " or " + String.class);
        };
    }

    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    public abstract Optional<Meal> find(int id) throws ServiceException;
    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    public abstract Optional<Meal> find(String mealName) throws ServiceException;


}

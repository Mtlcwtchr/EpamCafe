package by.epam.mtlcwtchr.ecafe.service;

import by.epam.mtlcwtchr.ecafe.entity.Comment;
import by.epam.mtlcwtchr.ecafe.entity.Order;
import by.epam.mtlcwtchr.ecafe.entity.User;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.exception.UnsupportedKeyTypeException;
import by.epam.mtlcwtchr.ecafe.verification.CheckedArguments;

import java.util.List;
import java.util.Optional;

public abstract class IClientCommentService implements IEntityService<Comment> {

    @Override
    public Optional<Comment> findAny(Object key) throws ServiceException {
        return switch (SupportedKeyTypes.of(key.getClass())){
            case INTEGER -> find((Integer) key);
            case STRING -> find((String) key);
            default -> throw new UnsupportedKeyTypeException("Unsupported key type " + key.getClass() +
                    " expected " + Integer.class + " or " + String.class);
        };
    }


    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    public abstract List<Comment> getList(String clientPhone) throws ServiceException;


    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    public abstract Optional<Comment> find(int id) throws ServiceException;
    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    public abstract Optional<Comment> find(String authorPhone) throws ServiceException;

}

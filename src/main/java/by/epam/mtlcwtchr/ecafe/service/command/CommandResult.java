package by.epam.mtlcwtchr.ecafe.service.command;

import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.exception.UninitializedCommandResultIterationException;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;

public class CommandResult implements Iterable<Object>{

    ArrayList<Object> result = new ArrayList<>();
    Integer pointer;

    CommandResult(Object... result){
        Collections.addAll(this.result, result);
    }

    @Override
    public @NotNull Iterator<Object> iterator() {
        return result.iterator();
    }

    @Override
    public void forEach(Consumer<? super Object> action) {
        result.forEach(action);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" + result.toString();
    }

    public boolean contains(Object object){
        return result.contains(object);
    }

    @ExceptionableBeingLogged
    public Object get() throws ServiceException {
        if(Objects.isNull(pointer)){
            throw new UninitializedCommandResultIterationException("Attempting to access an unreleased iteration");
        }
        return result.get(pointer);
    }

    public boolean first() {
        return Objects.nonNull(result.get(pointer = 0));
    }

    @ExceptionableBeingLogged
    public boolean next() throws ServiceException {
        if(Objects.isNull(pointer)){
            throw new UninitializedCommandResultIterationException("Attempting to access an unreleased iteration");
        }
        return ++pointer < result.size();
    }

    public List<?> getList() {
        return new ArrayList<>(result);
    }

}
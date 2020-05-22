package by.epam.mtlcwtchr.ecafe.service.command;

import java.util.*;
import java.util.stream.Collectors;

public class CommandParams {

    ArrayList<Object> params;

    CommandParams(Object... params){
        this.params = new ArrayList<>(Arrays.asList(params));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" + params.toString();
    }

    public boolean contains(Object object){
        return params.contains(object);
    }
    public ArrayList<?> getAllOfType(Class<?> type){
        return params.stream()
                     .filter(e->e.getClass().equals(type))
                     .collect(Collectors.toCollection(ArrayList::new));
    }
    public Optional<Object> getOfType(Class<?> type){
        return params.stream().filter(e->e.getClass().equals(type)).findFirst();
    }

}
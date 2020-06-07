package by.epam.mtlcwtchr.ecafe.entity;

import java.util.function.Consumer;

public abstract class Actor {

    public void act(){

    }

    public abstract boolean isPromoted();

    public abstract int getId();

    public void ifPromoted(Consumer<? super Object> action){
        if (isPromoted()) {
            action.accept(this);
        }
    }

}

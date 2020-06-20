package by.epam.mtlcwtchr.ecafe.config;

import java.lang.reflect.InvocationTargetException;

public class DependenciesLoader {

    private static final DependenciesLoader DEPENDENCIES_LOADER_INSTANCE = new DependenciesLoader();

    public static DependenciesLoader getInstance() {
        return DEPENDENCIES_LOADER_INSTANCE;
    }

    private DependenciesLoader() throws Error {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new Error(e);
        }
    }

}

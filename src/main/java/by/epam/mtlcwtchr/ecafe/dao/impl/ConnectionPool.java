package by.epam.mtlcwtchr.ecafe.dao.impl;

import by.epam.mtlcwtchr.ecafe.config.DAOConfiguration;
import by.epam.mtlcwtchr.ecafe.dao.IConnectionPool;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOConnectionPoolRisingException;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public enum ConnectionPool implements IConnectionPool {

    CONNECTION_POOL_INSTANCE;

    private final List<Connection> availableConnections = new ArrayList<>(DAOConfiguration.INSTANCE.getInitPoolCapacity());
    private final Set<Connection> involvedConnections = new HashSet<>(DAOConfiguration.INSTANCE.getInitPoolCapacity());

    ConnectionPool(){
        try {
            raiseConnections(DAOConfiguration.INSTANCE.getInitPoolCapacity());
        } catch (DAOException ignored){
        }
    }

    public Connection retrieveConnection() throws DAOException{
        Connection connection = null;
        if(availableConnections.size()!=0){
            connection = availableConnections.get(0);
            if(Objects.nonNull(connection)){
                availableConnections.remove(0);
                involvedConnections.add(connection);
            }
        } else if(availableConnections.size()+involvedConnections.size() < DAOConfiguration.INSTANCE.getMaxPoolSize()){
                raiseConnections(DAOConfiguration.INSTANCE.getPoolIncreaseStep());
        }
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        if(Objects.nonNull(connection)&&involvedConnections.contains(connection)) {
            try {
                involvedConnections.remove(connection);
                availableConnections.add(connection);
            } catch (Exception ex) {
                return false;
            }
        }
        return true;
    }

    public boolean isInvolved(Connection connection){
        return involvedConnections.contains(connection);
    }

    @ExceptionableBeingLogged("Data access object")
    private void raiseConnections(int initialCapacity) throws DAOConnectionPoolRisingException {
        for(int i=0; i<initialCapacity; ++i){
            try{
                availableConnections.add(new Connection$Proxy(DriverManager.getConnection(
                        DAOConfiguration.INSTANCE.getDbUrl(),
                        DAOConfiguration.INSTANCE.getDbUser(),
                        DAOConfiguration.INSTANCE.getDbPassword())));
            } catch (SQLException ex){
                throw new DAOConnectionPoolRisingException(ex);
            }
        }
    }

    public void shutdown() {
        involvedConnections.forEach(e-> {
            try {
                e.close();
            } catch (SQLException ignored) {

            }
        });
       availableConnections.forEach(e-> {
           try {
               ((Connection$Proxy) e).shutdown();
           } catch (SQLException ignored) {

           }
       });

    }

}

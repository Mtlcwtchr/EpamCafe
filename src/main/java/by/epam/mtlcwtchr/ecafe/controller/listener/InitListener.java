package by.epam.mtlcwtchr.ecafe.controller.listener;

import by.epam.mtlcwtchr.ecafe.config.DependenciesLoader;
import by.epam.mtlcwtchr.ecafe.dao.impl.ConnectionPool;
import by.epam.mtlcwtchr.ecafe.logging.aspect.LoggingAspect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener("Application initialization listener")
public class InitListener implements ServletContextListener {

    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Loaded dependencies " + DependenciesLoader.getInstance());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info(this + " context destroyed");
        ConnectionPool.CONNECTION_POOL_INSTANCE.shutdown();
    }
}

package by.epam.mtlcwtchr.ecafe.controller.servlet;

import by.epam.mtlcwtchr.ecafe.config.DependenciesLoader;
import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.command.WebCommandType;
import by.epam.mtlcwtchr.ecafe.controller.filter.CommonUrlFilter;
import by.epam.mtlcwtchr.ecafe.dao.impl.ConnectionPool;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ApplicationServlet", urlPatterns = "/app")
public class CommonServlet extends HttpServlet {

    @Override
    public void init() {
        System.out.println("Loaded " + DependenciesLoader.getInstance());
    }

    @Override
    @ExceptionableBeingLogged
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            final Command webCommand = Command.of((WebCommandType) req.getAttribute(CommonUrlFilter.COMMAND_ATTRIBUTE), req, resp);
            webCommand.executeGet();
        } catch (Exception ex){
            ex.printStackTrace();
            try {
                resp.sendRedirect(req.getServletContext().getContextPath() + "/something_went_wrong");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @ExceptionableBeingLogged
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try{
            final Command webCommand = Command.of((WebCommandType) req.getAttribute(CommonUrlFilter.COMMAND_ATTRIBUTE), req, resp);
            webCommand.executePost();
        } catch (Exception ex){
            ex.printStackTrace();
            try {
                resp.sendRedirect(req.getServletContext().getContextPath() + "/something_went_wrong");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {
        System.out.println("Destroyed " + this);
        ConnectionPool.CONNECTION_POOL_INSTANCE.shutdown();
    }

}
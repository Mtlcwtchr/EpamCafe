package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.service.authorization.impl.AuthorizationService;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpCommand extends Command {

    public SignUpCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            getRequest().getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {
        try {
            ((HttpServletRequest) getRequest()).getSession().setAttribute("actor",
                    AuthorizationService.getInstance().authorize(
                    getRequest().getParameter("username"),
                    getRequest().getParameter("password"),
                    getRequest().getParameter("email"),
                    getRequest().getParameter("phone"),
                    getRequest().getParameter("name")));
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/");
        } catch (ServiceException | IOException ex) {
            getRequest().setAttribute("error", "Неверные данные или данный пользователь уже зарегестрирован");
            executeGet();
        }
    }

}
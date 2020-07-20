package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class ReviewsCommand extends Command {

    public ReviewsCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            getRequest().setAttribute("comments",
                    EntityServiceFactory.getInstance().getClientCommentService().getList());
            getRequest().getRequestDispatcher("/WEB-INF/jsp/admin/adminreviews.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {
        executeGet();
    }

}

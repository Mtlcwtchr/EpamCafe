package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.AdminCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Objects;

public class ReviewsCommand extends AdminCommand {

    private static final int elementsOfPage = 5;

    public ReviewsCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try {
            if(Objects.nonNull(getRequest().getParameter("page")) &&
                    !getRequest().getParameter("page").isBlank() &&
                    !getRequest().getParameter("page").isEmpty() &&
                    getRequest().getParameter("page").matches("[0-9]++")) {
                getRequest().setAttribute("comments",
                        EntityServiceFactory.getInstance().getClientCommentService().getList(elementsOfPage, Integer.parseInt(getRequest().getParameter("page"))));
            } else {
                getRequest().setAttribute("comments",
                        EntityServiceFactory.getInstance().getClientCommentService().getList(elementsOfPage, 1));
            }
            final int count = EntityServiceFactory.getInstance().getClientCommentService().getCount() / elementsOfPage;
            getRequest().setAttribute("count", count == 0 ? 1 : count);
            getRequest().getRequestDispatcher("/WEB-INF/jsp/admin/adminreviews.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}

package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.WrongInteractionProcessor;
import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Actor;
import by.epam.mtlcwtchr.ecafe.entity.Ingredient;
import by.epam.mtlcwtchr.ecafe.entity.Meal;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class DeleteIngredientCommand extends Command {

    public DeleteIngredientCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            if (Objects.nonNull(((HttpServletRequest) getRequest()).getSession().getAttribute("actor")) &&
                    ((Actor) ((HttpServletRequest) getRequest()).getSession().getAttribute("actor")).isPromoted()) {
                executePost();
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch (IOException ex){
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {
        try{
            if (Objects.nonNull(getRequest().getParameter("dkey")) &&
                    !getRequest().getParameter("dkey").isBlank() &&
                    !getRequest().getParameter("dkey").isEmpty() &&
                    getRequest().getParameter("dkey").matches("[0-9]++")) {
                if (EntityServiceFactory.getInstance().getMealIngredientService().delete(Integer.parseInt(getRequest().getParameter("dkey")))) {
                    ((List<Ingredient>) ((HttpServletRequest) getRequest()).getSession().getAttribute("ingredients"))
                            .removeIf(_i -> _i.getId()==Integer.parseInt(getRequest().getParameter("dkey")));
                }
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/admin_ingredients");
            } else  {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch ( ServiceException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

}

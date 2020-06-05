package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.WebCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Category;
import by.epam.mtlcwtchr.ecafe.entity.Meal;
import by.epam.mtlcwtchr.ecafe.service.command.Command;
import by.epam.mtlcwtchr.ecafe.service.command.CommandType;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class UpdateCategoryWebCommand extends WebCommand {

    public UpdateCategoryWebCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            final Command getCommand = Command.of(CommandType.GET_CATEGORY_COMMAND);
            getCommand.initParams(Integer.parseInt(getRequest().getParameter("chosenCategoryId")));
            getCommand.execute();
            if (getCommand.getCommandResult().first()) {
                final Category category = (Category) getCommand.getCommandResult().get();
                if (Objects.nonNull(getRequest().getParameter("categoryName"))) {
                    category.setName(getRequest().getParameter("categoryName"));
                }
                if (Objects.nonNull(getRequest().getParameter("categoryPicUrl"))) {
                    category.setPictureUrl(getRequest().getParameter("categoryPicUrl"));
                }
                final Command updateCommand = Command.of(CommandType.UPDATE_CATEGORY_COMMAND);
                updateCommand.initParams(category);
                updateCommand.execute();
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/categories?open=" + getRequest().getParameter("chosenCategoryId"));
        } catch ( ServiceException | IOException ex) {
            executeGet();
        }
    }

}

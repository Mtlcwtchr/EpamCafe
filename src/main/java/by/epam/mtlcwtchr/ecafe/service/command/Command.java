package by.epam.mtlcwtchr.ecafe.service.command;

import by.epam.mtlcwtchr.ecafe.service.command.impl.*;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.verification.CheckedArguments;

public abstract class Command implements IExecutable {

    private CommandResult commandResult;
    private CommandParams commandParams;

    @Override
    public abstract void execute() throws ServiceException;

    public final CommandResult getCommandResult() {
        return commandResult;
    }
    public final void initResult(Object... result){
        commandResult = new CommandResult(result);
    }

    public final CommandParams getCommandParams(){ return commandParams; }
    public final void initParams(Object... params){
        commandParams = new CommandParams(params);
    }

    @CheckedArguments
    public static Command of(CommandType commandType) {
        return switch(commandType){
            case GET_MEALS_COMMAND -> new GetMealsCommand();
            case GET_INGREDIENTS_COMMAND -> new GetIngredientsCommand();
            case GET_CATEGORIES_COMMAND -> new GetCategoriesCommand();
            case GET_ORDERS_COMMAND -> new GetOrdersCommand();
            case GET_USERS_COMMAND -> new GetUsersCommand();
            case GET_CLIENTS_COMMAND -> new GetClientsCommand();

            case GET_MEAL_COMMAND -> new GetMealCommand();
            case GET_INGREDIENT_COMMAND -> new GetIngredientCommand();
            case GET_CATEGORY_COMMAND -> new GetCategoryCommand();
            case GET_ORDER_COMMAND -> new GetOrderCommand();
            case GET_USER_COMMAND -> new GetUserCommand();
            case GET_CLIENT_COMMAND -> new GetClientCommand();

            case SAVE_MEAL_COMMAND -> new SaveMealCommand();
            case SAVE_INGREDIENT_COMMAND -> new SaveIngredientCommand();
            case SAVE_CATEGORY_COMMAND -> new SaveCategoryCommand();
            case PLACE_ORDER_COMMAND -> new PlaceOrderCommand();

            case UPDATE_MEAL_COMMAND -> new UpdateMealCommand();
            case UPDATE_INGREDIENT_COMMAND -> new UpdateIngredientCommand();
            case UPDATE_CATEGORY_COMMAND -> new UpdateCategoryCommand();
            case UPDATE_ORDER_COMMAND -> new UpdateOrderCommand();

            case UPDATE_USER_COMMAND -> new UpdateUserCommand();
            case UPDATE_CLIENT_COMMAND -> new UpdateClientCommand();
            case DELETE_MEAL_COMMAND -> new DeleteMealCommand();
            case DELETE_INGREDIENT_COMMAND -> new DeleteIngredientCommand();
            case DELETE_CATEGORY_COMMAND -> new DeleteCategoryCommand();
            case DELETE_ORDER_COMMAND -> new DeleteOrderCommand();
            case DELETE_USER_COMMAND -> new DeleteUserCommand();
            case DELETE_CLIENT_COMMAND -> new DeleteClientCommand();
            case AUTHORIZATE_COMMAND -> new AuthorizateCommand();
        };
    }

}

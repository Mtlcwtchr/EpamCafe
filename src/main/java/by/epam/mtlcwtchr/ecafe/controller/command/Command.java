package by.epam.mtlcwtchr.ecafe.controller.command;

import by.epam.mtlcwtchr.ecafe.controller.command.impl.*;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public abstract class Command implements IWebExecutable{

    private final ServletRequest request;
    private final ServletResponse response;

    public Command(ServletRequest request, ServletResponse response){
        this.request = request;
        this.response = response;
    }

    public abstract void executeGet() throws ControllerException;
    public abstract void executePost() throws ControllerException;

    public static Command of(WebCommandType webCommandType, ServletRequest request, ServletResponse response){
        return switch (webCommandType){
            case HOME_COMMAND -> new HomeCommand(request, response);
            case PROFILE_COMMAND -> new ProfileCommand(request, response);
            case SIGN_IN_COMMAND -> new SignInCommand(request, response);
            case SIGN_UP_COMMAND -> new SignUpCommand(request, response);
            case SIGN_OUT_COMMAND -> new SignOutCommand(request, response);
            case CHANGE_PROFILE_COMMAND -> new ChangeProfileCommand(request, response);
            case MEALS_COMMAND -> new MealsCommand(request, response);
            case CATEGORIES_COMMAND -> new CategoriesCommand(request, response);
            case ADD_MEAL_TO_ORDER_COMMAND -> new AddMealToOrderCommand(request, response);
            case LOAD_IMAGE_COMMAND -> new LoadImageCommand(request, response);
            case REMOVE_MEAL_FROM_ORDER_COMMAND -> new RemoveMealFromOrderCommand(request, response);
            case CLIENT_ORDER_COMMAND -> new ClientOrderCommand(request, response);
            case CLIENT_ORDERS_COMMAND -> new ClientOrdersCommand(request, response);
            case PAYMENT_COMMAND -> new PaymentCommand(request, response);
            case PLACE_ORDER_COMMAND -> new PlaceOrderCommand(request, response);
            case MANUAL_COMMAND -> new ManualCommand(request, response);
            case CONTACTS_COMMAND -> new ContactsCommand(request, response);
            case ABOUT_CAFE_COMMAND -> new AboutCafeCommand(request, response);
            case HALLS_COMMAND -> new HallsCommand(request, response);
            case AORDERS_COMMAND -> new AdminOrdersCommand(request, response);
            case AINGREDIENTS_COMMAND -> new AdminIngredientsCommand(request, response);
            case ACLIENTS_COMMAND -> new AdminClientsCommand(request, response);
            case UPDATE_MEAL_COMMAND -> new UpdateMealCommand(request, response);
            case UPDATE_INGREDIENT_COMMAND -> new UpdateIngredientCommand(request, response);
            case UPDATE_CLIENT_COMMAND -> new UpdateClientCommand(request, response);
            case UPDATE_ORDER_COMMAND -> new UpdateOrderCommand(request, response);
            case UPDATE_CATEGORY_COMMAND -> new UpdateCategoryCommand(request, response);
            case UPDATE_HALL_COMMAND -> new UpdateHallCommand(request, response);
            case SAVE_MEAL_COMMAND -> new SaveMealCommand(request, response);
            case SAVE_CATEGORY_COMMAND -> new SaveCategoryCommand(request, response);
            case SAVE_INGREDIENT_COMMAND -> new SaveIngredientCommand(request, response);
            case SAVE_HALL_COMMAND -> new SaveHallCommand(request, response);
            case CHANGE_ADMIN_PROFILE_COMMAND -> new ChangeAdminProfileCommand(request, response);
            case SOMETHING_WENT_WRONG_COMMAND -> new SomethingWentWrongCommand(request, response);
        };
    }

    public ServletRequest getRequest() {
        return request;
    }
    public ServletResponse getResponse() {
        return response;
    }

}

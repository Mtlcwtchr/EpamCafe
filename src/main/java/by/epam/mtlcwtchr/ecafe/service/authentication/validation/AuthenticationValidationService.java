package by.epam.mtlcwtchr.ecafe.service.authentication.validation;

import by.epam.mtlcwtchr.ecafe.config.AuthenticationServiceConfiguration;

public class AuthenticationValidationService {

    public static AuthenticationValidationService getInstance(){
        return AuthenticationValidationServiceInstanceHandler.AUTHENTICATION_VALIDATION_SERVICE_INSTANCE;
    }

    public boolean signInDataIsValid(String username, String password) {
        return username.matches(AuthenticationServiceConfiguration.INSTANCE.getUsernamePattern()) &&
                password.matches(AuthenticationServiceConfiguration.INSTANCE.getPasswordPattern());
    }

    public boolean signUpDataIsValid(String username, String password, String email, String phone) {
        return username.matches(AuthenticationServiceConfiguration.INSTANCE.getUsernamePattern()) &&
                password.matches(AuthenticationServiceConfiguration.INSTANCE.getPasswordPattern()) &&
                email.matches(AuthenticationServiceConfiguration.INSTANCE.getEmailPattern()) &&
                phone.matches(AuthenticationServiceConfiguration.INSTANCE.getPhonePattern());
    }

    private static class AuthenticationValidationServiceInstanceHandler{

        private static final AuthenticationValidationService AUTHENTICATION_VALIDATION_SERVICE_INSTANCE = new AuthenticationValidationService();

    }

}

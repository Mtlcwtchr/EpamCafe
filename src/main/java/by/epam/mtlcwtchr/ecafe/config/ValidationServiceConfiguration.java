package by.epam.mtlcwtchr.ecafe.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public enum ValidationServiceConfiguration {

    INSTANCE;

    private String usernamePattern;
    private String passwordPattern;
    private String emailPattern;
    private String phonePattern;

    private final String defaultUsernamePattern = ".++";
    private final String defaultPasswordPattern = ".++";
    private final String defaultEmailPattern = ".++";
    private final String defaultPhonePattern = ".++";

    ValidationServiceConfiguration(){
        loadProperties();
    }
    private void loadProperties() {
        try(InputStream in = new FileInputStream("D:\\Java\\EPAM\\EcafeWebapp\\EpamCafe\\src\\main\\resources\\userAuthenticationValidation.properties")){
            Properties properties = new Properties();
            properties.load(in);
            usernamePattern = Objects.nonNull(properties.getProperty("usernamePattern.regexp")) ?
                    properties.getProperty("usernamePattern.regexp") : defaultUsernamePattern;
            passwordPattern = Objects.nonNull(properties.getProperty("passwordPattern.regexp")) ?
                    properties.getProperty("passwordPattern.regexp") : defaultPasswordPattern;
            emailPattern = Objects.nonNull(properties.getProperty("emailPattern.regexp")) ?
                    properties.getProperty("emailPattern.regexp") : defaultEmailPattern;
            phonePattern = Objects.nonNull(properties.getProperty("phonePattern.regexp")) ?
                    properties.getProperty("phonePattern.regexp") : defaultPhonePattern;
        } catch (IOException ex){
            throw new Error("Validation properties has not been loaded", ex);
        }
    }

    @Override
    public String toString() {
        return  getClass().getSimpleName() + '{' +
                "usernamePattern='" + usernamePattern + '\'' +
                ", passwordPattern='" + passwordPattern + '\'' +
                ", emailPattern='" + emailPattern + '\'' +
                ", phonePattern='" + phonePattern + '\'' +
                "}";
    }

    public String getUsernamePattern() {
        return usernamePattern;
    }
    public String getPasswordPattern() {
        return passwordPattern;
    }
    public String getEmailPattern() {
        return emailPattern;
    }
    public String getPhonePattern() {
        return phonePattern;
    }

}

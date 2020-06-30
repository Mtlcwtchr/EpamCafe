package by.epam.mtlcwtchr.ecafe.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public enum AuthenticationServiceConfiguration {

    INSTANCE;

    private final String usernamePattern = "^[a-zA-Z][a-zA-Z0-9-_.]{1,20}$";
    private final String passwordPattern = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$";
    private final String emailPattern = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    private final String phonePattern = "[0-9]{11}";
    private String globalSalt;
    private int hashIterations;

    AuthenticationServiceConfiguration(){
        loadProperties();
    }
    private void loadProperties() {
        try(InputStream in = getClass().getClassLoader().getResourceAsStream("AuthenticationProperties.properties")){
            Properties properties = new Properties();
            properties.load(in);
            globalSalt = Objects.nonNull(properties.getProperty("globalSalt")) ?
                    properties.getProperty("globalSalt") : "defaultSalt";
            hashIterations = Objects.nonNull(properties.getProperty("hashIterations")) ?
                    Integer.parseInt(properties.getProperty("hashIterations")) : 1;
        } catch (IOException ignored){ }
    }

    @Override
    public String toString() {
        return  getClass().getSimpleName() + '{' +
                "usernamePattern='" + usernamePattern + '\'' +
                ", passwordPattern='" + passwordPattern + '\'' +
                ", emailPattern='" + emailPattern + '\'' +
                ", phonePattern='" + phonePattern + '\'' +
                ", globalSalt='" + globalSalt + '\'' +
                ", hashIterations=" + hashIterations +
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
    public String getGlobalSalt() {
        return globalSalt;
    }
    public int getHashIterations() {
        return hashIterations;
    }

}

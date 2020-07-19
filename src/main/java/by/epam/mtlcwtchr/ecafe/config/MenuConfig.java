package by.epam.mtlcwtchr.ecafe.config;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

public enum MenuConfig {

    INSTANCE;

    private int defaultMenuCategoryId;

    MenuConfig(){
        loadProperties();
    }

    private void loadProperties() {
        try(InputStream in = getClass().getClassLoader().getResourceAsStream("menuProperties.properties")){
            Properties properties = new Properties();
            properties.load(in);
            defaultMenuCategoryId = Objects.nonNull(properties.getProperty("defaultMenuCategoryId")) ?
                    Integer.parseInt(properties.getProperty("defaultMenuCategoryId")) : 0;
        } catch (IOException ex){
           StaticDataHandler.INSTANCE.getLOGGER().error(ex);
        }
    }

    public int getDefaultMenuCategoryId() {
        return defaultMenuCategoryId;
    }

    @Override
    public String toString() {
        return  getClass().getSimpleName() + "{" +
                "defaultMenuCategoryId=" + defaultMenuCategoryId +
                '}';
    }
}
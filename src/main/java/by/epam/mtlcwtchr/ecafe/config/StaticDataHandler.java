package by.epam.mtlcwtchr.ecafe.config;

import by.epam.mtlcwtchr.ecafe.controller.servlet.CommonServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public enum  StaticDataHandler {

    INSTANCE;

    private final Logger LOGGER = LogManager.getLogger(CommonServlet.class);

    private final byte[] HOME_ICON;
    {
        byte[] TEMP_IC;
        try {
            TEMP_IC =  Files.readAllBytes(Paths.get("D:\\Java\\EPAM\\EcafeWebapp\\EpamCafe\\src\\main\\resources\\images\\ecafe-home-icon.png"));
        } catch (IOException ex) {
            LOGGER.error("Could not load home icon cause of " + ex);
            TEMP_IC = null;
        }
        HOME_ICON = TEMP_IC;
    }
    private final byte[] PROFILE_ICON;
    {
        byte[] TEMP_IC;
        try {
            TEMP_IC =  Files.readAllBytes(Paths.get("D:\\Java\\EPAM\\EcafeWebapp\\EpamCafe\\src\\main\\resources\\images\\ecafe-profile-icon.png"));
        } catch (IOException ex) {
            LOGGER.error("Could not load home icon cause of " + ex);
            TEMP_IC = null;
        }
        PROFILE_ICON = TEMP_IC;
    }

    public Logger getLOGGER() {
        return LOGGER;
    }

    public byte[] getHOME_ICON() {
        return HOME_ICON;
    }

    public byte[] getPROFILE_ICON() {
        return PROFILE_ICON;
    }

    @Override
    public String toString() {
        return  getClass().getSimpleName() + "{" +
                "LOGGER=" + LOGGER +
                ", HOME_ICON=" + Arrays.toString(HOME_ICON) +
                ", PROFILE_ICON=" + Arrays.toString(PROFILE_ICON) +
                '}';
    }
}
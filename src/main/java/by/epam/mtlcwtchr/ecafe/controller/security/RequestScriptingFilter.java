package by.epam.mtlcwtchr.ecafe.controller.security;

import javax.servlet.ServletRequest;
import java.util.Arrays;

public final class RequestScriptingFilter {

    private RequestScriptingFilter(){

    }

    public static final String SCRIPT_REGULAR = ".*<.*>.*</.*>.*";

    public static void filter(ServletRequest servletRequest){
        servletRequest.getParameterMap().forEach(((key, values) -> {
            if (Arrays.stream(values).anyMatch(value -> value.matches(SCRIPT_REGULAR))) {
                servletRequest.getParameterMap().remove(key);
            }
        }));
        servletRequest.getAttributeNames().asIterator().forEachRemaining(key -> {
            if(servletRequest.getAttribute(key).toString().matches(SCRIPT_REGULAR)) {
                servletRequest.removeAttribute(key);
            }
        });
    }

}

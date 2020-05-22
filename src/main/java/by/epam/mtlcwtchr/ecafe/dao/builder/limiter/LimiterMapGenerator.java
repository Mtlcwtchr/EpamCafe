package by.epam.mtlcwtchr.ecafe.dao.builder.limiter;

import by.epam.mtlcwtchr.ecafe.verification.CheckedArguments;

import java.util.HashMap;

public final class LimiterMapGenerator {

    @CheckedArguments
    public static HashMap<String, Limiter> generateOfSingleType(Limiter limiterType,String... args){
        HashMap<String, Limiter> limiterHashMap = new HashMap<>();
        for (String arg : args) {
            limiterHashMap.put(arg, limiterType);
        }
        return limiterHashMap;
    }

}

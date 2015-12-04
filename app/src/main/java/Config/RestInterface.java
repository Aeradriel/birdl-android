package config;

import retrofitInterface.RestEventInterface;
import retrofitInterface.RestMessageInterface;
import retrofitInterface.RestUserInterface;

/**
 * Created by Serkan on 12/07/2015.
 */

public class RestInterface {
    private static RestUserInterface REST_USER;
    private static RestEventInterface REST_EVENT;
    private static RestMessageInterface REST_MESSAGE;

    public static RestUserInterface getUserInterface() {
        return REST_USER;
    }

    public static RestEventInterface getEventInterface() {
        return REST_EVENT;
    }

    public static RestMessageInterface getMessageInterface() {
        return REST_MESSAGE;
    }

    public RestInterface(BirdlConfigNetwork userNetwork, String buildInterface)
    {
        userNetwork.setRequestInterceptor();
        userNetwork.setRestAdapterHeader(BirdlConfigNetwork.getApiUrl(), userNetwork.getRequestInterceptor());

        if (buildInterface.equals("user"))
            REST_USER = userNetwork.getRestAdapterHeader().create(RestUserInterface.class);
        else if (buildInterface.equals("event"))
            REST_EVENT = userNetwork.getRestAdapterHeader().create(RestEventInterface.class);
        else if (buildInterface.equals("message"))
            REST_MESSAGE = userNetwork.getRestAdapterHeader().create(RestMessageInterface.class);
    }
}

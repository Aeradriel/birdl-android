package configBirdl;

import com.birdl.activity.SessionInformation;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;

/**
 * Created by Christophe on 02/12/2015.
 */
public class BirdlConfigNetwork {

    private static String API_URL = "http://birdl.xyz:3000/";
    private static RestAdapter restAdapter;
    private static RequestInterceptor requestInterceptor;
    private static RestAdapter restAdapterHeader;

    public BirdlConfigNetwork()
    {
        this.SetRestAdapter(API_URL);
    }

    public static String getApiUrl()
    {
        return API_URL;
    }

    private void SetRestAdapter(String url) {
        this.restAdapter = new RestAdapter.Builder()
                .setEndpoint(url)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("log retrofit"))
                .build();
    }

    public static RestAdapter getRestAdapter() {
        return restAdapter;
    }

    public void setRequestInterceptor() {
        this.requestInterceptor = new RequestInterceptor() {
            public void intercept(RequestFacade request) {
                request.addHeader("ACCESS-TOKEN", SessionInformation.AccessToken);
            }
        };
    }

    public static RequestInterceptor getRequestInterceptor() {
        return requestInterceptor;
    }

    public void setRestAdapterHeader(String url, RequestInterceptor interceptor)
    {
        restAdapterHeader = new RestAdapter.Builder()
                .setEndpoint(url)
                .setRequestInterceptor(interceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("log retrofit"))
                .build();
    }

    public static RestAdapter getRestAdapterHeader() {
        return restAdapterHeader;
    }
}

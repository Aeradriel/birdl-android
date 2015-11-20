package slidingBar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.birdl.birdl.EventDetailActivity;
import com.birdl.birdl.R;
import com.birdl.birdl.RestAllEventInformation;
import com.birdl.birdl.SessionInformation;

import java.util.ArrayList;

import adapter.CustomListAdapter;
import model.AllEventInformationStatic;
import model.AllEventResponse;
import model.AllEventResponseStatic;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class Tab1 extends Fragment {
    ListView lv1;
    View v;
    private RequestInterceptor requestInterceptor;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.activity_list_event,container,false);

        //fill event information class
        requestInterceptor = new RequestInterceptor() {
            public void intercept(RequestFacade request) {
                request.addHeader("ACCESS-TOKEN", SessionInformation.AccessToken);
            }
        };

        RestAdapter eventInformation = new RestAdapter.Builder().setEndpoint("http://163.5.84.208:3000/")
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("log retrofit"))
                .build();
        RestAllEventInformation getEventInfo = eventInformation.create(RestAllEventInformation.class);
        getEventInfo.getInfo(new Callback<AllEventResponse>() {
            @Override
            public void success(AllEventResponse allEventResponse, Response response3) {

                AllEventResponseStatic.events = new ArrayList<AllEventInformationStatic>();
                for (int i = 0; i < allEventResponse.events.size(); i++) {
                    AllEventResponseStatic.events.add(new AllEventInformationStatic(allEventResponse.events.get(i).id,
                            allEventResponse.events.get(i).getName(),
                            allEventResponse.events.get(i).getType(),
                            allEventResponse.events.get(i).getMin_slots(),
                            allEventResponse.events.get(i).getMax_slots(),
                            allEventResponse.events.get(i).getDate(),
                            allEventResponse.events.get(i).getDesc(),
                            allEventResponse.events.get(i).getOwner_id(),
                            allEventResponse.events.get(i).getAddress_id(),
                            allEventResponse.events.get(i).getLocation()));
                }
                ArrayList events_list = AllEventResponseStatic.events;
                lv1 = (ListView) v.findViewById(R.id.list_event);
                lv1.setAdapter(new CustomListAdapter(getActivity(), AllEventResponseStatic.events));
                lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                        EventDetailActivity.position = position;
                        Intent intent = new Intent("com.birdl.birdl.EventDetailActivity");
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
        return v;
    }


}

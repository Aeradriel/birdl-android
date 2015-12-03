package slidingBarEvent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.birdl.activity.EventDetailActivity;
import com.birdl.activity.R;

import config.BirdlConfigNetwork;
import config.RestInterface;

import java.util.ArrayList;

import adapter.CustomListAdapter;
import config.AllEventInformationStatic;
import config.AllEventResponse;
import config.AllEventResponseStatic;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Tab1 extends Fragment {

    private ListView lv1;
    private View v;
    private BirdlConfigNetwork eventNetwork;
    private RestInterface restEvent;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.activity_list_event,container,false);

        eventNetwork = new BirdlConfigNetwork();
        restEvent = new RestInterface(eventNetwork, "event");
        restEvent.getEventInterface().getInfo(new Callback<AllEventResponse>() {
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
                Toast.makeText(getActivity(), "bad user information request", Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }


}

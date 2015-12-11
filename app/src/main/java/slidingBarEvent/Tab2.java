package slidingBarEvent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.birdl.activity.EventDetailActivity;
import com.birdl.activity.R;
import com.birdl.activity.SessionInformation;

import java.util.ArrayList;

import adapter.CustomListAdapter;
import configBirdl.AllEventInformationStatic;
import configBirdl.AllEventResponse;
import configBirdl.AllEventResponseStatic;
import configBirdl.BirdlConfigNetwork;
import configBirdl.RestInterface;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Tab2 extends Fragment {
    private ListView lv1;
    private View v;
    private Button search;
    private EditText searchInput;
    private static String searchInputResult = null;
    private Spinner eventTypeResult;
    private Spinner eventLanguage;
    private BirdlConfigNetwork eventNetwork;
    private RestInterface restEvent;
    private ArrayList events_list;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.search_event_activity,container,false);

        search = (Button) v.findViewById(R.id.valide_search);
        searchInput = (EditText) v.findViewById(R.id.search_event_field);
        eventTypeResult = (Spinner) v.findViewById(R.id.spinner_event_type);
        eventLanguage = (Spinner) v.findViewById(R.id.spinner_event_language);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                eventNetwork = new BirdlConfigNetwork();
                restEvent = new RestInterface(eventNetwork, "event");
                restEvent.getEventInterface().getFutureEvent(new Callback<AllEventResponse>() {
                    @Override
                    public void success(AllEventResponse allEventResponse, Response response3) {
                        searchInputResult = searchInput.getText().toString();
                        AllEventResponseStatic.events = new ArrayList<AllEventInformationStatic>();
                        for (int i = 0; i < allEventResponse.events.size(); i++) {
                            if (allEventResponse.events.get(i).getFree_slots() > 0) {
                                if ((searchInputResult != null && searchInputResult.equals(allEventResponse.events.get(i).getName())) ||
                                        (eventTypeResult.getSelectedItem().toString().equals(allEventResponse.events.get(i).getType())) ||
                                        (eventLanguage.getSelectedItem().toString().equals(allEventResponse.events.get(i).getLanguage()))) {
                                    AllEventResponseStatic.events.add(new AllEventInformationStatic(allEventResponse.events.get(i).id,
                                            allEventResponse.events.get(i).getName(),
                                            allEventResponse.events.get(i).getType(),
                                            allEventResponse.events.get(i).getMin_slots(),
                                            allEventResponse.events.get(i).getMax_slots(),
                                            allEventResponse.events.get(i).getDate(),
                                            allEventResponse.events.get(i).getDesc(),
                                            allEventResponse.events.get(i).getOwner_id(),
                                            allEventResponse.events.get(i).getAddress_id(),
                                            allEventResponse.events.get(i).getLanguage(),
                                            allEventResponse.events.get(i).getLocation(),
                                            allEventResponse.events.get(i).getFree_slots()));
                                }
                            }
                        }
                        events_list = AllEventResponseStatic.events;
                        lv1 = (ListView) v.findViewById(R.id.list_event_result);
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
            }
        });

        return v;
    }
}
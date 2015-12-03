package slidingBarEvent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.birdl.activity.R;
import retrofitInterface.RestEventInterface;
import com.birdl.activity.SessionInformation;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class Tab3 extends Fragment {
    private EditText EventNameField = null;
    private Spinner EventTypeField = null;
    private EditText LanguageField = null;
    private EditText LocationField = null;
    private EditText MinSlotsField = null;
    private EditText MaxSlotsField = null;
    private EditText DateField = null;
    private EditText EndDateField = null;
    private EditText DescField = null;
    private RestAdapter restAdapterHeader;
    private RequestInterceptor requestInterceptor;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.create_event_activity, container, false);

        Button ButtonSubmit = (Button) v.findViewById(R.id.submit_new_event);
        ButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EventNameField = (EditText) getActivity().findViewById(R.id.event_name_field);
                EventTypeField = (Spinner) getActivity().findViewById(R.id.spinner_event_type);
                DateField = (EditText) getActivity().findViewById(R.id.starting_date_field);
                LanguageField = (EditText) getActivity().findViewById(R.id.language_field);
                EndDateField = (EditText) getActivity().findViewById(R.id.ending_date_field);
                MinSlotsField = (EditText) getActivity().findViewById(R.id.minimum_places_field);
                MaxSlotsField = (EditText) getActivity().findViewById(R.id.maximum_places_field);
                DescField = (EditText) getActivity().findViewById(R.id.description_event_field);

                requestInterceptor = new RequestInterceptor() {
                    public void intercept(RequestFacade request) {
                        request.addHeader("ACCESS-TOKEN", SessionInformation.AccessToken);
                    }
                };

                restAdapterHeader = new RestAdapter.Builder().setEndpoint("http://163.5.84.208:3000/")
                        .setRequestInterceptor(requestInterceptor)
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .setLog(new AndroidLog("log retrofit"))
                        .build();

                final RestEventInterface eventInformation = restAdapterHeader.create(RestEventInterface.class);

                eventInformation.createEvent(EventNameField.getText().toString(), DescField.getText().toString(),
                        EventTypeField.getSelectedItem().toString(), MinSlotsField.getText().toString(),
                        MaxSlotsField.getText().toString(), DateField.getText().toString(),
                        EndDateField.getText().toString(), LanguageField.getText().toString(), new Callback<Response>() {
                            @Override
                            public void success(Response response, Response response2) {
                                Toast.makeText(getActivity(), "Event created", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Toast.makeText(getActivity(), "bad event request", Toast.LENGTH_SHORT).show();
                            }
                        });

                Intent intent2 = new Intent("com.birdl.birdl.action.menu");
                startActivity(intent2);
            }
        });


        return v;
    }
}

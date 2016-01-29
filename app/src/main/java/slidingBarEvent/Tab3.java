package slidingBarEvent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.birdl.activity.R;
import interfaceRetrofit.RestEventInterface;
import com.birdl.activity.SessionInformation;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class Tab3 extends Fragment {
    private View v;
    private EditText EventNameField;
    private Spinner EventTypeField;
    private Spinner LanguageField;
    private EditText LocationField;
    private EditText MinSlotsField;
    private EditText MaxSlotsField;
    private DatePicker DateField;
    private DatePicker EndDateField;
    private EditText DescField;
    private Button ButtonSubmit;
    private RestAdapter restAdapterHeader;
    private RequestInterceptor requestInterceptor;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.create_event_activity, container, false);
        bindView();

        ButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                requestInterceptor = new RequestInterceptor() {
                    public void intercept(RequestFacade request) {
                        request.addHeader("ACCESS-TOKEN", SessionInformation.AccessToken);
                    }
                };

                restAdapterHeader = new RestAdapter.Builder().setEndpoint("http://birdl.xyz:3000/")
                        .setRequestInterceptor(requestInterceptor)
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .setLog(new AndroidLog("log retrofit"))
                        .build();

                final RestEventInterface eventInformation = restAdapterHeader.create(RestEventInterface.class);

                eventInformation.createEvent(EventNameField.getText().toString(), DescField.getText().toString(),
                        EventTypeField.getSelectedItem().toString(), MinSlotsField.getText().toString(),
                        MaxSlotsField.getText().toString(), formatDate(DateField), formatDate(EndDateField),
                        LocationField.getText().toString(), LanguageField.getSelectedItem().toString(), new Callback<Response>() {
                            @Override
                            public void success(Response response, Response response2) {
                                Toast.makeText(getActivity(), "Event created", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent("com.birdl.birdl.SlidingEventLayout");
                                startActivity(intent);
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Toast.makeText(getActivity(), "bad event request", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        return v;
    }

    private String formatDate(DatePicker datePicker)
    {
        int day = datePicker.getDayOfMonth();
        String formattedDay = String.valueOf(day);
        int month = datePicker.getMonth() + 1;
        String formattedMonth = String.valueOf(month);
        int year = datePicker.getYear();

        if (day < 10)
            formattedDay = "0" + day;
        if (month < 10)
            formattedMonth = "0" + month;

        String date = String.valueOf(year)+ "/" +
                formattedMonth + "/" +
                formattedDay;

        return date;
    }

    private void bindView()
    {
        ButtonSubmit = (Button) v.findViewById(R.id.submit_new_event);
        EventNameField = (EditText) v.findViewById(R.id.event_name_field);
        EventTypeField = (Spinner) v.findViewById(R.id.spinner_event_type);
        DateField = (DatePicker) v.findViewById(R.id.starting_date_field);
        LanguageField = (Spinner) v.findViewById(R.id.spinner_event_language_create);
        EndDateField = (DatePicker) v.findViewById(R.id.ending_date_field);
        MinSlotsField = (EditText) v.findViewById(R.id.minimum_places_field);
        MaxSlotsField = (EditText) v.findViewById(R.id.maximum_places_field);
        LocationField = (EditText) v.findViewById(R.id.location_event_field);
        DescField = (EditText) v.findViewById(R.id.description_event_field);
    }
}

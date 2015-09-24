package slidingBar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.birdl.birdl.R;

import java.util.ArrayList;

import adapter.CustomListAdapter;
import model.AllEventInformationStatic;
import model.AllEventResponseStatic;

public class Tab1 extends Fragment {
    ListView lv1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.activity_list_event,container,false);

        ArrayList events_list = AllEventResponseStatic.events;
        lv1 = (ListView) v.findViewById(R.id.list_event);
        lv1.setAdapter(new CustomListAdapter(getActivity(), AllEventResponseStatic.events));

        return v;
    }
}

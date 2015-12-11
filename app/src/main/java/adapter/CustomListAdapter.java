package adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.birdl.activity.R;

import java.util.ArrayList;

import configBirdl.AllEventInformationStatic;
import configBirdl.AllInboxInformationStatic;

/**
 * Created by Christophe on 22/09/2015.
 */
public class CustomListAdapter extends BaseAdapter {
    private ArrayList<AllEventInformationStatic> listEvent;
    private ArrayList<AllInboxInformationStatic> listInbox;
    private LayoutInflater layoutInflater;

    public CustomListAdapter(Context aContext, ArrayList<AllEventInformationStatic> listData) {
        this.listEvent = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    public CustomListAdapter(FragmentActivity activity, ArrayList<AllInboxInformationStatic> inbox) {
        this.listInbox = inbox;
        layoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return listEvent.size();
    }

    @Override
    public Object getItem(int position) {
        return listEvent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_row_layout_event, null);
            holder = new ViewHolder();
            holder.headlineView = (TextView) convertView.findViewById(R.id.title);
            holder.reporterNameView = (TextView) convertView.findViewById(R.id.language_event);
            holder.reporterFreeSlots = (TextView) convertView.findViewById(R.id.free_slots);
            holder.reportedDateView = (TextView) convertView.findViewById(R.id.date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.headlineView.setText(listEvent.get(position).getName());
        holder.reporterNameView.setText("Language: " + listEvent.get(position).getLanguage());
        holder.reporterFreeSlots.setText("Free slots: " + listEvent.get(position).getFree_slots());
        holder.reportedDateView.setText(listEvent.get(position).getDate());
        return convertView;
    }

    static class ViewHolder {
        TextView headlineView;
        TextView reporterNameView;
        TextView reporterFreeSlots;
        TextView reportedDateView;
    }
}

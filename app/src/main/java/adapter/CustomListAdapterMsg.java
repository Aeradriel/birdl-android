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
public class CustomListAdapterMsg extends BaseAdapter {
    private ArrayList<AllInboxInformationStatic> listInbox;
    private LayoutInflater layoutInflater;

    public CustomListAdapterMsg(Context aContext, ArrayList<AllInboxInformationStatic> listData) {
        this.listInbox = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    public CustomListAdapterMsg(FragmentActivity activity, ArrayList<AllInboxInformationStatic> inbox) {
        this.listInbox = inbox;
        layoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return listInbox.size();
    }

    @Override
    public Object getItem(int position) {
        return listInbox.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_row_layout_message, null);
            holder = new ViewHolder();
            holder.headlineView = (TextView) convertView.findViewById(R.id.sender);
            holder.reporterNameView = (TextView) convertView.findViewById(R.id.receiver);
            holder.reporterFreeSlots = (TextView) convertView.findViewById(R.id.content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.headlineView.setText("Expéditeur: " + listInbox.get(position).getSender_name());
        holder.reporterNameView.setText("Destinataire: " + listInbox.get(position).getReceiver_name());
        holder.reporterFreeSlots.setText("Message: " + listInbox.get(position).getContent());
        return convertView;
    }

    static class ViewHolder {
        TextView headlineView;
        TextView reporterNameView;
        TextView reporterFreeSlots;
    }
}

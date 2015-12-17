package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.birdl.activity.R;

import java.util.ArrayList;

import configBirdl.RelationsInformation;

/**
 * Created by Christophe on 16/12/2015.
 */
public class AdapterSpinner extends BaseAdapter {
    private ArrayList<RelationsInformation> listRelations;
    private LayoutInflater layoutInflater;

    public AdapterSpinner(Context aContext, ArrayList<RelationsInformation> listData) {
        this.listRelations = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listRelations.size();
    }

    @Override
    public Object getItem(int position) {
        return listRelations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.custom_spinner_relations, null);
            holder = new ViewHolder();
            holder.relationFirstName = (TextView) convertView.findViewById(R.id.relation_first_name);
            holder.relationLastName = (TextView) convertView.findViewById(R.id.relation_last_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.relationFirstName.setText(listRelations.get(position).getFirst_name());
        holder.relationLastName.setText(listRelations.get(position).getLast_name());
        return convertView;
    }

    static class ViewHolder {
        TextView relationFirstName;
        TextView relationLastName;
    }
}

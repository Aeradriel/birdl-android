package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.birdl.birdl.R;

import java.util.Collections;
import java.util.List;

import model.NavDrawerItem;

public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {
    List<NavDrawerItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavDrawerItem current = data.get(position);
        String home = "Home";
        String new_message = "Create message";
        String inbox = "Inbox";
        String event = "Event";
        String disconnect = "Disconnect";
        holder.title.setText(current.getTitle());
        if (current.getTitle().equals(home))
        {
            holder.icon.setImageResource(R.drawable.ic_home);
        }
        else if (current.getTitle().equals(new_message))
        {
            holder.icon.setImageResource(R.drawable.ic_create_message);
        }
        else if (current.getTitle().equals(inbox))
        {
            holder.icon.setImageResource(R.drawable.ic_inbox);
        }
        else if (current.getTitle().equals(event))
        {
            holder.icon.setImageResource(R.drawable.ic_event);
        }
        else if (current.getTitle().equals(disconnect))
        {
            holder.icon.setImageResource(R.drawable.ic_disconnect);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            icon = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
}
package hof;

/**
 * Created by Christophe on 08/12/2015.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.birdl.activity.R;
import java.util.ArrayList;

public class HofViewAdapter extends RecyclerView.Adapter<HofViewHolder> {
    Context context;
    ArrayList<RowItemsHof> itemsList;

    public HofViewAdapter(Context context, ArrayList<RowItemsHof> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @Override
    public int getItemCount()
    {
        if (itemsList == null) {
            return 0;
        } else {
            return itemsList.size();
        }
    }

    @Override
    public HofViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.single_row_hof, null);
        HofViewHolder viewHolder = new HofViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HofViewHolder holder, int position) {
        RowItemsHof items = itemsList.get(position);
        HofViewHolder.vName.setText(String.valueOf(items.getName()));
        HofViewHolder.vPos.setText(String.valueOf(items.getPos()));
        HofViewHolder.vGrade.setText(String.valueOf(items.getGrade()));
    }
}
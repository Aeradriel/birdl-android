package hof;

/**
 * Created by Christophe on 08/12/2015.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.birdl.activity.R;

public class HofViewHolder extends RecyclerView.ViewHolder {

    static TextView vName;
    static TextView vPos;
    static TextView vGrade;

    public HofViewHolder(View v) {
        super(v);
        this.vName = (TextView) v.findViewById(R.id.user_name);
        this.vPos = (TextView) v.findViewById(R.id.user_pos);
        this.vGrade = (TextView) v.findViewById(R.id.user_grade);
    }
}

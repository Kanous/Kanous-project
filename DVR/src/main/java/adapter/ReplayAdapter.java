package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ReplayAdapter extends RecyclerView.Adapter<ReplayAdapter.MyHolder> {
    private String TAG = this.getClass().getSimpleName();
    private Context mContext;
    private List<String> list;
    private OnItemClickListener onItemClickListener;
    private final int NORMAL_VIDEO = 1;
    private final int EMERGENCY_VIDEO = 2;
    private final int PHOTO_VIDEO = 3;
    private int type = 0;
    public boolean isEdit = false;



    public ReplayAdapter(Context context, List<String> list, int type) {
        mContext = context;
        this.type = type;
        this.list = list;
    }


    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        holder.itemName.setText(list.get(position));
        if (type == 1) {
//            holder.itemPreView.setImageResource(R.drawable.pre_image);
        }else if(type == 2){
//            holder.itemPreView.setImageResource(R.drawable.em);
        }else if(type == 3){
//            holder.itemPreView.setImageResource(R.drawable.pho);
        }

        if(isEdit){
            holder.select_item.setVisibility(View.VISIBLE);
        }else{
            holder.select_item.setVisibility(View.GONE);
        }

        holder.replay_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView itemName;
        private ImageView itemPreView;
        private CheckBox select_item;
        private LinearLayout replay_item;

        private MyHolder(View itemView) {
            super(itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onitemClickListener) {
        onItemClickListener = onitemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}

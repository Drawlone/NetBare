package com.drawlone.packetanalyzer;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @author drawlone
 * @since 2021/5/16 11:07
 */
public class ConnectionAdapter extends RecyclerView.Adapter<ConnectionAdapter.ViewHolder> {

    private final List<String> mList;

    public ConnectionAdapter(List<String> list){
        this.mList = list;
    }

    public void addData(int i){
        mList.add("item"+i);
        notifyItemInserted(0);
    }

    public void removeData(int i){
        mList.remove(i);
        notifyItemRemoved(i);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_connection, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mAppName.setText(mList.get(position));
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                removeData(position);
                return true;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mAppName;
        public final TextView mConnectionTime;
        public final TextView mConnectionUrl;
        public final TextView mConnectionIp;
        public final TextView mConnectionSize;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            mAppName = (TextView) view.findViewById(R.id.app_name);
            mConnectionTime = (TextView) view.findViewById(R.id.connection_time);
            mConnectionUrl = (TextView) view.findViewById(R.id.connection_url);
            mConnectionIp = (TextView) view.findViewById(R.id.connection_ip);
            mConnectionSize = (TextView) view.findViewById(R.id.connection_size);

        }

    }

}

package com.drawlone.packetanalyzer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @author drawlone
 * @since 2021/5/17 10:48
 */
public class PacketAdapter extends RecyclerView.Adapter<PacketAdapter.ViewHolder>{

    private final List<String> mList;

    public PacketAdapter(List<String> list){
        this.mList = list;
    }

    public void addData(int i){
        mList.add("item"+i);
        notifyItemInserted(i);
    }

    public void removeData(int i){
        mList.remove(i);
        notifyItemRemoved(i);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_packet, parent, false);
        return new PacketAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mPacketDirection.setText(mList.get(position));
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                removeData(position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mPacketDirection;
        public final TextView mPacketTime;
        public final TextView mPacketRaw;
        public final TextView mPacketData;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            mPacketTime = (TextView) view.findViewById(R.id.packet_time);
            mPacketDirection = (TextView) view.findViewById(R.id.packet_direction);
            mPacketRaw = (TextView) view.findViewById(R.id.packet_raw);
            mPacketData = (TextView) view.findViewById(R.id.packet_data);

        }

    }
}

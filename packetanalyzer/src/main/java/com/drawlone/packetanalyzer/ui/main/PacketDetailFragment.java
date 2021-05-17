package com.drawlone.packetanalyzer.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.drawlone.packetanalyzer.ConnectionAdapter;
import com.drawlone.packetanalyzer.PacketAdapter;
import com.drawlone.packetanalyzer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author drawlone
 * @since 2021/5/17 10:07
 */
public class PacketDetailFragment extends Fragment {

    PacketAdapter mPacketAdapter;

    PacketDetailFragment(PacketAdapter packetAdapter){
        this.mPacketAdapter = packetAdapter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = root.findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mPacketAdapter);
        return root;
    }

}

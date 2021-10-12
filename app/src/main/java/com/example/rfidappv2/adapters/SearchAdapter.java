package com.example.rfidappv2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rfidappv2.R;
import com.example.rfidappv2.models.Device;
import com.example.rfidappv2.models.StationWithDeviceAsso;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> {
    List<StationWithDeviceAsso> mlist;


    @NonNull
    @Override
    public SearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_box, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class SearchHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;

        public SearchHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_stations);
            title = itemView.findViewById(R.id.station_title);
        }
    }

    public void setFilterList(List<StationWithDeviceAsso> list) {
            this.mlist = list;
            notifyDataSetChanged();

    }
}

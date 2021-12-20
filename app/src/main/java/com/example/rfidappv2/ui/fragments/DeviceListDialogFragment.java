package com.example.rfidappv2.ui.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import com.example.rfidappv2.models.Device;
import com.example.rfidappv2.mvvm.IViewModal;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rfidappv2.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     DeviceListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class DeviceListDialogFragment extends BottomSheetDialogFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_ITEM_COUNT = "item_count";

    // TODO: Customize parameters
    public static DeviceListDialogFragment newInstance(int itemCount) {
        final DeviceListDialogFragment fragment = new DeviceListDialogFragment();
        final Bundle args = new Bundle();
        args.putInt(ARG_ITEM_COUNT, itemCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_item_list_dialog_list_dialog, container, false);
        return view;
    }

    List<Device> deviceList = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        IViewModal viewModal = new ViewModelProvider(requireActivity()).get(IViewModal.class);
        viewModal.getGetAllListOfDevices(FirebaseAuth.getInstance().getCurrentUser().getUid()).observe(requireActivity(), new Observer<List<Device>>() {
            @Override
            public void onChanged(List<Device> devices) {
                deviceList = devices;
                if (deviceList != null)
                    recyclerView.setAdapter(new DeviceAdapter(deviceList));
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView device_name;
        AppCompatButton remove_device_button;

        ViewHolder(View binding) {
            super(binding);
            device_name = binding.findViewById(R.id.device_name);
            remove_device_button = binding.findViewById(R.id.remove_device);
        }

    }

    private class DeviceAdapter extends RecyclerView.Adapter<ViewHolder> {

        List<Device> deviceList = new ArrayList<>();


        DeviceAdapter(List<Device> deviceList) {
            this.deviceList = deviceList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_list_dialog_list_dialog_item, parent, false));

        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.device_name.setText(deviceList.get(position).getName());
            holder.remove_device_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeDevice();
                }


            });
        }

        private void removeDevice() {
            //do action later
        }

        @Override
        public int getItemCount() {
            return deviceList.size();
        }

    }
}
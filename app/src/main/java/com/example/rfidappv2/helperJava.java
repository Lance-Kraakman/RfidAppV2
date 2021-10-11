package com.example.rfidappv2;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.rfidappv2.models.Device;
import com.example.rfidappv2.mvvm.IViewModal;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
/*
* this class is test purpose only
* delete it after
* */
public class helperJava extends AppCompatActivity {
    IViewModal viewModal = new ViewModelProvider(this).get(IViewModal.class);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModal.getGetAllListOfDevices(FirebaseAuth.getInstance().getCurrentUser().getUid()).observe(this, new Observer<List<Device>>() {
            @Override
            public void onChanged(List<Device> devices) {

            }
        });
    }
}

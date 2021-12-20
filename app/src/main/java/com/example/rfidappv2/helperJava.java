package com.example.rfidappv2;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.WindowId;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.rfidappv2.HelperUtils.IKeys;
import com.example.rfidappv2.models.Device;
import com.example.rfidappv2.models.Station;
import com.example.rfidappv2.mvvm.IViewModal;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Integer> getDeviceDP() {
        try {
            DisplayMetrics matrix = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(matrix);
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put(IKeys.KEY_WIDTH, matrix.widthPixels);
            map.put(IKeys.KEY_HEIGHT, matrix.heightPixels);
            map.put(IKeys.KEY_DENSTI_PIXEL, matrix.densityDpi);

            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void drawOnCanvas() {
        try {
            Canvas canvas = new Canvas();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void calculateX(Device device, Station station, int containerSize) {
        int station_height = Integer.parseInt(station.height);
        int station_width = Integer.parseInt(station.width);
        float containerY = (containerSize - (2 * 10) * station_height) / station_width;
        float containerx = containerSize - (2 * 10);
        int deviceXLoc = Integer.parseInt(device.getX_location());
        int deviceYLoc = Integer.parseInt(device.getY_location());
        float x = ((deviceXLoc) * (containerx)) / station_width;
        float y = ((deviceYLoc) * (containerx)) / station_height;

    }

    private void startIntent(Context context){
        Intent i  = new Intent(context,AddDevice.class);
        context.startActivity(i);
    }
    private void loadingDailog(){
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please push add device button on nearest station");
        dialog.show();
    }
}


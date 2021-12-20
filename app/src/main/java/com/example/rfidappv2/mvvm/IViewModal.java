package com.example.rfidappv2.mvvm;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.rfidappv2.models.Device;
import com.example.rfidappv2.models.Station;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IViewModal extends ViewModel {

    FirebaseRepo repo;

    //for any ref
    public IViewModal() {
        this.repo = new FirebaseRepo();
    }

    //fore any ref
    public IViewModal(String ref) {
        this.repo = new FirebaseRepo(ref);
    }

    LiveData<List<Device>> getAllListOfDevices;
    LiveData<List<Station>> getAllStationsList;

    public LiveData<List<Device>> getGetAllListOfDevices(String uuid) {
        return getAllListOfDevices = repo.getAllDeviceList(uuid);
    }


    public LiveData<List<Station>> getGetAllStationsList(String uuid) {
        return getAllStationsList = repo.getAllStationsList(uuid);
    }

    public void addNewDevice(String uuid, Device divces) {
        this.repo.addDevice(uuid, divces);

    }

    public void addNewStation(String userKey, Station station) {
        this.repo.addStations(userKey, station);
    }

    public void removeStation(String userKey, Station station) {
        this.repo.removeStation(userKey, station);
    }

    public void removeDevice(String userKey, Device device) {
        this.repo.removeDevice(userKey, device);
    }
    public Device getLastAddedDevice(String userkey){
       return this.repo.getLastAddedDevice(userkey);
    }
}

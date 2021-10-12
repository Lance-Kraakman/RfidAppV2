package com.example.rfidappv2.models;

import java.util.List;

public class StationWithDeviceAsso {
    Station station;
    List<Device> list;

    public StationWithDeviceAsso(Station station, List<Device> list) {
        this.station = station;
        this.list = list;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public List<Device> getList() {
        return list;
    }

    public void setList(List<Device> list) {
        this.list = list;
    }
}

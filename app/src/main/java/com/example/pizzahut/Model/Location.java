package com.example.pizzahut.Model;

import com.google.android.gms.maps.model.LatLng;

public class Location {
    private LatLng location;
    private String name;

    public Location(LatLng location, String name) {
        this.location = location;
        this.name = name;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

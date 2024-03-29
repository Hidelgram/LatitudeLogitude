package com.example.latitudelogitude;

public class Coordenadas {

    private int _id;
    private double latitude;
    private double longitude;
    private long datahorario;


    public Coordenadas(int _id, double latitude, double longitude, long datahorario) {
        this._id = _id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.datahorario = datahorario;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getDatahorario() {
        return datahorario;
    }

    public void setDatahorario(long datahorario) {
        this.datahorario = datahorario;
    }
}

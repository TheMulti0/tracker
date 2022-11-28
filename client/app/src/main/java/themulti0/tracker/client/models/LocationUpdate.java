package themulti0.tracker.client.models;

public class LocationUpdate {
    private final double lat;
    private final double lon;
    private final double alt;

    public LocationUpdate(double lat, double lon, double alt) {

        this.lat = lat;
        this.lon = lon;
        this.alt = alt;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public double getAlt() {
        return alt;
    }
}

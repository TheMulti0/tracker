package themulti0.tracker.client.models;

public class SensorUpdate {
    private final String sensor;
    private final float[] values;

    public SensorUpdate(String sensor, float[] values) {
        this.sensor = sensor;
        this.values = values;
    }

    public String getSensor() {
        return sensor;
    }

    public float[] getValues() {
        return values;
    }
}

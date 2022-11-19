namespace WebApplication2
{
    public class State
    {
        public LocationUpdate Location { get; set; } = new LocationUpdate
        {
            Alt = 5,
            Lat = 6,
            Lon = 3,
        };

        public SensorUpdate Sensor { get; set; } = new SensorUpdate
        {
            Sensor = "idk",
            Values = Array.Empty<float>()
        };
    }
}
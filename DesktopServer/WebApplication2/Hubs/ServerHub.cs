using Microsoft.AspNetCore.SignalR;

namespace WebApplication2
{
    public class ServerHub : Hub<IServer>
    {
        private readonly State _state;

        public ServerHub(State state)
        {
            _state = state;
        }

        public LocationUpdate GetCurrentLocation()
        {
            return _state.Location;
        }
        
        public SensorUpdate GetCurrentSensor()
        {
            return _state.Sensor;
        }
    }
}
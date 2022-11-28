using Microsoft.AspNetCore.SignalR;

namespace WebApplication2
{
    public class SensorHub : Hub
    {
        private readonly State _state;
        private readonly IHubContext<ServerHub, IServer> _server;

        public SensorHub(
            State state, 
            IHubContext<ServerHub, IServer> server)
        {
            _state = state;
            _server = server;
        }

        public async Task OnSensorUpdate(SensorUpdate update)
        {
            _state.Sensor = update;
            await _server.Clients.All.OnSensorUpdate(update);
        }
    }
}
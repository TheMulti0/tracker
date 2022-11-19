using Microsoft.AspNetCore.SignalR;

namespace WebApplication2
{
    public class LocationHub : Hub
    {
        private readonly State _state;
        private readonly IHubContext<ServerHub, IServer> _server;

        public LocationHub(
            State state, 
            IHubContext<ServerHub, IServer> server)
        {
            _state = state;
            _server = server;
        }

        public async Task OnLocationUpdate(LocationUpdate location)
        {
            _state.Location = location;
            await _server.Clients.All.OnLocationUpdate(location);
        }
    }
}
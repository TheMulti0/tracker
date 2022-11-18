using Microsoft.AspNetCore.SignalR;

namespace WebApplication2
{
    public class ClientHub : Hub<IClient>
    {
        private readonly State _state;
        private readonly IHubContext<ServerHub, IServer> _server;

        public ClientHub(State state, IHubContext<ServerHub, IServer> server)
        {
            _state = state;
            _server = server;
        }

        public async Task OnNewLocation(LocationUpdate location)
        {
            _state.Location = location;
            await _server.Clients.All.OnNewLocation(location);
        }
    }
}
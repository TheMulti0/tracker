namespace WebApplication2
{
    public interface IClient
    {
        Task<LocationUpdate> GetCurrentLocation();
    }
}
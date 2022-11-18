namespace WebApplication2
{
    public interface IServer
    {
        Task OnNewLocation(LocationUpdate location);
    }
}
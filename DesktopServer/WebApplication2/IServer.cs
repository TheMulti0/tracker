namespace WebApplication2
{
    public interface IServer
    {
        Task OnLocationUpdate(LocationUpdate location);
        
        Task OnSensorUpdate(SensorUpdate sensor);
    }
}
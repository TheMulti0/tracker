namespace WebApplication2.Controllers ;

    public record LocationUpdate
    {
        public double Lat { get; init; }
        public double Lon { get; init; }
        public double Alt { get; init; }
    }
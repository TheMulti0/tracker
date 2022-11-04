using Microsoft.AspNetCore.Mvc;

namespace WebApplication2.Controllers ;

    [ApiController]
    [Route("[controller]")]
    public class LocationController
    {
        private static LocationUpdate _update = new()
        {
            Lat = 5,
            Lon = 5,
            Alt = 5
        };

        [HttpGet]
        public LocationUpdate GetCurrent()
        {
            return _update;
        }
        
        [HttpPost]
        public ActionResult UpdateLocation(LocationUpdate update)
        {
            _update = update;
            
            Console.WriteLine($"{update.Lat} - {update.Lon} - {update.Alt}");

            return new OkResult();
        }
    }
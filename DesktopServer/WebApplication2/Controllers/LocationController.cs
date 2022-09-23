using Microsoft.AspNetCore.Mvc;

namespace WebApplication2.Controllers ;

    [ApiController]
    [Route("[controller]")]
    public class LocationController
    {
        [HttpPost]
        public ActionResult UpdateLocation(LocationUpdate update)
        {
            Console.WriteLine($"{update.Lat} - {update.Lon} - {update.Alt}");

            return new OkResult();
        }
    }
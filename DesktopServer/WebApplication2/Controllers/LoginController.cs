using Microsoft.AspNetCore.Mvc;

namespace WebApplication2.Controllers ;

    [ApiController]
    [Route("[controller]")]
    public class LoginController : ControllerBase
    {
        private readonly ILogger<LoginController> _logger;

        public LoginController(ILogger<LoginController> logger)
        {
            _logger = logger;
        }

        [HttpPost]
        public bool Login()
        {
            return true;
        }
    }
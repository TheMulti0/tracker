using WebApplication2;

WebApplicationBuilder builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddSignalR();

builder.Services.AddSingleton<State>();

WebApplication app = builder.Build();

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapHub<ServerHub>("/Server");
app.MapHub<ClientHub>("/Client");

app.Run();
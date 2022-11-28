// using System.Net;
// using System.Net.Sockets;
// using System.Text;
//
// public class Program
// {
//     public static int Main(String[] args)
//     {
//         StartServer();
//         return 0;
//     }
//
//     public static void StartServer()
//     {
//         // Get Host IP Address that is used to establish a connection
//         // In this case, we get one IP address of localhost that is IP : 127.0.0.1
//         // If a host has multiple addresses, you will get a list of addresses
//         IPHostEntry host = Dns.GetHostEntry("localhost");
//         IPAddress ipAddress = host.AddressList[0];
//         IPEndPoint localEndPoint = new IPEndPoint(ipAddress, 8080);
//
//         try {
//
//             // Create a Socket that will use Tcp protocol
//             Socket listener = new Socket(ipAddress.AddressFamily, SocketType.Stream, ProtocolType.Tcp);
//             // A Socket must be associated with an endpoint using the Bind method
//             listener.Bind(localEndPoint);
//             // Specify how many requests a Socket can listen before it gives Server busy response.
//             // We will listen 10 requests at a time
//             listener.Listen(10);
//
//             Console.WriteLine("Waiting for a connection...");
//             Socket handler = listener.Accept();
//
//              // Incoming data from the client.
//              string data = null;
//              byte[] bytes = null;
//
//             while (true)
//             {
//                 bytes = new byte[1024];
//                 int bytesRec = handler.Receive(bytes);
//                 data += Encoding.ASCII.GetString(bytes, 0, bytesRec);
//                 if (data.IndexOf("<EOF>") > -1)
//                 {
//                     break;
//                 }
//             }
//
//             Console.WriteLine("Text received : {0}", data);
//
//             byte[] msg = Encoding.ASCII.GetBytes(data);
//             handler.Send(msg);
//             handler.Shutdown(SocketShutdown.Both);
//             handler.Close();
//         }
//         catch (Exception e)
//         {
//             Console.WriteLine(e.ToString());
//         }
//
//         Console.WriteLine("\n Press any key to continue...");
//         Console.ReadKey();
//     }
// }

// 00000020 0804b160 0804853d 00000009 bffffce0 b7e1b679 bffffbb4 b7fc3000 b7fc3000 0804b160 39617044 28293664 6d617045 bf000a64 0804861b 00000002 bffffbb4 bffffbc0 8da95e00 bffffb20

//string original = Console.ReadLine();

static string ConvertHex(String hexString)
{
    try
    {
        string ascii = string.Empty;

        for (int i = 0; i < hexString.Length; i += 2)
        {
            String hs = string.Empty;

            hs   = hexString.Substring(i,2);
            uint decval =   System.Convert.ToUInt32(hs, 16);
            char character = System.Convert.ToChar(decval);
            ascii += character;

        }

        return ascii;
    }
    catch (Exception ex) { Console.WriteLine(ex.Message); }

    return string.Empty;
}
    string original =
    "000000200804b1600804853d00000009bffffce0b7e1b679bffffbb4b7fc3000b7fc30000804b16039617044282936646d617045bf000a640804861b00000002bffffbb4bffffbc08da95e00bffffb20";

    string bigEndian = original
        .Chunk(8)
        .Select(
            chars => chars
                .Chunk(2)
                .Reverse()
                .Aggregate((lhs, rhs) => lhs.Concat(rhs).ToArray()))
        .Select(
            chars => new string(chars))
        .ToList()
        .Aggregate(
            (lhs, rhs) => new string(
                lhs.Concat(rhs).ToArray()));

    string asci = ConvertHex(bigEndian);

    Console.WriteLine(asci);
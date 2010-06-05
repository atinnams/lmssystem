using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ClientSimulatorNet.org.hcmus.util
{
    public class Debug
    {

        public static void Send()
        {
            Console.WriteLine("<send>");
        }

        public static void Receive()
        {
            Console.WriteLine("<receive>");
        }

        public static void Log(string host, string port)
        {
            Console.WriteLine("<log realm = channel/" + host + ":" + port + "at=" + DateTime.Now.ToString() + ">");
        }

        public static void EndLog()
        {
            Console.WriteLine("</log>");
        }

        public static void EndSend()
        {
            Console.WriteLine("</send>");
        }

        public static void EndReceive()
        {
            Console.WriteLine("</receive>");
        }

        public static void Connect()
        {
            Console.WriteLine("<session-start />");
        }

        public static void Disconnect()
        {
            Console.WriteLine("<session-end />");
        }

        public static void ShowField(string field, string value)
        {
            Console.WriteLine("<field id=" + field + ">" + value + "</field>");
        }

        public static void Dump(string hexString)
        {
            Console.WriteLine("<HEX>");
            Console.WriteLine(hexString);
            Console.WriteLine("</HEX>");
        }
    }
}

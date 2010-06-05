using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ClientSimulatorNet.org.hcmus.util
{
    public class ConfigurationManagement
    {
        private static string host;
        private static int port;
        private static string mid;
        private static string tid;

        public static string getHost()
        {
            return ConfigurationManagement.host;
        }

        public static void setHost(string aHost)
        {
            ConfigurationManagement.host = aHost;
        }

        public static int getPort()
        {
            return ConfigurationManagement.port;
        }

        public static void setPort(int aPort)
        {
            ConfigurationManagement.port = aPort;
        }

        public static string getMID()
        {
            return ConfigurationManagement.mid;
        }

        public static void setMID(string aMID)
        {
            ConfigurationManagement.mid = aMID;
        }

        public static string getTID()
        {
            return ConfigurationManagement.tid;
        }

        public static void setTID(string aTID)
        {
            ConfigurationManagement.tid = aTID;
        }

        public static string show()
        {
            return ConfigurationManagement.host + ConfigurationManagement.port.ToString() 
                + ConfigurationManagement.mid + ConfigurationManagement.tid;
        }
    }
}

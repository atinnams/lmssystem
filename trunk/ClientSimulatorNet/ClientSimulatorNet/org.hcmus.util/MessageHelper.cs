using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ClientSimulatorNet.org.hcmus.util
{
    public class MessageHelper
    {
        public static string generateVoiceId()
        {
            Random rd = new Random();
            string result = "";
            for (int i = 0; i < 12; i++)
            {
                result += rd.Next(10);
            }
            return result;
        }

        ///Encode the value as BCD and put it in the buffer. The buffer must be big enough
        ///to store the digits in the original value (half the length of the string).
        public static byte[] ToBcd(String value)
        {
            int haftLen = value.Length % 2 == 0 ? value.Length / 2 : (value.Length / 2 + 1);
            byte[] buf = new byte[haftLen];
            int charpos = 0; //char where we start
            int bufpos = 0;
            //encode the rest of the string

            int len = value.Length;
            if (len % 2 == 1)
            {
                //for odd lengths we encode just the last digit in the last byte
                buf[haftLen - 1] = (byte)((value[len - 1] - 48) << 4);
                len--;
            }

            while (charpos < len)
            {
                buf[bufpos] = (byte)(((value[charpos] - 48) << 4)
                        | (value[charpos + 1] - 48));
                charpos += 2;
                bufpos++;
            }

            return buf;
        }

        public static string GetString(byte[] value)
        {
            char[] hexValue = new char[] {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F' };
            string result = "";
            int len = value.Length;
            for (int i = 0; i < len; i++)
            {
                result += hexValue[((value[i] >> 4))];
                result += hexValue[(value[i] & 0x0F)];
            }

            return result;
        }

        public static string PaddingAmount(int amount,int padding)
        {
            string strAmount = amount.ToString();
            int len = strAmount.Length;
            StringBuilder builder = new StringBuilder(8);
            while(len < padding)
            {
                builder.Append("0");
                len++;
            }
            builder.Append(strAmount);
            return builder.ToString();
        }

        public static string getFF3EValue(string field61)
        {
            int index = field61.IndexOf("FF3E");
            if (index != -1)
            {
                return field61.Substring(40 + index,8);
            }
            return null;
        }

        public static string getFF41Value(string field61)
        {
            
            int index = field61.IndexOf("FF41");
            if (index != -1)
            {
                return field61.Substring(40 + index,8);
            }
            return null;
            
        }

        public static string getFF58Value(string field61)
        {
            int index = field61.IndexOf("FF58");
            if (index != -1)
            {
                return field61.Substring(6 + index,8);
            }
            return null;
        }
    }
}

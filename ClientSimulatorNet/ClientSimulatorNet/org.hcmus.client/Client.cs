using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ClientSimulatorNet.org.hcmus.util;
using System.Net.Sockets;

namespace ClientSimulatorNet.org.hcmus.client
{
    public class Client
    {
        public static string redeemReloadId = "";
        
        public static string Balance(string cardId, string host,int port,string mid,string tid)
        {
            string result = "";
            /*3030393002002020000020C10000383838383838303030303038379704215000000046D14061011468030000000038313130313030313831313030393939393930303030310023FF21027567FF2203000000FF25091655D7C5A50A1B2C7B */
            byte[] streamLength = ASCIIEncoding.ASCII.GetBytes("0090");
            byte[] mti = new byte[2] {0x02,0x00};
            byte[] bitmap = new byte[8] { 0x20, 0x20, 0x00, 0x00, 0x20, 0xC1, 0x00, 0x00 };
            byte[] processingCode = ASCIIEncoding.ASCII.GetBytes("888888");
            byte[] systemTraceAuditNumber = ASCIIEncoding.ASCII.GetBytes("000008");
            byte[] field35Length = new byte[1] {0x37};
            byte[] field35CardId = MessageHelper.ToBcd(cardId);
            byte[] field35Paddle = new byte[11] { 0xD1,0x40,0x61,0x01,0x14,0x68,0x03,0x00,0x00,0x00,0x00 };
            byte[] field41 = ASCIIEncoding.ASCII.GetBytes(tid);
            byte[] field42 = ASCIIEncoding.ASCII.GetBytes(mid);
            byte[] field48 = new byte[25] { 0x00,0x23,0xFF,0x21,0x02,0x75,0x67,0xFF,0x22,0x03,0x00,0x00,0x00,0xFF,0x25,0x09,0x16,0x55,0xD7,0xC5,0xA5,0x0A,0x1B,0x2C,0x7B };

            Debug.Connect();
            TcpClient client = null;
            try
            {
                client = new TcpClient(host, port);
            }
            catch (SocketException ex)
            {
                Console.WriteLine(ex.Message);
                Debug.Disconnect();
                return "Can not connect to server";
            }

            NetworkStream stream = client.GetStream();
            if (stream == null)
            {
                Debug.Disconnect();
                return "Can not get stream";
            }

            Debug.Send();
            Debug.Log(host,port.ToString());

            stream.Write(streamLength, 0, streamLength.Length);

            stream.Write(mti, 0, mti.Length);
            Debug.ShowField("0", MessageHelper.GetString(mti));

            stream.Write(bitmap, 0, bitmap.Length);
            Debug.ShowField("Bitmap", MessageHelper.GetString(bitmap));

            stream.Write(processingCode, 0, processingCode.Length);
            Debug.ShowField("3", MessageHelper.GetString(processingCode));

            stream.Write(systemTraceAuditNumber, 0, systemTraceAuditNumber.Length);
            Debug.ShowField("11", MessageHelper.GetString(systemTraceAuditNumber));

            stream.Write(field35Length, 0, field35Length.Length);
            stream.Write(field35CardId, 0, field35CardId.Length);
            stream.Write(field35Paddle, 0, field35Paddle.Length);
            Debug.ShowField("35", MessageHelper.GetString(field35CardId) + MessageHelper.GetString(field35Paddle));

            stream.Write(field41, 0, field41.Length);
            Debug.ShowField("41", MessageHelper.GetString(field41));

            stream.Write(field42, 0, field42.Length);
            Debug.ShowField("42", MessageHelper.GetString(field42));

            stream.Write(field48, 0, field48.Length);
            Debug.ShowField("48", MessageHelper.GetString(field48));

            Debug.EndLog();
            Debug.EndSend();

            int lenBuff = 0;
            byte[] bufferRespone = new byte[2048];
            lenBuff = stream.Read(bufferRespone, 0, 2048);
            if (lenBuff != -1)
            {
                byte[] response = new byte[lenBuff];
                Array.Copy(bufferRespone, response, lenBuff);

                byte[] field61 = new byte[5];
                Array.Copy(response, response.Length - 6, field61, 0, 5);

                result += "Transaction Balance\r\n";
                result += "CardId : " + cardId + "\r\n";
                result += "DateTime : " + DateTime.Now.ToString() + "\r\n";
                bool tryParse = false;
                int amoutResponse = 0;
                tryParse =  int.TryParse(MessageHelper.GetString(field61),out amoutResponse);
                if (!tryParse)
                {
                    result = "error";
                }
                else
                {
                    result += "Total amount" + amoutResponse + "\r\n";
                }

                Debug.Receive();
                Debug.Dump(MessageHelper.GetString(response));
                Debug.EndReceive();
            }

            stream.Close();
            client.Close();
            Debug.Disconnect();
            return result;
        }

        public static string Redeem(string cardId,int amount, string host, int port, string mid, string tid)
        {
            string result = "";
            /*3031323402002020000020C10008383838383838303030303036379704215000000046D14061011468030000000038313130313030313831313030393939393930303030310032FF21027207FF2203000000FF25091655D7C5A50A1B2C7BFF2F060519095801060023FF3C140002000000000000000000000000000000000000 */
            byte[] streamLength = ASCIIEncoding.ASCII.GetBytes("0124");
            byte[] mti = new byte[2] { 0x02, 0x00 };
            byte[] bitmap = new byte[8] { 0x20, 0x20, 0x00, 0x00, 0x20, 0xC1, 0x00, 0x08 };
            byte[] processingCode = ASCIIEncoding.ASCII.GetBytes("888888");
            byte[] systemTraceAuditNumber = ASCIIEncoding.ASCII.GetBytes("000008");
            byte[] field35Length = new byte[1] { 0x37 };
            byte[] field35CardId = MessageHelper.ToBcd(cardId);
            byte[] field35Paddle = new byte[11] { 0xD1, 0x40, 0x61, 0x01, 0x14, 0x68, 0x03, 0x00, 0x00, 0x00, 0x00 };
            byte[] field41 = ASCIIEncoding.ASCII.GetBytes(tid);
            byte[] field42 = ASCIIEncoding.ASCII.GetBytes(mid);

            byte[] headerPaddingField48 = new byte[28] { 0x00, 0x32, 0xFF, 0x21, 0x02, 0x72, 0x07, 0xFF, 0x22, 0x03, 0x00, 0x00, 0x00, 0xFF, 0x25, 0x09, 0x16, 0x55, 0xD7, 0xC5, 0xA5, 0x0A, 0x1B, 0x2C, 0x7B, 0xFF, 0x2F, 0x06 };
            string invoiceId = MessageHelper.generateVoiceId();
            byte[] invoiceByte = MessageHelper.ToBcd(invoiceId);
            byte[] field48 = new byte[34];
            Array.Copy(headerPaddingField48, field48, 28);
            Array.Copy(invoiceByte, 0, field48, 28, 6);

            /*0023FF3C140002000000000000000000000000000000000000*/
            byte[] headerPaddingField61 = new byte[5] {0x00,0x23,0xFF,0x3C,0x14 };
            string paddingAmount = MessageHelper.PaddingAmount(amount, 8);
            byte[] bAmount = MessageHelper.ToBcd(paddingAmount);
            byte[] field61 = new byte[25];
            Array.Copy(headerPaddingField61, field61, 5);
            Array.Copy(bAmount, 0, field61, 5, 4);

            Debug.Connect();
            TcpClient client = null;
            try
            {
                client = new TcpClient(host, port);
            }
            catch (SocketException ex)
            {
                Console.WriteLine(ex.Message);
                Debug.Disconnect();
                return "Can not connect to server";
            }

            NetworkStream stream = client.GetStream();
            if (stream == null)
            {
                Debug.Disconnect();
                return "Can not get stream";
            }

            Debug.Send();
            Debug.Log(host, port.ToString());

            stream.Write(streamLength, 0, streamLength.Length);

            stream.Write(mti, 0, mti.Length);
            Debug.ShowField("0", MessageHelper.GetString(mti));

            stream.Write(bitmap, 0, bitmap.Length);
            Debug.ShowField("Bitmap", MessageHelper.GetString(bitmap));

            stream.Write(processingCode, 0, processingCode.Length);
            Debug.ShowField("3", MessageHelper.GetString(processingCode));

            stream.Write(systemTraceAuditNumber, 0, systemTraceAuditNumber.Length);
            Debug.ShowField("11", MessageHelper.GetString(systemTraceAuditNumber));

            stream.Write(field35Length, 0, field35Length.Length);
            stream.Write(field35CardId, 0, field35CardId.Length);
            stream.Write(field35Paddle, 0, field35Paddle.Length);
            Debug.ShowField("35", MessageHelper.GetString(field35CardId) + MessageHelper.GetString(field35Paddle));

            stream.Write(field41, 0, field41.Length);
            Debug.ShowField("41", MessageHelper.GetString(field41));

            stream.Write(field42, 0, field42.Length);
            Debug.ShowField("42", MessageHelper.GetString(field42));

            stream.Write(field48, 0, field48.Length);
            Debug.ShowField("48", MessageHelper.GetString(field48));

            stream.Write(field61, 0, field61.Length);
            Debug.ShowField("61", MessageHelper.GetString(field61));

            Debug.EndLog();
            Debug.EndSend();
            
            int lenBuff = 0;
            byte[] bufferRespone = new byte[2048];
            lenBuff = stream.Read(bufferRespone, 0, 2048);
            if (lenBuff != -1)
            {
                byte[] response = new byte[lenBuff];
                Array.Copy(bufferRespone, response, lenBuff);

                byte[] bField61Response = new byte[58];
                Array.Copy(response,lenBuff - 58,bField61Response,0,58);

                string field61Response = MessageHelper.GetString(bField61Response);
                string totalAmount = MessageHelper.getFF3EValue(field61Response);
                string redeemAmount = MessageHelper.getFF41Value(field61Response);
                string totalPoint = MessageHelper.getFF58Value(field61Response);
                result += "Redeem Transaction\r\n";
                result += "CardId : " + cardId + "\r\n";
                result += "DateTime : " + DateTime.Now.ToString() + "\r\n";
                result += "InvoiceId : " + invoiceId + "\r\n";
                result += "Redeem Amount : " + redeemAmount + "\r\n";
                result += "Total Amount: " + totalAmount + "\r\n";
                result += "Total Point: " + totalPoint + "\r\n";

                Debug.Receive();
                Debug.Dump(MessageHelper.GetString(response));
                Debug.EndReceive();
            }

            Client.redeemReloadId = invoiceId;

            stream.Close();
            client.Close();
            Debug.Disconnect();
            return result;
        }

        public static string ReLoad(string cardId, int amount, string host, int port, string mid, string tid)
        {
            string result = "";
            /*3030393202002020000020C10000383838383838303030303133328704353300000015D99991231000000038313130313030313831313030393939393930303030310028FF21027707FF2203000000FF2B050000040000FF2F06052615240213 */
            byte[] streamLength = ASCIIEncoding.ASCII.GetBytes("0092");
            byte[] mti = new byte[2] { 0x02, 0x00 };
            byte[] bitmap = new byte[8] { 0x20,0x20,0x00,0x00,0x20,0xC1,0x00,0x00 };
            byte[] processingCode = ASCIIEncoding.ASCII.GetBytes("888888");
            byte[] systemTraceAuditNumber = ASCIIEncoding.ASCII.GetBytes("000008");
            byte[] field35Length = new byte[1] { 0x32 };
            byte[] field35CardId = MessageHelper.ToBcd(cardId);
            byte[] field35Paddle = new byte[8] { 0xD9,0x99,0x91,0x23,0x10,0x00,0x00,0x00 };
            byte[] field41 = ASCIIEncoding.ASCII.GetBytes(tid);
            byte[] field42 = ASCIIEncoding.ASCII.GetBytes(mid);

            byte[] headerPaddingField48 = new byte[16] { 0x00,0x28,0xFF,0x21,0x02,0x77,0x07,0xFF,0x22,0x03,0x00,0x00,0x00,0xFF,0x2B,0x05};
            byte[] bAmount = MessageHelper.ToBcd(MessageHelper.PaddingAmount(amount,10));
            byte[] headerInvoice = new byte[3] { 0xFF,0x2F,0x06 };
            string invoiceId = MessageHelper.generateVoiceId();
            byte[] invoiceByte = MessageHelper.ToBcd(invoiceId);
            byte[] field48 = new byte[30];
            Array.Copy(headerPaddingField48, field48, 16);
            Array.Copy(bAmount, 0, field48, 16, 5);
            Array.Copy(headerInvoice, 0, field48, 21, 3);
            Array.Copy(invoiceByte, 0, field48, 24, 6);

            Debug.Connect();
            TcpClient client = null;
            try
            {
                client = new TcpClient(host, port);
            }
            catch (SocketException ex)
            {
                Console.WriteLine(ex.Message);
                Debug.Disconnect();
                return "Can not connect to server";
            }

            NetworkStream stream = client.GetStream();
            if (stream == null)
            {
                Debug.Disconnect();
                return "Can not get stream";
            }

            Debug.Send();
            Debug.Log(host, port.ToString());

            stream.Write(streamLength, 0, streamLength.Length);

            stream.Write(mti, 0, mti.Length);
            Debug.ShowField("0", MessageHelper.GetString(mti));

            stream.Write(bitmap, 0, bitmap.Length);
            Debug.ShowField("Bitmap", MessageHelper.GetString(bitmap));

            stream.Write(processingCode, 0, processingCode.Length);
            Debug.ShowField("3", MessageHelper.GetString(processingCode));

            stream.Write(systemTraceAuditNumber, 0, systemTraceAuditNumber.Length);
            Debug.ShowField("11", MessageHelper.GetString(systemTraceAuditNumber));

            stream.Write(field35Length, 0, field35Length.Length);
            stream.Write(field35CardId, 0, field35CardId.Length);
            stream.Write(field35Paddle, 0, field35Paddle.Length);
            Debug.ShowField("35", MessageHelper.GetString(field35CardId) + MessageHelper.GetString(field35Paddle));

            stream.Write(field41, 0, field41.Length);
            Debug.ShowField("41", MessageHelper.GetString(field41));

            stream.Write(field42, 0, field42.Length);
            Debug.ShowField("42", MessageHelper.GetString(field42));

            stream.Write(field48, 0, field48.Length);
            Debug.ShowField("48", MessageHelper.GetString(field48));

            Debug.EndLog();
            Debug.EndSend();

            int lenBuff = 0;
            byte[] bufferRespone = new byte[2048];
            lenBuff = stream.Read(bufferRespone, 0, 2048);
            if (lenBuff != -1)
            {
                byte[] response = new byte[lenBuff];
                Array.Copy(bufferRespone, response, lenBuff);

                byte[] bField61Response = new byte[50];
                Array.Copy(response, lenBuff - 50, bField61Response, 0, 50);

                string field61Response = MessageHelper.GetString(bField61Response);
                string totalAmount = MessageHelper.getFF3EValue(field61Response);
                string reloadAmount = MessageHelper.getFF41Value(field61Response);

                result += "ReLoad Transaction\r\n";
                result += "CardId : " + cardId + "\r\n";
                result += "DateTime : " + DateTime.Now.ToString() + "\r\n";
                result += "InvoiceId : " + invoiceId + "\r\n";
                result += "ReLoad Amount : " + reloadAmount + "\r\n";
                result += "Total amount : " + totalAmount + "\r\n";

                Debug.Receive();
                Debug.Dump(MessageHelper.GetString(response));
                Debug.EndReceive();
            }

            Client.redeemReloadId = invoiceId;

            stream.Close();
            client.Close();
            Debug.Disconnect();
            return result;
        }

        public static string VoidRedeem(string cardId, int amount,string invoiceRedeem, string host, int port, string mid, string tid)
        {
            string result = "";
            /*3031333102003020000020C10008383838383838303030303030303030303030303030303038288704353300000015D9999123100038313130313030313831313030393939393930303030310032FF21027208FF2F06052515412008FF340F0200720700000000000526152402130023FF3C140001000000000000000000000000000000000000 */
            byte[] streamLength = ASCIIEncoding.ASCII.GetBytes("0131");
            byte[] mti = new byte[2] { 0x02, 0x00 };
            byte[] bitmap = new byte[8] { 0x30,0x20,0x00,0x00,0x20,0xC1,0x00,0x08 };
            byte[] processingCode = ASCIIEncoding.ASCII.GetBytes("888888");
            byte[] field4 = ASCIIEncoding.ASCII.GetBytes("000000000000");
            byte[] systemTraceAuditNumber = ASCIIEncoding.ASCII.GetBytes("000008");
            byte[] field35Length = new byte[1] { 0x28 };
            byte[] field35CardId = MessageHelper.ToBcd(cardId);
            byte[] field35Paddle = new byte[6] { 0xD9,0x99,0x91,0x23,0x10,0x00 };
            byte[] field41 = ASCIIEncoding.ASCII.GetBytes(tid);
            byte[] field42 = ASCIIEncoding.ASCII.GetBytes(mid);

            /*0032FF21027208FF2F06052515412008FF340F020072070000000000052615240213 */
            byte[] header48 = new byte[10] { 0x00, 0x32, 0xFF, 0x21, 0x02, 0x72, 0x08, 0xFF, 0x2F, 0x06 };
            string invoiceVoid = MessageHelper.generateVoiceId();
            byte[] invoiceByte = MessageHelper.ToBcd(invoiceVoid);
            byte[] tailer48 = new byte[12] { 0xFF, 0x34, 0x0F, 0x02, 0x00, 0x72, 0x07, 0x00, 0x00, 0x00, 0x00, 0x00 };
            byte[] invoiceRedeemByte = MessageHelper.ToBcd(invoiceRedeem);
            byte[] field48 = new byte[34];
            Array.Copy(header48, field48, 10);
            Array.Copy(invoiceByte, 0, field48, 10, 6);
            Array.Copy(tailer48, 0, field48, 16, 12);
            Array.Copy(invoiceRedeemByte, 0, field48, 28, 6);

            byte[] headerPaddingField61 = new byte[5] { 0x00, 0x23, 0xFF, 0x3C, 0x14 };
            string paddingAmount = MessageHelper.PaddingAmount(amount, 8);
            byte[] bAmount = MessageHelper.ToBcd(paddingAmount);
            byte[] field61 = new byte[25];
            Array.Copy(headerPaddingField61, field61, 5);
            Array.Copy(bAmount, 0, field61, 5, 4);

            Debug.Connect();
            TcpClient client = null;
            try
            {
                client = new TcpClient(host, port);
            }
            catch (SocketException ex)
            {
                Console.WriteLine(ex.Message);
                Debug.Disconnect();
                return "Can not connect to server";
            }

            NetworkStream stream = client.GetStream();
            if (stream == null)
            {
                Debug.Disconnect();
                return "Can not get stream";
            }

            Debug.Send();
            Debug.Log(host, port.ToString());

            stream.Write(streamLength, 0, streamLength.Length);

            stream.Write(mti, 0, mti.Length);
            Debug.ShowField("0", MessageHelper.GetString(mti));

            stream.Write(bitmap, 0, bitmap.Length);
            Debug.ShowField("Bitmap", MessageHelper.GetString(bitmap));

            stream.Write(processingCode, 0, processingCode.Length);
            Debug.ShowField("3", MessageHelper.GetString(processingCode));

            stream.Write(field4, 0, field4.Length);
            Debug.ShowField("4", MessageHelper.GetString(field4));

            stream.Write(systemTraceAuditNumber, 0, systemTraceAuditNumber.Length);
            Debug.ShowField("11", MessageHelper.GetString(systemTraceAuditNumber));

            stream.Write(field35Length, 0, field35Length.Length);
            stream.Write(field35CardId, 0, field35CardId.Length);
            stream.Write(field35Paddle, 0, field35Paddle.Length);
            Debug.ShowField("35", MessageHelper.GetString(field35CardId) + MessageHelper.GetString(field35Paddle));

            stream.Write(field41, 0, field41.Length);
            Debug.ShowField("41", MessageHelper.GetString(field41));

            stream.Write(field42, 0, field42.Length);
            Debug.ShowField("42", MessageHelper.GetString(field42));

            stream.Write(field48, 0, field48.Length);
            Debug.ShowField("48", MessageHelper.GetString(field48));

            stream.Write(field61, 0, field61.Length);
            Debug.ShowField("61", MessageHelper.GetString(field61));

            Debug.EndLog();
            Debug.EndSend();

            int lenBuff = 0;
            byte[] bufferRespone = new byte[2048];
            lenBuff = stream.Read(bufferRespone, 0, 2048);
            if (lenBuff != -1)
            {
                byte[] response = new byte[lenBuff];
                Array.Copy(bufferRespone, response, lenBuff);

                byte[] bField61Response = new byte[50];
                Array.Copy(response, lenBuff - 50, bField61Response, 0, 50);

                string field61Response = MessageHelper.GetString(bField61Response);
                string totalAmount = MessageHelper.getFF3EValue(field61Response);
                string voidAmount = MessageHelper.getFF41Value(field61Response);

                result += "Void Redeem Transaction\r\n";
                result += "CardId : " + cardId + "\r\n";
                result += "DateTime : " + DateTime.Now.ToString() + "\r\n";
                result += "InvoiceId : " + invoiceVoid + "\r\n";
                result += "Void Redeem Amount : " + voidAmount + "\r\n";
                result += "Total amount : " + totalAmount + "\r\n";

                Debug.Receive();
                Debug.Dump(MessageHelper.GetString(response));
                Debug.EndReceive();
            }

            stream.Close();
            client.Close();
            Debug.Disconnect();
            return result;
        }

        public static string VoidReload(string cardId, int amount,string invoiceRedeem, string host, int port, string mid, string tid)
        {
            string result = "";
            /*3031333102003020000020C10008383838383838303030303030303030303030303030303038288704353300000015D9999123100038313130313030313831313030393939393930303030310032FF21027708FF2F06052515412008FF340F0200720700000000000526152402130023FF3C140001000000000000000000000000000000000000 */
            byte[] streamLength = ASCIIEncoding.ASCII.GetBytes("0131");
            byte[] mti = new byte[2] { 0x02, 0x00 };
            byte[] bitmap = new byte[8] { 0x30,0x20,0x00,0x00,0x20,0xC1,0x00,0x08 };
            byte[] processingCode = ASCIIEncoding.ASCII.GetBytes("888888");
            byte[] field4 = ASCIIEncoding.ASCII.GetBytes("000000000000");
            byte[] systemTraceAuditNumber = ASCIIEncoding.ASCII.GetBytes("000008");
            byte[] field35Length = new byte[1] { 0x28 };
            byte[] field35CardId = MessageHelper.ToBcd(cardId);
            byte[] field35Paddle = new byte[6] { 0xD9,0x99,0x91,0x23,0x10,0x00 };
            byte[] field41 = ASCIIEncoding.ASCII.GetBytes(tid);
            byte[] field42 = ASCIIEncoding.ASCII.GetBytes(mid);

            /*0032FF21027208FF2F06052515412008FF340F020072070000000000052615240213 */
            byte[] header48 = new byte[10] { 0x00, 0x32, 0xFF, 0x21, 0x02, 0x77, 0x08, 0xFF, 0x2F, 0x06 };
            string invoiceVoid = MessageHelper.generateVoiceId();
            byte[] invoiceByte = MessageHelper.ToBcd(invoiceVoid);
            byte[] tailer48 = new byte[12] { 0xFF, 0x34, 0x0F, 0x02, 0x00, 0x72, 0x07, 0x00, 0x00, 0x00, 0x00, 0x00 };
            byte[] invoiceRedeemByte = MessageHelper.ToBcd(invoiceRedeem);
            byte[] field48 = new byte[34];
            Array.Copy(header48, field48, 10);
            Array.Copy(invoiceByte, 0, field48, 10, 6);
            Array.Copy(tailer48, 0, field48, 16, 12);
            Array.Copy(invoiceRedeemByte, 0, field48, 28, 6);

            byte[] headerPaddingField61 = new byte[5] { 0x00, 0x23, 0xFF, 0x3C, 0x14 };
            string paddingAmount = MessageHelper.PaddingAmount(amount, 8);
            byte[] bAmount = MessageHelper.ToBcd(paddingAmount);
            byte[] field61 = new byte[25];
            Array.Copy(headerPaddingField61, field61, 5);
            Array.Copy(bAmount, 0, field61, 5, 4);

            Debug.Connect();
            TcpClient client = null;
            try
            {
                client = new TcpClient(host, port);
            }
            catch (SocketException ex)
            {
                Console.WriteLine(ex.Message);
                Debug.Disconnect();
                return "Can not connect to server";
            }

            NetworkStream stream = client.GetStream();
            if (stream == null)
            {
                Debug.Disconnect();
                return "Can not get stream";
            }

            Debug.Send();
            Debug.Log(host, port.ToString());

            stream.Write(streamLength, 0, streamLength.Length);

            stream.Write(mti, 0, mti.Length);
            Debug.ShowField("0", MessageHelper.GetString(mti));

            stream.Write(bitmap, 0, bitmap.Length);
            Debug.ShowField("Bitmap", MessageHelper.GetString(bitmap));

            stream.Write(processingCode, 0, processingCode.Length);
            Debug.ShowField("3", MessageHelper.GetString(processingCode));

            stream.Write(field4, 0, field4.Length);
            Debug.ShowField("4", MessageHelper.GetString(field4));

            stream.Write(systemTraceAuditNumber, 0, systemTraceAuditNumber.Length);
            Debug.ShowField("11", MessageHelper.GetString(systemTraceAuditNumber));

            stream.Write(field35Length, 0, field35Length.Length);
            stream.Write(field35CardId, 0, field35CardId.Length);
            stream.Write(field35Paddle, 0, field35Paddle.Length);
            Debug.ShowField("35", MessageHelper.GetString(field35CardId) + MessageHelper.GetString(field35Paddle));

            stream.Write(field41, 0, field41.Length);
            Debug.ShowField("41", MessageHelper.GetString(field41));

            stream.Write(field42, 0, field42.Length);
            Debug.ShowField("42", MessageHelper.GetString(field42));

            stream.Write(field48, 0, field48.Length);
            Debug.ShowField("48", MessageHelper.GetString(field48));

            stream.Write(field61, 0, field61.Length);
            Debug.ShowField("61", MessageHelper.GetString(field61));

            Debug.EndLog();
            Debug.EndSend();

            int lenBuff = 0;
            byte[] bufferRespone = new byte[2048];
            lenBuff = stream.Read(bufferRespone, 0, 2048);
            if (lenBuff != -1)
            {
                byte[] response = new byte[lenBuff];
                Array.Copy(bufferRespone, response, lenBuff);

                byte[] bField61Response = new byte[50];
                Array.Copy(response, lenBuff - 50, bField61Response, 0, 50);

                string field61Response = MessageHelper.GetString(bField61Response);
                string totalAmount = MessageHelper.getFF3EValue(field61Response);
                string voidAmount = MessageHelper.getFF41Value(field61Response);

                result += "Void Reload Transaction\r\n";
                result += "CardId : " + cardId + "\r\n";
                result += "DateTime : " + DateTime.Now.ToString() + "\r\n";
                result += "InvoiceId : " + invoiceVoid + "\r\n";
                result += "Void Reload Amount : " + voidAmount + "\r\n";
                result += "Total amount : " + totalAmount + "\r\n";

                Debug.Receive();
                Debug.Dump(MessageHelper.GetString(response));
                Debug.EndReceive();
            }

            stream.Close();
            client.Close();
            Debug.Disconnect();
            return result;
        }
    }
}

using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using ClientSimulatorNet.org.hcmus.util;
using ClientSimulatorNet.org.hcmus.client;

namespace ClientSimulatorNet
{
    public partial class Form1 : Form
    {
        public static int priorTransaction = -1;
        public Form1()
        {
            InitializeComponent();
        }

        private void btnSend_Click(object sender, EventArgs e)
        {
            if (ConfigurationManagement.getHost() == "" || ConfigurationManagement.getPort() == 0 
                || ConfigurationManagement.getMID() == "" || ConfigurationManagement.getTID() == "")
            {
                MessageBox.Show("At first, please configure host,port,tid and mid in configuration tab.");
                return;
            }

            string card = txtCard.Text;
            if (card.Length != 16)
            {
                MessageBox.Show("CardId must consist 16 digits.");
                return;
            }

            string task = (string)cbxTransaction.SelectedItem;
            Console.WriteLine(task);

            if (!task.Equals("Balance"))
            {
                if (String.IsNullOrEmpty(txtAmount.Text))
                {
                    MessageBox.Show("Amount is empty");
                    return;
                }
            }

            if (task.Equals("VoidRedeem") || task.Equals("VoidReload"))
            {
                if (String.IsNullOrEmpty(txtInvoice.Text))
                {
                    MessageBox.Show("Invoive is empty");
                    return;
                }

                if (txtInvoice.Text.Length != 12)
                {
                    MessageBox.Show("Invoice must consist of 12 digits.");
                    return;
                }
            }

            
            string result = "";
            if (task.Equals("Balance"))
            {
                result = Client.Balance(card, ConfigurationManagement.getHost(), ConfigurationManagement.getPort(), ConfigurationManagement.getMID(), ConfigurationManagement.getTID());
                priorTransaction = -1;
                btnVoid.Enabled = false;
            }
            else if (task.Equals("Redeem"))
            {
                result = Client.Redeem(card,int.Parse(txtAmount.Text), ConfigurationManagement.getHost(), ConfigurationManagement.getPort(), ConfigurationManagement.getMID(), ConfigurationManagement.getTID());
                priorTransaction = 0;
                btnVoid.Enabled = true;
            }
            else if(task.Equals("Reload"))
            {
                result = Client.ReLoad(card, int.Parse(txtAmount.Text), ConfigurationManagement.getHost(), ConfigurationManagement.getPort(), ConfigurationManagement.getMID(), ConfigurationManagement.getTID());
                priorTransaction = 1;
                btnVoid.Enabled = true;
            }
            else if (task.Equals("VoidRedeem"))
            {
                result = Client.VoidRedeem(card, int.Parse(txtAmount.Text),txtInvoice.Text, ConfigurationManagement.getHost(), ConfigurationManagement.getPort(), ConfigurationManagement.getMID(), ConfigurationManagement.getTID());
                priorTransaction = -1;
                btnVoid.Enabled = false;
            }
            else if (task.Equals("VoidReload"))
            {
                result = Client.VoidReload(card, int.Parse(txtAmount.Text), txtInvoice.Text, ConfigurationManagement.getHost(), ConfigurationManagement.getPort(), ConfigurationManagement.getMID(), ConfigurationManagement.getTID());
                priorTransaction = -1;
                btnVoid.Enabled = false;
            }

            txtResult.Text = result;
        }

        private void btnConfigre_Click(object sender, EventArgs e)
        {
            if (txtHost.Text == "")
            {
                MessageBox.Show("Host is empty");
                return;
            }

            if (txtPort.Text == "")
            {
                MessageBox.Show("Port is empty");
                return;
            }

            if (txtMID.Text == "")
            {
                MessageBox.Show("MID is empty");
                return;
            }

            if (txtTID.Text == "")
            {
                MessageBox.Show("TID is empty");
                return;
            }

            if (txtMID.Text.Length != 15)
            {
                MessageBox.Show("MID must consists 15 digits.");
                return;
            }

            if (txtTID.Text.Length != 8)
            {
                MessageBox.Show("TID must consists 8 digits.");
                return;
            }

            ConfigurationManagement.setHost(txtHost.Text);
            ConfigurationManagement.setPort(Int32.Parse(txtPort.Text));
            ConfigurationManagement.setMID(txtMID.Text);
            ConfigurationManagement.setTID(txtTID.Text);

            MessageBox.Show("Configuration is successful.");
        }

        private void isDigit(object sender, KeyPressEventArgs e)
        {
            if (!Char.IsDigit(e.KeyChar) && !Char.IsControl(e.KeyChar))
            {
                e.Handled = true;
            }
        }

        private void cbxTransaction_SelectedIndexChanged(object sender, EventArgs e)
        {
            string item = (string)cbxTransaction.SelectedItem;
            if (item.Equals("VoidReload") || item.Equals("VoidRedeem"))
            {
                txtInvoice.Enabled = true;
            }
            else
            {
                txtInvoice.Enabled = false;
            }

            if (item.Equals("Balance"))
            {
                txtAmount.Enabled = false;
            }
            else
            {
                txtAmount.Enabled = true;
            }
        }

        private void btnVoid_Click(object sender, EventArgs e)
        {
            if(priorTransaction != -1)
            {
                cbxTransaction.SelectedItem = (string)cbxTransaction.Items[priorTransaction + 3];
                txtInvoice.Text = Client.redeemReloadId;
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            btnVoid.Enabled = false;
        }

        
    }
}

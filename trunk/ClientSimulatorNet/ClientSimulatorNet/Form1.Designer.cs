namespace ClientSimulatorNet
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.barCodeTab = new System.Windows.Forms.TabControl();
            this.tabPageBarCode = new System.Windows.Forms.TabPage();
            this.btnVoid = new System.Windows.Forms.Button();
            this.btnSend = new System.Windows.Forms.Button();
            this.label4 = new System.Windows.Forms.Label();
            this.txtAmount = new System.Windows.Forms.TextBox();
            this.txtInvoice = new System.Windows.Forms.TextBox();
            this.txtCard = new System.Windows.Forms.TextBox();
            this.txtResult = new System.Windows.Forms.TextBox();
            this.cbxTransaction = new System.Windows.Forms.ComboBox();
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.tabPageConfig = new System.Windows.Forms.TabPage();
            this.btnConfigre = new System.Windows.Forms.Button();
            this.txtTID = new System.Windows.Forms.TextBox();
            this.txtMID = new System.Windows.Forms.TextBox();
            this.txtPort = new System.Windows.Forms.TextBox();
            this.txtHost = new System.Windows.Forms.TextBox();
            this.label8 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.barCodeTab.SuspendLayout();
            this.tabPageBarCode.SuspendLayout();
            this.tabPageConfig.SuspendLayout();
            this.SuspendLayout();
            // 
            // barCodeTab
            // 
            this.barCodeTab.Controls.Add(this.tabPageBarCode);
            this.barCodeTab.Controls.Add(this.tabPageConfig);
            this.barCodeTab.Dock = System.Windows.Forms.DockStyle.Fill;
            this.barCodeTab.Location = new System.Drawing.Point(0, 0);
            this.barCodeTab.Name = "barCodeTab";
            this.barCodeTab.SelectedIndex = 0;
            this.barCodeTab.Size = new System.Drawing.Size(565, 361);
            this.barCodeTab.TabIndex = 0;
            // 
            // tabPageBarCode
            // 
            this.tabPageBarCode.Controls.Add(this.btnVoid);
            this.tabPageBarCode.Controls.Add(this.btnSend);
            this.tabPageBarCode.Controls.Add(this.label4);
            this.tabPageBarCode.Controls.Add(this.txtAmount);
            this.tabPageBarCode.Controls.Add(this.txtInvoice);
            this.tabPageBarCode.Controls.Add(this.txtCard);
            this.tabPageBarCode.Controls.Add(this.txtResult);
            this.tabPageBarCode.Controls.Add(this.cbxTransaction);
            this.tabPageBarCode.Controls.Add(this.label3);
            this.tabPageBarCode.Controls.Add(this.label2);
            this.tabPageBarCode.Controls.Add(this.label1);
            this.tabPageBarCode.Location = new System.Drawing.Point(4, 22);
            this.tabPageBarCode.Name = "tabPageBarCode";
            this.tabPageBarCode.Padding = new System.Windows.Forms.Padding(3);
            this.tabPageBarCode.Size = new System.Drawing.Size(557, 335);
            this.tabPageBarCode.TabIndex = 0;
            this.tabPageBarCode.Text = "BarCode";
            this.tabPageBarCode.UseVisualStyleBackColor = true;
            // 
            // btnVoid
            // 
            this.btnVoid.Location = new System.Drawing.Point(459, 192);
            this.btnVoid.Name = "btnVoid";
            this.btnVoid.Size = new System.Drawing.Size(75, 45);
            this.btnVoid.TabIndex = 10;
            this.btnVoid.Text = "Void This Transaction";
            this.btnVoid.UseVisualStyleBackColor = true;
            this.btnVoid.Click += new System.EventHandler(this.btnVoid_Click);
            // 
            // btnSend
            // 
            this.btnSend.Location = new System.Drawing.Point(459, 24);
            this.btnSend.Name = "btnSend";
            this.btnSend.Size = new System.Drawing.Size(75, 23);
            this.btnSend.TabIndex = 9;
            this.btnSend.Text = "Send";
            this.btnSend.UseVisualStyleBackColor = true;
            this.btnSend.Click += new System.EventHandler(this.btnSend_Click);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(25, 154);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(63, 13);
            this.label4.TabIndex = 8;
            this.label4.Text = "Transaction";
            // 
            // txtAmount
            // 
            this.txtAmount.Enabled = false;
            this.txtAmount.Location = new System.Drawing.Point(90, 110);
            this.txtAmount.Name = "txtAmount";
            this.txtAmount.Size = new System.Drawing.Size(353, 20);
            this.txtAmount.TabIndex = 7;
            this.txtAmount.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.isDigit);
            // 
            // txtInvoice
            // 
            this.txtInvoice.Enabled = false;
            this.txtInvoice.Location = new System.Drawing.Point(90, 68);
            this.txtInvoice.Name = "txtInvoice";
            this.txtInvoice.Size = new System.Drawing.Size(353, 20);
            this.txtInvoice.TabIndex = 6;
            // 
            // txtCard
            // 
            this.txtCard.Location = new System.Drawing.Point(90, 26);
            this.txtCard.Name = "txtCard";
            this.txtCard.Size = new System.Drawing.Size(353, 20);
            this.txtCard.TabIndex = 5;
            // 
            // txtResult
            // 
            this.txtResult.Enabled = false;
            this.txtResult.ForeColor = System.Drawing.Color.Blue;
            this.txtResult.Location = new System.Drawing.Point(28, 192);
            this.txtResult.Multiline = true;
            this.txtResult.Name = "txtResult";
            this.txtResult.Size = new System.Drawing.Size(415, 122);
            this.txtResult.TabIndex = 4;
            // 
            // cbxTransaction
            // 
            this.cbxTransaction.BackColor = System.Drawing.SystemColors.Window;
            this.cbxTransaction.FormattingEnabled = true;
            this.cbxTransaction.Items.AddRange(new object[] {
            "Balance",
            "Redeem",
            "Reload",
            "VoidRedeem",
            "VoidReload"});
            this.cbxTransaction.Location = new System.Drawing.Point(90, 154);
            this.cbxTransaction.Name = "cbxTransaction";
            this.cbxTransaction.Size = new System.Drawing.Size(110, 21);
            this.cbxTransaction.TabIndex = 3;
            this.cbxTransaction.Text = "Balance";
            this.cbxTransaction.SelectedIndexChanged += new System.EventHandler(this.cbxTransaction_SelectedIndexChanged);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(22, 117);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(43, 13);
            this.label3.TabIndex = 2;
            this.label3.Text = "Amount";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(22, 71);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(53, 13);
            this.label2.TabIndex = 1;
            this.label2.Text = "InvoiceID";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(22, 29);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(40, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "CardID";
            // 
            // tabPageConfig
            // 
            this.tabPageConfig.Controls.Add(this.btnConfigre);
            this.tabPageConfig.Controls.Add(this.txtTID);
            this.tabPageConfig.Controls.Add(this.txtMID);
            this.tabPageConfig.Controls.Add(this.txtPort);
            this.tabPageConfig.Controls.Add(this.txtHost);
            this.tabPageConfig.Controls.Add(this.label8);
            this.tabPageConfig.Controls.Add(this.label7);
            this.tabPageConfig.Controls.Add(this.label6);
            this.tabPageConfig.Controls.Add(this.label5);
            this.tabPageConfig.Location = new System.Drawing.Point(4, 22);
            this.tabPageConfig.Name = "tabPageConfig";
            this.tabPageConfig.Padding = new System.Windows.Forms.Padding(3);
            this.tabPageConfig.Size = new System.Drawing.Size(557, 335);
            this.tabPageConfig.TabIndex = 1;
            this.tabPageConfig.Text = "Configure";
            this.tabPageConfig.UseVisualStyleBackColor = true;
            // 
            // btnConfigre
            // 
            this.btnConfigre.Location = new System.Drawing.Point(474, 31);
            this.btnConfigre.Name = "btnConfigre";
            this.btnConfigre.Size = new System.Drawing.Size(75, 23);
            this.btnConfigre.TabIndex = 8;
            this.btnConfigre.Text = "Configure";
            this.btnConfigre.UseVisualStyleBackColor = true;
            this.btnConfigre.Click += new System.EventHandler(this.btnConfigre_Click);
            // 
            // txtTID
            // 
            this.txtTID.Location = new System.Drawing.Point(104, 200);
            this.txtTID.Name = "txtTID";
            this.txtTID.Size = new System.Drawing.Size(356, 20);
            this.txtTID.TabIndex = 7;
            this.txtTID.Text = "00000001";
            // 
            // txtMID
            // 
            this.txtMID.Location = new System.Drawing.Point(104, 142);
            this.txtMID.Name = "txtMID";
            this.txtMID.Size = new System.Drawing.Size(356, 20);
            this.txtMID.TabIndex = 6;
            this.txtMID.Text = "000000000000001";
            // 
            // txtPort
            // 
            this.txtPort.Location = new System.Drawing.Point(104, 82);
            this.txtPort.Name = "txtPort";
            this.txtPort.Size = new System.Drawing.Size(356, 20);
            this.txtPort.TabIndex = 5;
            this.txtPort.Text = "10000";
            this.txtPort.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.isDigit);
            // 
            // txtHost
            // 
            this.txtHost.Location = new System.Drawing.Point(104, 34);
            this.txtHost.Name = "txtHost";
            this.txtHost.Size = new System.Drawing.Size(356, 20);
            this.txtHost.TabIndex = 4;
            this.txtHost.Text = "127.0.0.1";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(39, 200);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(25, 13);
            this.label8.TabIndex = 3;
            this.label8.Text = "TID";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(39, 142);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(27, 13);
            this.label7.TabIndex = 2;
            this.label7.Text = "MID";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(39, 89);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(26, 13);
            this.label6.TabIndex = 1;
            this.label6.Text = "Port";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(39, 37);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(29, 13);
            this.label5.TabIndex = 0;
            this.label5.Text = "Host";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(565, 361);
            this.Controls.Add(this.barCodeTab);
            this.Name = "Form1";
            this.Text = "Client Simulator POS(ASCII channel)";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.barCodeTab.ResumeLayout(false);
            this.tabPageBarCode.ResumeLayout(false);
            this.tabPageBarCode.PerformLayout();
            this.tabPageConfig.ResumeLayout(false);
            this.tabPageConfig.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl barCodeTab;
        private System.Windows.Forms.TabPage tabPageBarCode;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TabPage tabPageConfig;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox txtAmount;
        private System.Windows.Forms.TextBox txtInvoice;
        private System.Windows.Forms.TextBox txtCard;
        private System.Windows.Forms.TextBox txtResult;
        private System.Windows.Forms.ComboBox cbxTransaction;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Button btnConfigre;
        private System.Windows.Forms.TextBox txtTID;
        private System.Windows.Forms.TextBox txtMID;
        private System.Windows.Forms.TextBox txtPort;
        private System.Windows.Forms.TextBox txtHost;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Button btnSend;
        private System.Windows.Forms.Button btnVoid;
    }
}


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hcmus.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.hcmus.jpos.Client;
import org.hcmus.util.MessageHelper;

/**
 * 
 * @author HungPT
 */
public class BarCodePanel extends JPanel implements ActionListener {

	JLabel lblBarCode;
	JLabel lblInvoice;
	JLabel lblTransaction;
	JLabel lblAmount;

	JTextField txtBarCode;
	JTextField txtInvoice;
	JTextField txtAmount;

	JComboBox cbxTransaction;
	Vector<String> transactionItem = new Vector<String>();
	JButton btnSend;
	JTextArea txtResult;

	public BarCodePanel() {

		transactionItem.add("Redeem");
		transactionItem.add("Reload");
		transactionItem.add("VoidRedeem");
		transactionItem.add("VoidReload");
		transactionItem.add("Balance");

		txtBarCode = new JTextField(30);
		txtInvoice = new JTextField(30);
		txtInvoice.enable(false);
		txtInvoice.setBackground(new Color(220,220,220));
		txtAmount = new JTextField(30);
		cbxTransaction = new JComboBox(transactionItem);
		cbxTransaction.setActionCommand("cbxClick");
		cbxTransaction.addActionListener(this);

		btnSend = new JButton("Send");
		btnSend.setActionCommand("SendClick");
		btnSend.addActionListener(this);

		txtResult = new JTextArea(10, 1);
		txtResult.enable(false);
		txtResult.setForeground(new Color(255, 0, 0));

		lblBarCode = new JLabel(" Barcode          ");
		lblInvoice = new JLabel(" Invoice             ");
		lblTransaction = new JLabel(" Transaction    ");
		lblAmount = new JLabel(" Amount            ");

		JPanel barCodePanel = new JPanel();
		barCodePanel.setLayout(new BorderLayout());
		JPanel barCodeChildPanel = new JPanel();
		barCodeChildPanel.setLayout(new BorderLayout());
		barCodeChildPanel.add(txtBarCode, BorderLayout.WEST);
		barCodePanel.add(lblBarCode, BorderLayout.WEST);
		barCodePanel.add(barCodeChildPanel, FlowLayout.CENTER);
		barCodePanel.add(btnSend, BorderLayout.EAST);

		JPanel invoicePanel = new JPanel();
		invoicePanel.setLayout(new BorderLayout());
		JPanel invoiceChildPanel = new JPanel();
		invoiceChildPanel.setLayout(new BorderLayout());
		invoiceChildPanel.add(txtInvoice, BorderLayout.WEST);
		invoicePanel.add(lblInvoice, BorderLayout.WEST);
		invoicePanel.add(invoiceChildPanel, BorderLayout.CENTER);

		JPanel amountPanel = new JPanel();
		amountPanel.setLayout(new BorderLayout());
		JPanel amountChildPanel = new JPanel();
		amountChildPanel.setLayout(new BorderLayout());
		amountChildPanel.add(txtAmount, BorderLayout.WEST);
		amountPanel.add(lblAmount, BorderLayout.WEST);
		amountPanel.add(amountChildPanel, BorderLayout.CENTER);

		JPanel transactionPanel = new JPanel();
		transactionPanel.setLayout(new BorderLayout());
		JPanel transactionChildPanel = new JPanel();
		transactionChildPanel.setLayout(new BorderLayout());
		transactionChildPanel.add(cbxTransaction, BorderLayout.WEST);
		transactionPanel.add(lblTransaction, BorderLayout.WEST);
		transactionPanel.add(transactionChildPanel, FlowLayout.CENTER);

		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		northPanel.add(barCodePanel, BorderLayout.NORTH);
		northPanel.add(invoicePanel, BorderLayout.WEST);
		northPanel.add(amountPanel, BorderLayout.SOUTH);

		this.setLayout(new BorderLayout());
		this.add(northPanel, BorderLayout.NORTH);
		this.add(transactionPanel, BorderLayout.CENTER);
		this.add(txtResult, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("cbxClick")) {
			String t = (String)cbxTransaction.getSelectedItem();
			if(t.equals("VoidRedeem") || t.equals("VoidReload")){
				txtInvoice.enable();
				txtInvoice.setBackground(new Color(255,255,255));
			}else{
				txtInvoice.enable(false);
				txtInvoice.setBackground(new Color(220,220,220));
			}
			
			if(t.equals("Balance")){
				txtAmount.enable(false);
				txtAmount.setBackground(new Color(220,220,220));
			}else{
				txtAmount.enable();
				txtAmount.setBackground(new Color(255,255,255));
			}
			
		} else {
			if (ConfigManager.getIp().isEmpty() || ConfigManager.getPort() == 0
					|| ConfigManager.getMid().isEmpty()
					|| ConfigManager.getTid().isEmpty()) {
				JOptionPane.showMessageDialog(this,"FirstLy, Please configure ip,port,tid and mid in config tab. ");
				return;
			}

			String task = (String) cbxTransaction.getSelectedItem();

			if (txtBarCode.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Barcode is empty");
				return;
			}
			
			if( task.equals("VoidRedeem") || task.equals("VoidReload")){
				if (txtInvoice.getText().isEmpty()) {
					JOptionPane.showMessageDialog(this, "InvoiceNumber is empty");
					return;
				}
				if (txtInvoice.getText().length() != 12) {
					JOptionPane.showMessageDialog(this, "InvoiceNumber must have 12 number.");
					return;
				}
			}
			
			String barCode = txtBarCode.getText();
			
			if (barCode.length() != 16) {
				JOptionPane.showMessageDialog(this, "BarCode must consist of 16 digits");
				return;
			}
			
			String amountString = "";
			int amount = 0;
			if(!task.equals("Balance")){
				amountString = txtAmount.getText();
				if(amountString.isEmpty()){
					JOptionPane.showMessageDialog(this, "Amount is empty");
					return;
				}
				
				if (!MessageHelper.isDigits(amountString)) {
					JOptionPane
							.showMessageDialog(this, "Amount must be a digits. ");
					return;
				}
	
				amount = Integer.parseInt(amountString);
			}

			String result = "";
			txtResult.setText("Sending...Please wait...");
			
			if (task.equals("Redeem")) {
				result = Client.redeem(barCode, amount, ConfigManager.getIp(),
						ConfigManager.getPort(), ConfigManager.getTid(),
						ConfigManager.getMid());
				txtResult.setText(result);
			} else if (task.equals("Reload")) {
				result = Client.reload(barCode, amount, ConfigManager.getIp(),
						ConfigManager.getPort(), ConfigManager.getTid(),
						ConfigManager.getMid());
				txtResult.setText(result);
			} else if (task.equals("VoidRedeem")) {
				result = Client.voidRedeem(barCode, txtInvoice.getText(), amount,
						ConfigManager.getIp(), ConfigManager.getPort(),
						ConfigManager.getTid(), ConfigManager.getMid());
				txtResult.setText(result);
			} else if (task.equals("VoidReload")) {
				result = Client.voidReload(barCode, txtInvoice.getText(), amount,
						ConfigManager.getIp(), ConfigManager.getPort(),
						ConfigManager.getTid(), ConfigManager.getMid());
				txtResult.setText(result);
			} else if (task.equals("Balance")) {
				result = Client.balance(barCode, ConfigManager.getIp(), ConfigManager
						.getPort(), ConfigManager.getTid(), ConfigManager
						.getMid());
				txtResult.setText(result);
			}
		}
	}
}

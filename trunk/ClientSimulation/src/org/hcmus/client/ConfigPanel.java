/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hcmus.client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hcmus.util.MessageHelper;

/**
 * 
 * @author HungPT
 */
public class ConfigPanel extends JPanel implements ActionListener {
 
	JLabel lblIP;
	JLabel lblPort;
	JLabel lblMID;
	JLabel lblTID;
	
	JTextField txtIP ;
	JTextField txtPort;
	JTextField txtTID;
	JTextField txtMID;
	JButton btnConfig;
	
	public ConfigPanel() {
    	txtIP = new JTextField(30);
    	txtIP.setText("127.0.0.1");
		txtPort = new JTextField(30);
		txtPort.setText("9500");
		txtMID = new JTextField(30);
		txtMID.setText("000000000000001");
		txtTID = new JTextField(30);
		txtTID.setText("00000001");
		
		btnConfig = new JButton("Config");
		btnConfig.addActionListener(this);
		
		lblIP = new JLabel("IP");
		lblPort = new JLabel("Port   ");
		lblMID = new JLabel("MID   ");
		lblTID = new JLabel("TID    ");
		
    	JPanel ipPanel = new JPanel();
    	ipPanel.setLayout(new FlowLayout());
    	ipPanel.add(lblIP,FlowLayout.LEFT);
    	ipPanel.add(txtIP,FlowLayout.CENTER);
    	ipPanel.add(btnConfig,FlowLayout.RIGHT);
    	
    	JPanel portPanel = new JPanel();
    	portPanel.setLayout(new FlowLayout());
    	portPanel.add(lblPort,FlowLayout.LEFT);
    	portPanel.add(txtPort,FlowLayout.CENTER);
    	
    	JPanel midPanel = new JPanel();
    	midPanel.setLayout(new FlowLayout());
    	midPanel.add(lblMID,FlowLayout.LEFT);
    	midPanel.add(txtMID,FlowLayout.CENTER);
    	
    	JPanel tidPanel = new JPanel();
    	tidPanel.setLayout(new FlowLayout());
    	tidPanel.add(lblTID,FlowLayout.LEFT);
    	tidPanel.add(txtTID,FlowLayout.CENTER);
    	
    	JPanel northPanel = new JPanel();
    	northPanel.setLayout(new BorderLayout());
    	northPanel.add(ipPanel,BorderLayout.NORTH);
    	northPanel.add(portPanel,BorderLayout.WEST);
    	
    	JPanel westPanel = new JPanel();
    	westPanel.setLayout(new BorderLayout());
    	westPanel.add(midPanel,BorderLayout.NORTH);
    	westPanel.add(tidPanel,BorderLayout.WEST);
    	
    	this.setLayout(new BorderLayout());
    	this.add(northPanel, BorderLayout.NORTH);
    	this.add(westPanel,BorderLayout.WEST);
	}

	public void actionPerformed(ActionEvent e) { 
		if(txtIP.getText().isEmpty()){
			JOptionPane.showMessageDialog(this, "ip empty");
			return;
		}
		
		if(txtPort.getText().isEmpty()){
			JOptionPane.showMessageDialog(this, "port empty");
			return;
	
		}
		
		if(txtMID.getText().isEmpty()){
			JOptionPane.showMessageDialog(this, "MID empty");
			return;
	
		}
		
		if(txtTID.getText().isEmpty()){
			JOptionPane.showMessageDialog(this, "TID empty");
			return;
	
		}
		
		if(txtMID.getText().length() != 15){
			JOptionPane.showMessageDialog(this, "MID must have 15 digits");
			return;
	
		}
		
		if(txtTID.getText().length() != 8){
			JOptionPane.showMessageDialog(this, "TID must have 8 digits");
			return;
	
		}
		
		if(!MessageHelper.isDigits(txtPort.getText())){
			JOptionPane.showMessageDialog(this, "Port must consist of digits");
			return;
		}
		
		ConfigManager.setIp(txtIP.getText());
		ConfigManager.setPort(Integer.parseInt(txtPort.getText()));
		ConfigManager.setTid(txtTID.getText());
		ConfigManager.setMid(txtMID.getText());
		
		JOptionPane.showMessageDialog(this, "Configuation is sucessful.");
	}
}

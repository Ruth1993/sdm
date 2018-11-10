package gui;

import java.awt.*; 
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import databaseAccess.DBConnection;
import sdm.Company;
import sdm.Person;

public class GUI2 extends JFrame implements ActionListener {
	  private Connection connection;
	  
	  private Person p;
	  
	  private JPanel start_panel;
	  
	  private JLabel start_title; 
	  private JLabel l_id;
	  private JTextField t_id;
	  private JButton start_submit;
	  
	  public GUI2() {
	    super("PHR system interface - start screen"); 
	    
		// Test DB connection
		connection = DBConnection.getConnection();
		
	    this.p = new Person(7, "Donald Trump");
	    this.p.setAttrs(new String[]{"Doctor"});
		
		start_panel = new JPanel();
		start_panel.setLayout(new BoxLayout(start_panel, BoxLayout.Y_AXIS));

	    pack();
	    setLocationRelativeTo(null); 
	    setSize(600, 400);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Container con = this.getContentPane();
	    
	    con.add(this.start_panel);
	    
	    showStartScreen();
	    
	    setVisible(true); // make frame visible
	  }
	  
	  public void showStartScreen() {  
	      start_title = new JLabel("Login screen");
	      l_id = new JLabel("Id:");
	      t_id = new JTextField(20);
	        
	      start_submit.addActionListener(this);
		    
		  this.start_panel.add(l_id);
		  this.start_panel.add(t_id);
		  this.start_panel.add(start_submit);
	  }
	  
	  public void actionPerformed(ActionEvent event)
	  {
	    Object source = event.getSource();
	    
	    if(source == this.start_submit) {
	    	  int id = Integer.parseInt(t_id.getText());
	    	  new DoctorWindow(this.connection, id).setVisible(true);
	    	  this.dispose();
	    }
	  }
	  
	  public Connection getConnection() {
		  return this.connection;
	  }
	  
	  public static void main(String args[]) {	  
		  new GUI2();
	  }
}

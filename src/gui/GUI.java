package gui;

import java.awt.*; 
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

import databaseAccess.DBConnection;
import sdm.Person;

public class GUI extends JFrame implements ActionListener {
	  private Connection connection;
	  
	  private Person p;
	  
	  private JPanel start_panel;
	  
	  private JLabel start_title = new JLabel("Start screen");
	  private JLabel start_label = new JLabel("Please insert your identity below");
	  private JTextField start_insert_id = new JTextField(20);
	  private JButton start_submit = new JButton("Submit");
	  
	  //Patient screen components
	  private JLabel patient_title = new JLabel("Start screen");
	  
	  //Insurance screen components
	  private JLabel insurance_title = new JLabel("Start screen");
	  
	  //Employer screen components
	  private JLabel employer_title = new JLabel("Start screen");
	  
	  public GUI() {
	    super("PHR system interface - start screen"); 
	    
		// Test DB connection
		connection = DBConnection.getConnection();
		
	    this.p = new Person(7, "Donald Trump", new String[]{"Doctor"});
		
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
	      start_label.setSize(200, 30);
	      start_label.setHorizontalAlignment(0);
	        
	      start_insert_id.setLocation(0, 100);
	      start_insert_id.setSize(100, 30);
	        
	      start_submit.setLocation(0, 200);
	        
	      start_submit.addActionListener(this);
		    
		  this.start_panel.add(start_label);
		  this.start_panel.add(start_insert_id);
		  this.start_panel.add(start_submit);
	  }
	  
	  public void actionPerformed(ActionEvent event)
	  {
	    Object source = event.getSource();
	    
	    if(source == this.start_submit) {
	      if(this.start_insert_id.getText().equals("Doctor") || this.start_insert_id.getText().equals("d")) {
	    	  new DoctorWindow(this.connection, this.p).setVisible(true);
	    	  this.dispose();
	      } else if(this.start_insert_id.getText().equals("Patient")) {
	    	  new PatientWindow().setVisible(true);
	    	  this.dispose();
	      } else if(this.start_insert_id.getText().equals("Insurance")) {
	    	  new InsuranceWindow().setVisible(true);
	    	  this.dispose();
	      } else if (this.start_insert_id.getText().equals("Employer")) {
	    	  new EmployerWindow().setVisible(true);
	    	  this.dispose();
	      }
	    }
	  }
	  
	  public Connection getConnection() {
		  return this.connection;
	  }
	  
	  public static void main(String args[]) {	  
		  new GUI();
	  }
}

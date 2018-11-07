package gui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.*;

import sdm.Company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DoctorWindow extends JFrame {
	  //Panel
	  private JPanel doctor_panel;
	  
	  //Title
	  private JLabel doctor_title;
	  
	  //Menu components
	  private JMenuBar menu;
	  private JMenu read, add, change;
	  private JMenuItem read_persons_basic_info, read_patients_basic_info, read_patients_visit, read_patients_medicine;
	  private JMenuItem add_patient;
	  private JMenuItem change_patient;
	  
	  public DoctorWindow() {
		    super("PHR system interface - Doctor panel"); 
		    
		    this.doctor_panel = new JPanel(new BorderLayout());
		    
		    //Set the size and alignment of title
		    this.doctor_title = new JLabel("Start screen");
		    this.doctor_title.setSize(200, 30);
		    this.doctor_title.setHorizontalAlignment(0);
		  
		    //Menu
		    this.menu = new JMenuBar();
		    
		    //Menu with their items
		    this.read = new JMenu("Read");
		    this.read_persons_basic_info = new JMenuItem("Persons basic info");
		    this.read_patients_basic_info = new JMenuItem("Patients basic info");
		    this.read_patients_visit = new JMenuItem("Patients visits");
		    this.read_patients_medicine = new JMenuItem("Patients medicines");
		    this.read.add(read_persons_basic_info);
		    this.read.addSeparator();
		    this.read.add(read_patients_basic_info);
		    this.read.add(read_patients_visit);
		    this.read.add(read_patients_medicine);
		    
		    this.add = new JMenu("Add");
		    this.add_patient = new JMenuItem("New patient");
		    this.add.add(this.add_patient);
		    
		    this.change = new JMenu("Change");
		    
		    this.menu.add(read); 
		    this.menu.add(add);
		    this.menu.add(change);
 
			this.doctor_panel.add(menu, BorderLayout.NORTH);
			this.doctor_panel.add(doctor_title);
			//this.doctor_panel.add(start_submit);
			
		    pack();
		    setLocationRelativeTo(null); 
		    setSize(600, 400);
		    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    Container con = this.getContentPane();
		    
		    con.add(this.doctor_panel);
	  }
	  
	  /*
	   * Show a table with all the persons
	   * TODO needs work
	   */
	  public void showPersonBasicInfo(Connection connection) {
		  JTable table = new JTable(1, 8);
		  
		  try {
				Statement Statement = connection.createStatement();
				ResultSet res = Statement.executeQuery("SELECT * FROM sdmproject.persons_basic_info");
				while (res.next()) {
					String[] row = new String[8];
					row[0] = res.getString("id");
					row[1] = res.getString("name");
					row[2] = res.getString("birth_date");
					row[3] = res.getString("birth_place");
					row[4] = res.getString("gender");
					row[5] = res.getString("nationality");
					row[6] = res.getString("address");
					row[7] = res.getString("phone_number");
				}
				res.close();
				Statement.close();
		  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		  }
	  }
	  
	  public void showPersonBasicInfo(Connection connection, int person_id) {
		  String rowData[][] = new String[1][8];
		  String columnNames[] = {"Id", "Name", "Birth date", "Birth place", "Gender", "Nationality", "Address", "Phone number"};
		  
		  try {
				Statement Statement = connection.createStatement();
				ResultSet res = Statement.executeQuery("SELECT * FROM sdmproject.persons_basic_info WHERE id="+person_id);
				while (res.next()) {
					String[] row = new String[8];
					row[0] = res.getString("id");
					row[1] = res.getString("name");
					row[2] = res.getString("birth_date");
					row[3] = res.getString("birth_place");
					row[4] = res.getString("gender");
					row[5] = res.getString("nationality");
					row[6] = res.getString("address");
					row[7] = res.getString("phone_number");
					rowData[0] = row;
				}
				res.close();
				Statement.close();
		  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		  }
		  
		  JTable table = new JTable(rowData, columnNames);
	  }
	  
	  public void showPatientBasicInfo(Connection connection) {
		  
	  }
	  
	  public void showPatientsVisit(Connection connection) {
		  
	  }
	  
	  public void showPatientsMedicine(Connection connection) {
		  
	  }
	  
	  //TODO remove this as soon as everything is working, this function is just so that you don't have to enter the id in GUI to enter DoctorWindow (for testing purposes)
	  public static void main(String args[]) {
		  new DoctorWindow();
	  }
}

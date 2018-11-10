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
import sdm.Person;

public class GUI extends JFrame implements ActionListener {
	  //Connection
	  private Connection connection;
	  
	  //Id of the person using the gui
	  private Person p;
	  
	  //Panels
	  private JPanel cards;
	  private JPanel menu_panel;
	  private JPanel content_panel;
	  private JPanel read_pe_info_panel;
	  private JPanel read_pa_info_panel;
	  private JPanel read_med_visit_panel;
	  
	  //Menu components
	  private JMenuBar menu;
	  private JMenu read, add, update;
	  private JMenuItem read_persons_basic_info, read_patients_basic_info, read_patients_visit, read_patients_medicine, read_healthclub_visit;
	  private JMenuItem add_person, add_patient, add_visit, add_medicine, add_healthclub_visit;
	  private JMenuItem update_person, update_patient;
	  
	  //Read person/patient/medical visit info
	  private JTextField t_uid;
	  private JButton s_read_pe_info;
	  private JButton s_read_pa_info;
	  private JButton s_read_med_visit;
	  
	  //Add person info components
	  private JTextField[] t_add_person;
	  private JButton s_add_person;
	  
	  //Add patient info components
	  private JTextField t_id;
	  private JTextField t_bloodtype;
	  private JTextField t_weight;
	  private JTextField t_height;
	  private JTextField t_emerg_contact;
	  private JTextField t_id_doc;
	  private JButton s_addpatient;
	  
	  //Add patient visit components
	  private JTextField t_id_patient;
	  private JTextField t_vis_date_start;
	  private JTextField t_vis_date_end;
	  private JTextField t_reason;
	  private JTextField t_results;
	  private JTextField t_id_hospital_doctors;
	  private JButton s_addvisit;
	  
	  //Add medicines components;
	  private JTextField t_med_name;
	  private JTextField t_dosage;
	  private JTextField t_med_date_start;
	  private JTextField t_med_date_end;
	  private JTextField t_id_visit;
	  private JButton s_addmedicine;
	  
	  //Update
	  
	  public GUI(Connection connection, Person p) {
		    super("PHR system interface"); 
		    
		    this.connection = connection;
		    
		    this.p = p;
		    
		    menu_panel = new JPanel(new BorderLayout());
		    content_panel = new JPanel(new BorderLayout());
		  
		    showMenu();
			
		    pack();
		    setLocationRelativeTo(null); 
		    setSize(600, 400);
		    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    Container con = this.getContentPane();
		    
	        con.add(menu_panel, BorderLayout.NORTH);
	        con.add(content_panel, BorderLayout.CENTER);
	        
	        setVisible(true);
	  }
	  
	  public void showMenu() {
		    //Menu
		    this.menu = new JMenuBar();
		    
		    //Menu with their items
		    //Menu item Read
		    this.read = new JMenu("Read");
		    this.read_persons_basic_info = new JMenuItem("Persons basic info");
		    this.read_patients_basic_info = new JMenuItem("Patients basic info");
		    this.read_patients_visit = new JMenuItem("Patients visits");
		    this.read_patients_medicine = new JMenuItem("Patients medicines");
		    this.read_healthclub_visit = new JMenuItem("Healthclub visit");
		    this.read.add(read_persons_basic_info);
		    this.read.addSeparator();
		    this.read.add(read_patients_basic_info);
		    this.read.add(read_patients_visit);
		    this.read.add(read_patients_medicine);
		    this.read.addSeparator();
		    this.read.add(read_healthclub_visit);
		    
		    //Menu item Add
		    this.add = new JMenu("Add");
		    this.add_person = new JMenuItem("New person");
		    this.add_patient = new JMenuItem("New patient");
		    this.add_visit = new JMenuItem("New visit");
		    this.add_medicine = new JMenuItem("New medicine");
		    this.add_healthclub_visit = new JMenuItem("New healthclub visit");
		    this.add.add(this.add_person);
		    this.add.add(this.add_patient);
		    this.add.add(this.add_visit);
		    this.add.add(this.add_medicine);
		    this.add.add(this.add_healthclub_visit);
		    
		    //Menu item update
		    this.update = new JMenu("Update");
		    
		    this.menu.add(read); 
		    this.menu.add(add);
		    this.menu.add(update);
		    
			//Add actionlisteners
			read_persons_basic_info.addActionListener(this);
			read_patients_basic_info.addActionListener(this);
			read_patients_visit.addActionListener(this);
			read_patients_medicine.addActionListener(this);
			read_healthclub_visit.addActionListener(this);
			
			add_person.addActionListener(this);
			add_patient.addActionListener(this);
			add_visit.addActionListener(this);
			add_medicine.addActionListener(this);
			add_healthclub_visit.addActionListener(this);

			menu_panel.add(menu);
	  }
	  
	  /*
	   * Show a table with all the persons
	   */
	  /**
	  public void showPersonBasicInfo() {
		  content_panel.removeAll();
		  
		  List<String[]> rowDataList = new ArrayList<String[]>();
		  
		  try {
				Statement Statement = this.connection.createStatement();
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
					rowDataList.add(row);
				}
				res.close();
				Statement.close();
		  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		  }
		  
		  Object[][] rowData = new Object[rowDataList.size()][8];
		  
		  for(int i=0; i<rowDataList.size(); i++) {
			  rowData[i] = rowDataList.get(i);
		  }
		  
		  Object[] columnNames = {"Id", "Name", "Birth date", "Birth place", "Gender", "Nationality", "Address", "Phone number"};
		  
		  JTable table = new JTable(rowData, columnNames);
		  JScrollPane scrollPane = new JScrollPane(table);
		  this.content_panel.add(scrollPane);
		  repaint_panel();
	  }*/
	  
	  public void clickPersonBasicInfo() {
		  content_panel.removeAll();
		  
		  read_pe_info_panel = new JPanel();
		  read_pe_info_panel.setLayout(new BoxLayout(read_pe_info_panel, BoxLayout.Y_AXIS));
		  
		  JLabel l_uid = new JLabel("Insert id of person:");
		  t_uid = new JTextField(20);
		  s_read_pe_info = new JButton("Submit");
		  
		  read_pe_info_panel.add(l_uid);
		  read_pe_info_panel.add(t_uid);
		  read_pe_info_panel.add(s_read_pe_info);
		  
		  s_read_pe_info.addActionListener(this);
		  
		  content_panel.add(read_pe_info_panel);
		  repaint_panel();
	  }
	  
	  public void showPersonBasicInfoTable(int person_id) {
		  ArrayList<String> results = p.readBasicInfoDB(person_id);

		  Object[] array = results.toArray(new String[results.size()]);
		  
		  Object rowData[][] = new String[1][results.size()];
		  rowData[0] = array;
		  Object columnNames[] = {"Id", "Name", "Birth date", "Birth place", "Gender", "Nationality", "Address", "Phone number"};
		  
		  JTable table = new JTable(rowData, columnNames);
		  JScrollPane scrollPane = new JScrollPane(table);
		  
		  this.content_panel.add(scrollPane);
		  repaint_panel();
	  }
	  
	  public void clickPatientBasicInfo() {
		  content_panel.removeAll();
		  
		  read_pa_info_panel = new JPanel();
		  read_pa_info_panel.setLayout(new BoxLayout(read_pa_info_panel, BoxLayout.Y_AXIS));
		  
		  JLabel l_uid = new JLabel("Insert id of patient:");
		  t_uid = new JTextField(20);
		  s_read_pa_info = new JButton("Submit");
		  
		  read_pa_info_panel.add(l_uid);
		  read_pa_info_panel.add(t_uid);
		  read_pa_info_panel.add(s_read_pa_info);
		  
		  s_read_pa_info.addActionListener(this);
		  
		  content_panel.add(read_pa_info_panel);
		  repaint_panel();
	  }
	  
	  public void showPatientBasicInfo(int person_id) {
		  content_panel.removeAll();
		  
		  ArrayList<String> results = p.readBasicHealthInfoDB(person_id);

		  Object[] array = results.toArray(new String[results.size()]);
		  
		  Object rowData[][] = new String[1][results.size()];
		  rowData[0] = array;
		  Object[] columnNames = {"Id", "Blood type", "Weight (kg)", "Height (cm)", "Emergency contact", "Id family doctor"};
		  
		  JTable table = new JTable(rowData, columnNames);
		  JScrollPane scrollPane = new JScrollPane(table);
		  
		  this.content_panel.add(scrollPane);
		  repaint_panel();
	  }
	  
	  public void clickMedicalVisit() {
		  content_panel.removeAll();
		  
		  read_med_visit_panel = new JPanel();
		  read_med_visit_panel.setLayout(new BoxLayout(read_med_visit_panel, BoxLayout.Y_AXIS));
		  
		  JLabel l_uid = new JLabel("Insert id of patient:");
		  t_uid = new JTextField(20);
		  s_read_med_visit = new JButton("Submit");
		  
		  read_med_visit_panel.add(l_uid);
		  read_med_visit_panel.add(t_uid);
		  read_med_visit_panel.add(s_read_pa_info);
		  
		  s_read_med_visit.addActionListener(this);
		  
		  content_panel.add(read_med_visit_panel);
		  repaint_panel();
	  }
	  
	  public void showMedicalVisit(int person_id) {
		  content_panel.removeAll();
		  
		  /**
		  ArrayList<String> results = p.readBasicHealthInfoDB(person_id);

		  Object[] array = results.toArray(new String[results.size()]);
		  
		  Object rowData[][] = new String[1][results.size()];
		  rowData[0] = array;
		  Object[] columnNames = {"Id visit", "Id patient", "Start date", "End date", "Reason", "Results", "Id hospital doctors"};
		  
		  JTable table = new JTable(rowData, columnNames);
		  JScrollPane scrollPane = new JScrollPane(table);
		  
		  this.content_panel.add(scrollPane);
		  repaint_panel();
		  ///////*/
		  
		  List<String[]> rowDataList = new ArrayList<String[]>();
		  
		  try {
				Statement Statement = this.connection.createStatement();
				ResultSet res = Statement.executeQuery("SELECT * FROM sdmproject.patients_visits");
				while (res.next()) {
					String[] row = new String[7];
					row[0] = res.getString("id");
					row[1] = res.getString("id_patient");
					row[2] = res.getString("date_start");
					row[3] = res.getString("date_end");
					row[4] = res.getString("reason");
					row[5] = res.getString("results");
					row[6] = res.getString("id_hospital_doctors");
					rowDataList.add(row);
				}
				res.close();
				Statement.close();
		  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		  }
		  
		  Object[][] rowData = new Object[rowDataList.size()][7];
		  
		  for(int i=0; i<rowDataList.size(); i++) {
			  rowData[i] = rowDataList.get(i);
		  }
		  
		  Object[] columnNames = {"Id visit", "Id patient", "Start date", "End date", "Reason", "Results", "Id hospital doctors"};
		  
		  JTable table = new JTable(rowData, columnNames);
		  JScrollPane scrollPane = new JScrollPane(table);
		  this.content_panel.add(scrollPane);
		  repaint_panel();
	  }
	  
	  public void showPatientsMedicine() {
		  content_panel.removeAll();
		  
		  List<String[]> rowDataList = new ArrayList<String[]>();
		  
		  try {
				Statement Statement = this.connection.createStatement();
				ResultSet res = Statement.executeQuery("SELECT * FROM sdmproject.patients_medicines");
				while (res.next()) {
					String[] row = new String[5];
					row[0] = res.getString("medicine_name");
					row[1] = res.getString("dosage");
					row[2] = res.getString("date_start");
					row[3] = res.getString("date_end");
					row[4] = res.getString("id_visit");
					rowDataList.add(row);
				}
				res.close();
				Statement.close();
		  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		  }
		  
		  Object[][] rowData = new Object[rowDataList.size()][8];
		  
		  for(int i=0; i<rowDataList.size(); i++) {
			  rowData[i] = rowDataList.get(i);
		  }
		  
		  Object[] columnNames = {"Medicine name", "Dosage", "Start date", "End date", "Id visit"};
		  
		  JTable table = new JTable(rowData, columnNames);
		  JScrollPane scrollPane = new JScrollPane(table);
		  this.content_panel.add(scrollPane);
		  repaint_panel();
	  }
	  
	  public void showHealthClubVisits() {
		  content_panel.removeAll();
		  
		  
	  }
	  
	  public void showAddPatient() {
		  content_panel.removeAll();
		  
		  JPanel addpatient_panel = new JPanel();
		  addpatient_panel.setLayout(new BoxLayout(addpatient_panel, BoxLayout.Y_AXIS));
		  
		  JLabel l_id = new JLabel("Id patient:");
		  JLabel l_bloodtype = new JLabel("Blood type:");
		  JLabel l_weight = new JLabel("Weight:");
		  JLabel l_height = new JLabel("Height of patient:");
		  JLabel l_emerg_contact = new JLabel("Emergency contact:");
		  JLabel l_id_doc = new JLabel("Id of family doctor:");
		  
		  t_id = new JTextField("");
		  t_bloodtype = new JTextField("");
		  t_weight = new JTextField("");
		  t_height = new JTextField("");
		  t_emerg_contact = new JTextField("");
		  t_id_doc = new JTextField(""); //if possible, we could set this to the doctor's id by default
	  
		  s_addpatient = new JButton("Add patient to system");
		  
		  addpatient_panel.add(l_id);
		  addpatient_panel.add(t_id);
		  addpatient_panel.add(l_bloodtype);
		  addpatient_panel.add(t_bloodtype);
		  addpatient_panel.add(l_weight);
		  addpatient_panel.add(t_weight);
		  addpatient_panel.add(l_height);
		  addpatient_panel.add(t_height);
		  addpatient_panel.add(l_emerg_contact);
		  addpatient_panel.add(t_emerg_contact);
		  addpatient_panel.add(l_id_doc);
		  addpatient_panel.add(t_id_doc);
		  
		  addpatient_panel.add(s_addpatient);
		  
		  content_panel.add(addpatient_panel);
		  
		  s_addpatient.addActionListener(this);
		  
		  repaint_panel();
	  }
	  
	  public void addPatientDB() {
		  Integer id = Integer.parseInt(this.t_id.getText());
		  String blood_type = this.t_bloodtype.getText();
		  String weight = this.t_weight.getText();
		  String height = this.t_height.getText();
		  String emerg_contact = this.t_emerg_contact.getText();
		  String id_doc = this.t_id_doc.getText();

		 // p.insertBasicHealthInfoDB(id, blood_type, weight, height, emerg_contact, id_doc);
		  
		  t_id.setText("");
		  t_bloodtype.setText("");
		  t_weight.setText("");
		  t_height.setText("");
		  t_emerg_contact.setText("");
		  t_id_doc.setText("");
		  
		  repaint_panel();
	  }
	  
	  public void showAddVisit() {
		  content_panel.removeAll();
		  
		  JPanel addvisit_panel = new JPanel();
		  addvisit_panel.setLayout(new BoxLayout(addvisit_panel, BoxLayout.Y_AXIS));
		  
		  JLabel l_id_patient = new JLabel("Id patient:");
		  JLabel l_vis_date_start = new JLabel("Date start:");
		  JLabel l_vis_date_end = new JLabel("Date end:");
		  JLabel l_reason = new JLabel("Reason:");
		  JLabel l_results = new JLabel("Results:");
		  JLabel l_id_hospital_doctors = new JLabel("Id of hospital doctors:");
		  
		  t_id_patient = new JTextField("");
		  t_vis_date_start = new JTextField("");
		  t_vis_date_end = new JTextField("");
		  t_reason = new JTextField("");
		  t_results = new JTextField("");
		  t_id_hospital_doctors = new JTextField(""); //if possible, we could set this to the doctor's id by default
	  
		  s_addvisit = new JButton("Add patient visit to system");
		  
		  addvisit_panel.add(l_id_patient);
		  addvisit_panel.add(t_id_patient);
		  addvisit_panel.add(l_vis_date_start);
		  addvisit_panel.add(t_vis_date_start);
		  addvisit_panel.add(l_vis_date_end);
		  addvisit_panel.add(t_vis_date_end);
		  addvisit_panel.add(l_reason);
		  addvisit_panel.add(t_reason);
		  addvisit_panel.add(l_results);
		  addvisit_panel.add(t_results);
		  addvisit_panel.add(l_id_hospital_doctors);
		  addvisit_panel.add(t_id_hospital_doctors);
		  
		  addvisit_panel.add(s_addvisit);
		  
		  content_panel.add(addvisit_panel);
		  
		  s_addvisit.addActionListener(this);
		  
		  repaint_panel();
	  }
	  
	  public void addVisitDB() {
		  Integer id_patient = Integer.parseInt(this.t_id_patient.getText());
		  String vis_date_start = this.t_vis_date_start.getText();
		  String vis_date_end = this.t_vis_date_end.getText();
		  String reason = this.t_reason.getText();
		  String results = this.t_results.getText();
		  String id_hospital_doctors = this.t_id_hospital_doctors.getText();

		  //p.insertVisitDB(id_patient, vis_date_start, vis_date_end, reason, results, id_hospital_doctors);
		  
		  t_id_patient.setText("");
		  t_vis_date_start.setText("");
		  t_vis_date_end.setText("");
		  t_reason.setText("");
		  t_results.setText("");
		  t_id_hospital_doctors.setText("");
		  
		  repaint_panel();
	  }
	  
	  public void showAddMedicine() {
		  content_panel.removeAll();
		  
		  JPanel addmedicine_panel = new JPanel();
		  addmedicine_panel.setLayout(new BoxLayout(addmedicine_panel, BoxLayout.Y_AXIS));
		  
		  JLabel l_med_name = new JLabel("Name of medicine:");
		  JLabel l_dosage = new JLabel("Dosage:");
		  JLabel l_med_date_start = new JLabel("Date start:");
		  JLabel l_med_date_end = new JLabel("Date end:");
		  JLabel l_id_visit = new JLabel("Id of visitation:");
		  
		  t_med_name = new JTextField("");
		  t_dosage = new JTextField("");
		  t_med_date_start = new JTextField("");
		  t_med_date_end = new JTextField("");
		  t_id_visit = new JTextField("");
	  
		  s_addmedicine = new JButton("Add patient medicine to system");
		  
		  addmedicine_panel.add(l_med_name);
		  addmedicine_panel.add(t_med_name);
		  addmedicine_panel.add(l_dosage);
		  addmedicine_panel.add(t_dosage);
		  addmedicine_panel.add(l_med_date_start);
		  addmedicine_panel.add(t_med_date_start);
		  addmedicine_panel.add(l_med_date_end);
		  addmedicine_panel.add(t_med_date_end);
		  addmedicine_panel.add(l_id_visit);
		  addmedicine_panel.add(t_id_visit);
		  
		  addmedicine_panel.add(s_addmedicine);
		  
		  content_panel.add(addmedicine_panel);
		  
		  s_addmedicine.addActionListener(this);
		  
		  repaint_panel();
	  }
	  
	  public void addMedicineDB() {
		  String med_name = this.t_med_name.getText();
		  String dosage = this.t_dosage.getText();
		  String med_date_start = this.t_vis_date_start.getText();
		  String med_date_end = this.t_vis_date_end.getText();
		  Integer id_visit = Integer.parseInt(t_id_visit.getText());

		  //p.insertMedicineDB(med_name, dosage, med_date_start, med_date_end, id_visit);
		  
		  t_med_name.setText("");
		  t_dosage.setText("");
		  t_med_date_start.setText("");
		  t_med_date_end.setText("");
		  t_id_visit.setText("");

		  repaint_panel();
	  }
	  
	  public void repaint_panel() {
	      this.content_panel.revalidate();
	      this.repaint();
	  }
	  
	  public void actionPerformed(ActionEvent event)
	  {
	    Object source = event.getSource();
	    
	    if(source == this.read_persons_basic_info) {
	    	clickPersonBasicInfo();
	    } else if(source == this.s_read_pe_info) {
	    	showPersonBasicInfoTable(Integer.parseInt(this.t_uid.getText()));
	    } else if(source == this.read_patients_basic_info) {
	    	clickPatientBasicInfo();
	    } else if(source == this.s_read_pa_info) {
	    	showPatientBasicInfo(Integer.parseInt(this.t_uid.getText()));
	    } else if(source == this.read_patients_visit) {
	    	clickMedicalVisit();
	    } else if(source == this.read_patients_medicine) {
	    	showPatientsMedicine();
	    } else if(source == this.add_patient) {
	    	showAddPatient();
	    } else if(source == this.s_addpatient) {
	    	addPatientDB();
	    } else if(source == this.add_visit) {
	    	showAddVisit();
	    } else if(source == this.add_medicine) {
	    	showAddMedicine();
	    }
	  }
}

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
import sdm.Policies;

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
	  private JPanel read_med_panel;
	  private JPanel read_hcv_panel;
	  private JPanel add_medicine_panel;
	  private JPanel update_person_panel;
	  private JPanel update_writing_policy_panel;
	  private JPanel update_reading_policy_panel;
	  
	  //Menu components
	  private JMenuBar menu;
	  private JMenu read, add, update;
	  private JMenuItem read_persons_basic_info, read_patients_basic_info, read_patients_visit, read_patients_medicine, read_healthclub_visit;
	  private JMenuItem add_patient, add_visit, add_medicine, add_healthclub_visit; 
	  private JMenuItem update_person, update_patient, update_writing_policy, update_reading_policy;
	  
	  //Read person/patient/medical/health club visit info or add medicine
	  private JTextField t_uid;
	  private JButton s_read_pe_info;
	  private JButton s_read_pa_info;
	  private JButton s_read_med_visit;
	  private JButton s_read_med;
	  private JButton s_read_hcv;
	  private JButton s_add_medicine;
	  
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
	  private JTextField[] t_add_medicine;
	  private JButton s_addmedicine;
	  
	  //Add health club visit components;
	  private JTextField t_date;
	  private JTextField t_duration;
	  private JTextField t_reasons;
	  private JTextField t_comments;
	  private JButton s_addhcvisit;
	  
	  //Update person components
	  private JTextField[] t_update_person;
	  private JButton s_update_person;
	  private JButton s_fill_person;
	  
	  //Update patient components
	  private JTextField[] t_update_patient;
	  private JButton s_update_patient;
	  private JButton s_fill_patient;
	  
	  //Update policy components;
	  private JComboBox<String> c_policies;
	  private final String[] table_names = {"BasicInfo", "BasicHealthInfo", "MedicalVisit", "Medicine", "HealthClubVisit"};
	  private JTextField t_policy;
	  private JButton s_update_writing_policy;
	  private JButton s_update_reading_policy;
	  
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
		    this.read_healthclub_visit = new JMenuItem("Health Club visit");
		    this.read.add(read_persons_basic_info);
		    this.read.addSeparator();
		    this.read.add(read_patients_basic_info);
		    this.read.add(read_patients_visit);
		    this.read.add(read_patients_medicine);
		    this.read.addSeparator();
		    this.read.add(read_healthclub_visit);
		    
		    //Menu item Add
		    this.add = new JMenu("Add");
		    this.add_patient = new JMenuItem("New patient");
		    this.add_visit = new JMenuItem("New visit");
		    this.add_medicine = new JMenuItem("New medicine");
		    this.add_healthclub_visit = new JMenuItem("New Health Club visit");
		    this.add.add(this.add_patient);
		    this.add.add(this.add_visit);
		    this.add.add(this.add_medicine);
		    this.add.add(this.add_healthclub_visit);
		    
		    //Menu item update
		    this.update = new JMenu("Update");
		    this.update_person = new JMenuItem("Person");
		    this.update_patient = new JMenuItem("Patient");
		    this.update_writing_policy = new JMenuItem("Writing policy");
		    this.update_reading_policy = new JMenuItem("Reading policy");
		    this.update.add(this.update_person);
		    this.update.add(this.update_patient);
		    this.update.add(this.update_writing_policy);
		    this.update.add(this.update_reading_policy);
		    
		    this.menu.add(read); 
		    this.menu.add(add);
		    this.menu.add(update);
		    
			//Add actionlisteners
		    //Read
			read_persons_basic_info.addActionListener(this);
			read_patients_basic_info.addActionListener(this);
			read_patients_visit.addActionListener(this);
			read_patients_medicine.addActionListener(this);
			read_healthclub_visit.addActionListener(this);
			
			//Add
			add_patient.addActionListener(this);
			add_visit.addActionListener(this);
			add_medicine.addActionListener(this);
			add_healthclub_visit.addActionListener(this);
			
			//Update
			update_person.addActionListener(this);
			update_patient.addActionListener(this);
			update_writing_policy.addActionListener(this);
			update_reading_policy.addActionListener(this);

			menu_panel.add(menu);
	  }
	  
	  /**
	   * Displays panel with textfield for inserting id to read that specific persons basic info
	   */
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
	  
	  /**
	   * Displays the table with the persons specified by the id inserted
	   * @param person_id
	   */
	  public void showPersonBasicInfoTable(int person_id) {
		  content_panel.removeAll();
		  
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
	  
	  /**
	   * Displays panel with textfield for inserting id to read that specific persons basic health info 
	   */
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
	  
	  /**
	   * 
	   * @param person_id
	   */
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
	  
	  /**
	   * Displays panel with textfield for inserting patient id to read their medical data
	   */
	  public void clickMedicalVisit() {
		  content_panel.removeAll();
		  
		  read_med_visit_panel = new JPanel();
		  read_med_visit_panel.setLayout(new BoxLayout(read_med_visit_panel, BoxLayout.Y_AXIS));
		  
		  JLabel l_uid = new JLabel("Insert id of patient:");
		  t_uid = new JTextField(20);
		  s_read_med_visit = new JButton("Submit");
		  
		  read_med_visit_panel.add(l_uid);
		  read_med_visit_panel.add(t_uid);
		  read_med_visit_panel.add(s_read_med_visit);
		  
		  s_read_med_visit.addActionListener(this);
		  
		  content_panel.add(read_med_visit_panel);
		  repaint_panel();
	  }
	  
	  public void showMedicalVisit(int person_id) {
		  content_panel.removeAll();
		  
		  ArrayList<ArrayList<String>> results = p.readDisplayMedicalVisitDB(person_id);
		  
		  Object[] columnNames = {"Id visit", "Id patient", "Start date", "End date", "Reason", "Results", "Id hospital doctor", "ID Doctor", "Name hospital"};
		  Object rowData[][] = new Object[results.size()][columnNames.length];

		  for(int i=0; i<results.size(); i++) {
			  Object[] row = new Object[columnNames.length];
			  
			  for(int j=0; j<results.get(i).size(); j++) {
				  row[j] = results.get(i).get(j);
			  }
			  
			  rowData[i] = row;
		  }
		  
		  
		  JTable table = new JTable(rowData, columnNames);
		  JScrollPane scrollPane = new JScrollPane(table);
		  
		  this.content_panel.add(scrollPane);
		  repaint_panel();
	  }
	  
	  public void clickPatientMedicines() {
		  content_panel.removeAll();
		  
		  read_med_panel = new JPanel();
		  read_med_panel.setLayout(new BoxLayout(read_med_panel, BoxLayout.Y_AXIS));
		  
		  JLabel l_uid = new JLabel("Insert id of patient:");
		  t_uid = new JTextField(20);
		  s_read_med = new JButton("Submit");
		  
		  read_med_panel.add(l_uid);
		  read_med_panel.add(t_uid);
		  read_med_panel.add(s_read_med);
		  
		  s_read_med.addActionListener(this);
		  
		  content_panel.add(read_med_panel);
		  repaint_panel();
	  }
	  
	  //TODO extra column
	  public void showPatientMedicines(int person_id) {
		  content_panel.removeAll();
		  
		  ArrayList<ArrayList<String>> results = p.readDisplayMedicineDB(person_id);
		  
		  Object[] columnNames = {"Id", "Medicine name", "Dosage", "Start date", "End date", "Id visit", "Id patient", "Id doctor", "Hospital name"};
		  Object rowData[][] = new Object[results.size()][columnNames.length];

		  for(int i=0; i<results.size(); i++) {
			  Object[] row = new Object[columnNames.length];
			  
			  for(int j=0; j<results.get(i).size(); j++) {
				  row[j] = results.get(i).get(j);
			  }
			  
			  rowData[i] = row;
		  }
		  
		  
		  JTable table = new JTable(rowData, columnNames);
		  JScrollPane scrollPane = new JScrollPane(table);
		  
		  this.content_panel.add(scrollPane);
		  repaint_panel();
	  }
	  
	  public void clickHealthClubVisits() {
		  content_panel.removeAll();
		  
		  read_hcv_panel = new JPanel();
		  read_hcv_panel.setLayout(new BoxLayout(read_hcv_panel, BoxLayout.Y_AXIS));
		  
		  JLabel l_uid = new JLabel("Insert id of patient:");
		  t_uid = new JTextField(20);
		  s_read_hcv = new JButton("Submit");
		  
		  read_hcv_panel.add(l_uid);
		  read_hcv_panel.add(t_uid);
		  read_hcv_panel.add(s_read_hcv);
		  
		  s_read_hcv.addActionListener(this);
		  
		  content_panel.add(read_hcv_panel);
		  repaint_panel();
	  }
	  
	  public void showHealthClubVisits(int person_id) {
		  content_panel.removeAll();
		  
		  ArrayList<ArrayList<String>> results = p.readDisplayHealthClubVisitDB(person_id);
		  
		  Object[] columnNames = {"Id", "Id patient healthclub", "Id patient", "Health club", "Date", "Duration", "Reasons", "Results", "Comments"};
		  
		  Object rowData[][] = new Object[results.size()][columnNames.length];

		  for(int i=0; i<results.size(); i++) {
			  Object[] row = new Object[columnNames.length];
			  
			  for(int j=0; j<results.get(i).size(); j++) {
				  row[j] = results.get(i).get(j);
				  System.out.println(row[j]);
			  }
			  
			  rowData[i] = row;
		  }
		  
		  
		  JTable table = new JTable(rowData, columnNames);
		  JScrollPane scrollPane = new JScrollPane(table);
		  
		  this.content_panel.add(scrollPane);
		  repaint_panel();
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
	  
	  public void addPatientDB() throws SQLException {
		  Integer id = Integer.parseInt(this.t_id.getText());
		  String blood_type = this.t_bloodtype.getText();
		  String weight = this.t_weight.getText();
		  String height = this.t_height.getText();
		  String emerg_contact = this.t_emerg_contact.getText();
		  Integer id_doc = Integer.parseInt(this.t_id_doc.getText());

		  p.insertBasicHealthInfoDB(id, blood_type, weight, height, emerg_contact, id_doc);	  
		  
		  t_id.setText("");
		  t_bloodtype.setText("");
		  t_weight.setText("");
		  t_height.setText("");
		  t_emerg_contact.setText("");
		  t_id_doc.setText("");
		  
		  repaint_panel();
	  }
	  
	  //TODO extra column
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
		  Integer id_hospital_doctors = Integer.parseInt(this.t_id_hospital_doctors.getText());

		  p.addMedicalVisitDB(id_patient, vis_date_start, vis_date_end, reason, results, id_hospital_doctors);
		  
		  t_id_patient.setText("");
		  t_vis_date_start.setText("");
		  t_vis_date_end.setText("");
		  t_reason.setText("");
		  t_results.setText("");
		  t_id_hospital_doctors.setText("");
		  
		  repaint_panel();
	  }
	  
	  public void clickAddMedicine() {
		  content_panel.removeAll();
		  
		  JPanel add_medicine_panel = new JPanel();
		  add_medicine_panel.setLayout(new BoxLayout(add_medicine_panel, BoxLayout.Y_AXIS));
		  
		  JLabel[] l_add_medicine = new JLabel[6];
		  t_add_medicine = new JTextField[6];
		 
		  l_add_medicine[0] = new JLabel("Id of patient:");
		  l_add_medicine[1] = new JLabel("Name of medicine:");
		  l_add_medicine[2] = new JLabel("Dosage:");
		  l_add_medicine[3] = new JLabel("Date start:");
		  l_add_medicine[4] = new JLabel("Date end:");
		  l_add_medicine[5] = new JLabel("Id of visitation:");
		  
		  for(int i=0; i<l_add_medicine.length; i++) {
			  t_add_medicine[i] = new JTextField(20);
		  }
	  
		  s_add_medicine = new JButton("Add new medicine");
		  
		  for(int i=0; i<l_add_medicine.length; i++) {
			  add_medicine_panel.add(l_add_medicine[i]);
			  add_medicine_panel.add(t_add_medicine[i]);
		  }
		  
		  add_medicine_panel.add(s_add_medicine);
		  
		  content_panel.add(add_medicine_panel);
		 
		  s_add_medicine.addActionListener(this);
		  
		  repaint_panel();
	  }
	  
	  public void addMedicine() {
		  Integer id = Integer.parseInt(this.t_add_medicine[0].getText());
		  String med_name = this.t_add_medicine[1].getText();
		  String dosage = this.t_add_medicine[2].getText();
		  String med_date_start = this.t_add_medicine[3].getText();
		  String med_date_end = this.t_add_medicine[4].getText();
		  Integer id_visit = Integer.parseInt(t_add_medicine[5].getText());

		  p.addMedicineDB(id, med_name, dosage, med_date_start, med_date_end, id_visit);
		  
		  for(int i=0; i<t_add_medicine.length; i++) {
			  t_add_medicine[i].setText("");
		  }

		  repaint_panel();
	  }
	  
	  public void clickAddHealthClubVisits() {
		  content_panel.removeAll();
		  
		  JPanel addhcvisit_panel = new JPanel();
		  addhcvisit_panel.setLayout(new BoxLayout(addhcvisit_panel, BoxLayout.Y_AXIS));
		  
		  JLabel l_uid = new JLabel("User id:");
		  JLabel l_id_patient = new JLabel("Patient id:");
		  JLabel l_date = new JLabel("Date:");
		  JLabel l_duration = new JLabel("Duration:");
		  JLabel l_reasons = new JLabel("Reasons:");
		  JLabel l_results = new JLabel("Results:");
		  JLabel l_comments = new JLabel("Comments:");
		  
		  t_uid = new JTextField(20);
		  t_id_patient = new JTextField("");
		  t_date = new JTextField("");
		  t_duration = new JTextField("");
		  t_reasons = new JTextField("");
		  t_results = new JTextField("");
		  t_comments = new JTextField("");
	  
		  s_addhcvisit = new JButton("Add health club visit to system");
		  
		  addhcvisit_panel.add(l_uid);
		  addhcvisit_panel.add(t_uid);
		  addhcvisit_panel.add(l_id_patient);
		  addhcvisit_panel.add(t_id_patient);
		  addhcvisit_panel.add(l_date);
		  addhcvisit_panel.add(t_date);
		  addhcvisit_panel.add(l_duration);
		  addhcvisit_panel.add(t_duration);
		  addhcvisit_panel.add(l_reasons);
		  addhcvisit_panel.add(t_reasons);
		  addhcvisit_panel.add(l_results);
		  addhcvisit_panel.add(t_results);
		  addhcvisit_panel.add(l_comments);
		  addhcvisit_panel.add(t_comments);
		  
		  addhcvisit_panel.add(s_addhcvisit);
		  
		  content_panel.add(addhcvisit_panel);
		  
		  s_addhcvisit.addActionListener(this);
		  
		  repaint_panel();
	  }
	  
	  public void addHealthClubVisits() {
		  Integer uid = Integer.parseInt(this.t_uid.getText());
		  Integer id_patient = Integer.parseInt(this.t_id_patient.getText());
		  String date = this.t_date.getText();
		  String duration = this.t_duration.getText();
		  String reasons = this.t_reasons.getText();
		  String results = this.t_results.getText();
		  String comments = t_comments.getText();

		  p.addHealthClubVisitsDB(uid, id_patient, date, duration, reasons, results, comments);
		  
		  t_uid.setText("");
		  t_id_patient.setText("");
		  t_date.setText("");
		  t_duration.setText("");
		  t_reasons.setText("");
		  t_results.setText("");
		  t_comments.setText("");

		  repaint_panel();
	  }
	  
	  public void clickUpdatePerson() {
		  content_panel.removeAll();
		  
		  JPanel update_person_panel = new JPanel();
		  update_person_panel.setLayout(new BoxLayout(update_person_panel, BoxLayout.Y_AXIS));
		  
		  JLabel[] l_update_person = new JLabel[8];
		  t_update_person = new JTextField[8];
		  
		  l_update_person[0] = new JLabel("Id of person");
		  l_update_person[1] = new JLabel("Name:");
		  l_update_person[2] = new JLabel("Birth date");
		  l_update_person[3] = new JLabel("Birth place");
		  l_update_person[4] = new JLabel("Gender");
		  l_update_person[5] = new JLabel("Nationality");
		  l_update_person[6] = new JLabel("Address");
		  l_update_person[7] = new JLabel("Phone number");
		  
		  for(int i=0; i<l_update_person.length; i++) {
			  t_update_person[i] = new JTextField(20);
		  }
	  
		  s_fill_person = new JButton("Fill in other fields automatically");
		  
		  s_update_person = new JButton("Update person");
		  
		  update_person_panel.add(l_update_person[0]);
		  update_person_panel.add(t_update_person[0]);
		  update_person_panel.add(s_fill_person);
		  
		  for(int i=1; i<l_update_person.length; i++) {
			  update_person_panel.add(l_update_person[i]);
			  update_person_panel.add(t_update_person[i]);
		  }
		  
		  update_person_panel.add(s_update_person);
		  
		  content_panel.add(update_person_panel);
		  
		  s_fill_person.addActionListener(this);
		  s_update_person.addActionListener(this);
		  
		  repaint_panel();
	  }
	  
	  public void fillFieldsUpdatePerson() {
		  int person_id = Integer.parseInt(t_update_person[0].getText());
		  
		  ArrayList<String> update_fields = p.readBasicInfoDB(person_id);
		  
		  for(int i=1; i<update_fields.size(); i++) {
			  t_update_person[i].setText(update_fields.get(i));
		  }
		  
		  repaint_panel();
	  }
	  
	  public void updatePerson() throws SQLException {
		  Integer uid = Integer.parseInt(t_update_person[0].getText());
		  String name = this.t_update_person[1].getText();
		  String birth_date = t_update_person[2].getText();
		  String birth_place = t_update_person[3].getText();
		  String gender = t_update_person[4].getText();
		  String nationality = t_update_person[5].getText();
		  String address = t_update_person[6].getText();
		  String phone_no = t_update_person[7].getText();

		  p.updateBasicInfoDB(uid, name, birth_date, birth_place, gender, nationality, address, phone_no);
		  
		  for(int i=0; i<t_update_person.length; i++) {
			  t_update_person[i].setText("");
		  }

		  repaint_panel();
	  }
	  
	  public void clickUpdatePatient() {
		  content_panel.removeAll();
		  
		  JPanel update_patient_panel = new JPanel();
		  update_patient_panel.setLayout(new BoxLayout(update_patient_panel, BoxLayout.Y_AXIS));
		  
		  JLabel[] l_update_patient = new JLabel[6];
		  t_update_patient = new JTextField[6];
		  
		  l_update_patient[0] = new JLabel("Id of patient");
		  l_update_patient[1] = new JLabel("Blood type:");
		  l_update_patient[2] = new JLabel("Weight:");
		  l_update_patient[3] = new JLabel("Height:");
		  l_update_patient[4] = new JLabel("Emergency contact:");
		  l_update_patient[5] = new JLabel("Id of family doctor:");
		  
		  for(int i=0; i<l_update_patient.length; i++) {
			  t_update_patient[i] = new JTextField(20);
		  }
	  
		  s_fill_patient = new JButton("Fill in other fields automatically");
		  
		  s_update_patient = new JButton("Update patient");
		  
		  update_patient_panel.add(l_update_patient[0]);
		  update_patient_panel.add(t_update_patient[0]);
		  update_patient_panel.add(s_fill_patient);
		  
		  for(int i=1; i<l_update_patient.length; i++) {
			  update_patient_panel.add(l_update_patient[i]);
			  update_patient_panel.add(t_update_patient[i]);
		  }
		  
		  update_patient_panel.add(s_update_patient);
		  
		  content_panel.add(update_patient_panel);
		  
		  s_fill_patient.addActionListener(this);
		  s_update_patient.addActionListener(this);
		  
		  repaint_panel();
	  }
	  
	  public void fillFieldsUpdatePatient() {
		  int person_id = Integer.parseInt(t_update_patient[0].getText());
		  
		  ArrayList<String> update_fields = p.readBasicHealthInfoDB(person_id);
		  
		  for(int i=1; i<update_fields.size(); i++) {
			  t_update_patient[i].setText(update_fields.get(i));
		  }
		  
		  repaint_panel();
	  }
	  
	  public void updatePatient() throws SQLException {
		  Integer id_patient = Integer.parseInt(t_update_patient[0].getText());
		  String blood_type = this.t_update_patient[1].getText();
		  String weight = t_update_patient[2].getText();
		  String height = t_update_patient[3].getText();
		  String emergency_contact = t_update_patient[4].getText();
		  Integer id_family_doctor = Integer.parseInt(t_update_patient[5].getText());

		  p.updateBasicHealthInfoDB(id_patient, blood_type, weight, height, emergency_contact, id_family_doctor);
		  
		  for(int i=0; i<t_update_patient.length; i++) {
			  t_update_patient[i].setText("");
		  }

		  repaint_panel();
	  }
	  
	  public void clickUpdateWritingPolicy() {
		  content_panel.removeAll();
		  
		  update_writing_policy_panel = new JPanel();
		  update_writing_policy_panel.setLayout(new BoxLayout(update_writing_policy_panel, BoxLayout.Y_AXIS));
		  
		  JLabel title = new JLabel("Update policy");
		  c_policies = new JComboBox(table_names);
		  
		  JLabel l_writing_policy = new JLabel("Change writing policy of the selected table below:");
		  t_policy = new JTextField(200);
		  s_update_writing_policy = new JButton("Update policy");
		  
		  update_writing_policy_panel.add(title);
		  update_writing_policy_panel.add(c_policies);
		  
		  update_writing_policy_panel.add(l_writing_policy);
		  update_writing_policy_panel.add(t_policy);
		  update_writing_policy_panel.add(s_update_writing_policy);
		  
		  s_update_writing_policy.addActionListener(this);

		  content_panel.add(update_writing_policy_panel);
		  
		  c_policies.addActionListener(this);
		  
		  repaint_panel();
	  }
	  
	  public void fillFieldUpdateWritingPolicy(String selection) {
		  Policies policies = p.policies;
		 
		  String writingPolicy = "";
		  
		  if(selection.equals(table_names[0])) {
			  writingPolicy = policies.getBIWritingPolicy();
		  } else if(selection.equals(table_names[1])) {
			  writingPolicy = policies.getBHIWritingPolicy();
		  } else if(selection.equals(table_names[2])) {
			  writingPolicy = policies.getMVWritingPolicy();
		  } else if(selection.equals(table_names[3])) {
			  writingPolicy = policies.getMWritingPolicy();
		  } else if(selection.equals(table_names[4])) {
			  writingPolicy = policies.getHCVWritingPolicy();
		  }
		  
		  t_policy.setText(writingPolicy);
		  
		  repaint_panel();
	  }
	  
	  public void updateWritingPolicy(String table_name) {
		  String writing_policy = this.t_policy.getText();
		  
		 this.p.updateWritingPolicy(table_name, writing_policy);
		  
		  this.t_policy.setText("");

		  repaint_panel();
	  }
	  
	  public void clickUpdateReadingPolicy() {
		  content_panel.removeAll();
		  
		  update_reading_policy_panel = new JPanel();
		  update_reading_policy_panel.setLayout(new BoxLayout(update_reading_policy_panel, BoxLayout.Y_AXIS));
		  
		  JLabel title = new JLabel("Update policy");
		  c_policies = new JComboBox(table_names);
		  
		  JLabel l_reading_policy = new JLabel("Change reading policy of the selected table below:");
		  t_policy = new JTextField(200);
		  s_update_reading_policy = new JButton("Update policy");
		  
		  update_reading_policy_panel.add(title);
		  update_reading_policy_panel.add(c_policies);
		  
		  update_reading_policy_panel.add(l_reading_policy);
		  update_reading_policy_panel.add(t_policy);
		  update_reading_policy_panel.add(s_update_reading_policy);
		  
		  s_update_reading_policy.addActionListener(this);

		  content_panel.add(update_reading_policy_panel);
		  
		  c_policies.addActionListener(this);
		  
		  repaint_panel();
	  }
	  
	  public void fillFieldUpdateReadingPolicy(String selection) {
		  Policies policies = p.policies;
		 
		  String readingPolicy = "";
		  
		  if(selection.equals(table_names[0])) {
			  readingPolicy = policies.getBIReadingPolicy();
		  } else if(selection.equals(table_names[1])) {
			  readingPolicy = policies.getBHIReadingPolicy();
		  } else if(selection.equals(table_names[2])) {
			  readingPolicy = policies.getMVReadingPolicy();
		  } else if(selection.equals(table_names[3])) {
			  readingPolicy = policies.getMReadingPolicy();
		  } else if(selection.equals(table_names[4])) {
			  readingPolicy = policies.getHCVReadingPolicy();
		  }
		  
		  t_policy.setText(readingPolicy);
		  
		  repaint_panel();
	  }
	  
	  public void updateReadingPolicy(String table_name) {
		  String reading_policy = this.t_policy.getText();
		  
		 this.p.updateReadingPolicy(table_name, reading_policy);
		  
		  this.t_policy.setText("");

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
	    } else if(source == this.s_read_med_visit) {
	    	showMedicalVisit(Integer.parseInt(this.t_uid.getText()));
	    } else if(source == this.read_patients_medicine) {
	    	clickPatientMedicines();
	    } else if(source == this.s_read_med) {
	    	System.out.println(this.t_uid.getText());
	    	showPatientMedicines(Integer.parseInt(this.t_uid.getText()));
	    } else if(source == this.read_healthclub_visit) {
	    	clickHealthClubVisits();
	    } else if(source == this.s_read_hcv) {
	    	showHealthClubVisits(Integer.parseInt(this.t_uid.getText()));
	    } else if(source == this.add_patient) {
	    	showAddPatient();
	    } else if(source == this.s_addpatient) {
	    	try {
				addPatientDB();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    } else if(source == this.add_visit) {
	    	showAddVisit();
	    } else if(source == this.s_addvisit) {
	    	addVisitDB();
	    } else if(source == this.add_medicine) {
	    	clickAddMedicine();
	    } else if(source == s_add_medicine) {
	    	addMedicine();
	    } else if(source == this.add_healthclub_visit) {
	    	clickAddHealthClubVisits();
	    } else if(source == this.s_addhcvisit) {
	    	addHealthClubVisits();
	    } else if(source == this.update_person) {
	    	clickUpdatePerson();
	    } else if(source == this.s_fill_person) {
	    	fillFieldsUpdatePerson();
	    } else if(source == this.s_update_person) {
	    	try {
				updatePerson();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    } else if(source == this.update_patient) {
	    	clickUpdatePatient();
	    } else if(source == s_fill_patient) {
	    	fillFieldsUpdatePatient();
	    } else if(source == s_update_patient) {
	    	try {
				updatePatient();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    } else if(source == this.update_writing_policy) {
	    	clickUpdateWritingPolicy();
	    } else if(source == this.c_policies) {
	    	fillFieldUpdateWritingPolicy((String) c_policies.getSelectedItem());
	    } else if(source == this.s_update_writing_policy) {
	    	updateWritingPolicy((String) c_policies.getSelectedItem());
	    } else if(source == this.update_reading_policy) {
	    	clickUpdateReadingPolicy();
	    } else if(source == this.c_policies) {
	    	fillFieldUpdateReadingPolicy((String) c_policies.getSelectedItem());
	    } else if(source == this.s_update_reading_policy) {
	    	updateReadingPolicy((String) c_policies.getSelectedItem());
	    }
	  }
}

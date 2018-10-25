package sdm;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.crypto.Cipher;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.edu.pku.ss.crypto.abe.CPABEImpl;
import cn.edu.pku.ss.crypto.abe.CPABEImplWithoutSerialize;
import cn.edu.pku.ss.crypto.abe.Ciphertext;
import cn.edu.pku.ss.crypto.abe.Parser;
import cn.edu.pku.ss.crypto.abe.Policy;
import cn.edu.pku.ss.crypto.abe.PublicKey;
import cn.edu.pku.ss.crypto.abe.SecretKey;
import cn.edu.pku.ss.crypto.abe.serialize.SerializeUtils;
import cn.edu.pku.ss.crypto.aes.AES;
import databaseAccess.DBConnection;
import it.unisa.dia.gas.jpbc.Element;

public class Person {
	private int id;
	
	private PublicKey PK;
	private SecretKey SK;
	private String[] attrs;
	
	public Person(int id, String[] attrs) {
		this.id = id;
		this.attrs = attrs;
		
	}
	
	public String[] getAttrs(){
		return attrs;
	}
	
	public PublicKey getPK() {
		return PK;
	}

	public void setPK(String PKJSONString) {
		JSONObject json = JSON.parseObject(PKJSONString);
		byte[] b = json.getBytes("PK");
		this.PK = SerializeUtils.constructFromByteArray(PublicKey.class, b);
	}

	public SecretKey getSK() {
		return SK;
	}

	public void setSK(String SKJSONString) {
		JSONObject json = JSON.parseObject(SKJSONString);
		byte[] b = json.getBytes("SK");
		this.SK = SerializeUtils.constructFromByteArray(SecretKey.class, b);
	}
	
	public void changeHealthRecordDB(String emergency_contact, int id_family_doctor) {
		//to be implemented
		//sql query
	}
	
	/**
	 * Insert or change the name of the emergency contact in the PHR system
	 * @param emergency_contact
	 */
	public void changeEmergencyContactDB(String emergency_contact) {
		
		DBConnection.update("UPDATE sdmproject.patients_basic_health_info set emergency_contact= '"+emergency_contact+"' WHERE id_patient= "+this.id+" limit 1");
		
	}
	
	/**
	 * Insert or change the id of the family doctor in the PHR system
	 * @param id_family_doctor
	 */
	public void changeIdFamilyDoctorDB(int id_family_doctor) {
		
		DBConnection.update("UPDATE sdmproject.patients_basic_health_info set id_family_doctor= '"+
		id_family_doctor+
		"' WHERE id_patient= "+this.id+" limit 1");
		
	}
	
	/**
	 * Insert entry in insertVisitationDB table
	 * @param id_patient
	 * @param date_start
	 * @param date_end
	 * @param reason
	 * @param results
	 * @param id_hospitals_doctors
	 */
	public void insertVisitationDB(int id_patient, Date date_start, Date date_end, String reason, String results, int id_hospitals_doctors) {
		//to be implemented
		//sql query
	}
	
	/**
	 * Insert entry in Persons_medicine table
	 * @param medicine_name
	 * @param dosage
	 * @param date_start
	 * @param date_end
	 * @param id_visit
	 */
	public void insertMedicineDB(String medicine_name, String dosage, Date date_start, Date date_end, int id_visit) {
		//to be implemented
		//sql query
	}

	//file in should be changed to the plaintext parameters for the input query
	//String outputFileName should be the query to the database with the CP-ABE encrypted parameters
	public void enc(File in, String policy, String outputFileName){
		Parser parser = new Parser();
		Policy p = parser.parse(policy);
		CPABEImplWithoutSerialize.enc(in, p, this.PK, outputFileName);
	}
	
	//Change decryption function with mediator
	public void dec(File in){
		String ciphertextFileName = null; 
		DataInputStream dis = null;
		try {
			ciphertextFileName = in.getCanonicalPath();
			dis = new DataInputStream(new FileInputStream(in));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Ciphertext ciphertext = SerializeUtils._unserialize(Ciphertext.class, dis);
		
		String output = null;
		if(ciphertextFileName.endsWith(".cpabe")){
			int end = ciphertextFileName.indexOf(".cpabe");
			output = ciphertextFileName.substring(0, end);
		}
		else{
			output = ciphertextFileName + ".out";
		}
		File outputFile = CPABEImpl.createNewFile(output);
		OutputStream os = null;
		try {
			os =  new FileOutputStream(outputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Element m = CPABEImpl.dec(ciphertext, SK, PK);
		AES.crypto(Cipher.DECRYPT_MODE, dis, os, m);
	}
	
	public void serializePK(File f){
		SerializeUtils.serialize(this.PK, f);
	}
	
	public void serializeSK(File f){
		SerializeUtils.serialize(this.SK, f);
	}

}

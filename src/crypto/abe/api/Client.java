package crypto.abe.api;

import it.unisa.dia.gas.jpbc.Element;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;

import crypto.abe.CPABEImpl;
import crypto.abe.CPABEImplWithoutSerialize;
import crypto.abe.Ciphertext;
import crypto.abe.Parser;
import crypto.abe.Policy;
import crypto.abe.PublicKey;
import crypto.abe.SecretKey;
import crypto.abe.serialize.SerializeUtils;
import crypto.aes.AES;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Client {
	private PublicKey PK;
	private SecretKey SK;
	private String[] attrs;

	public Client() {
	}

	public Client(String[] attrs) {
		this.attrs = attrs;
	}

	public String[] getAttrs() {
		return attrs;
	}

	public void setAttrs(String[] attrs) {
		this.attrs = attrs;
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

	public byte[] enc(String in, String policy, String outputFileName) {
		Parser parser = new Parser();
		Policy p = parser.parse(policy);
		return CPABEImplWithoutSerialize.enc(in, p, this.PK, outputFileName);
	}

	public void dec(byte[] b) {
		DataInputStream dis = null;
		dis = new DataInputStream(new ByteArrayInputStream(b));
		Ciphertext ciphertext = SerializeUtils._unserialize(Ciphertext.class, dis);
		ByteArrayOutputStream os = null;
		try {
			os = new ByteArrayOutputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Element m = CPABEImpl.dec(ciphertext, SK, PK);
		AES.crypto(Cipher.DECRYPT_MODE, dis, os, m);
		System.out.println(os.toString());
	}

	public void serializePK(File f) {
		SerializeUtils.serialize(this.PK, f);
	}

	public void serializeSK(File f) {
		SerializeUtils.serialize(this.SK, f);
	}
}
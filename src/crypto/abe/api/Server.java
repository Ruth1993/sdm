package crypto.abe.api;

import crypto.abe.CPABEImplWithoutSerialize;
import crypto.abe.CPABEImplWithoutSerialize.KeyPair;


public class Server {
	private KeyPair pair;
	
	public Server(){
		this.pair = CPABEImplWithoutSerialize.setup();
	}
	
	public String getPublicKeyInString(){
		return pair.getPKJSONString();
	}
	
	public void setPublicKey(String PKJSONString){
		pair.setPK(PKJSONString);
	}
	
	public String getMasterKeyInString(){
		return pair.getMKJSONString();
	}
	
	public void setMasterKey(String MKJSONString){
		pair.setMK(MKJSONString);
	}
	
	public String generateSecretKey(String[] attrs){
		return CPABEImplWithoutSerialize.keygen(attrs, pair.getPK(), pair.getMK());
	}
}

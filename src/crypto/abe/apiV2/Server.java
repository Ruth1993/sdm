package crypto.abe.apiV2;

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
	
	public String generateSecretKey(String[] attrs){
		return CPABEImplWithoutSerialize.keygen(attrs, pair.getPK(), pair.getMK());
	}
}

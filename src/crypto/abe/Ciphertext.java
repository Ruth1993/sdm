package crypto.abe;

import crypto.abe.serialize.Serializable;
import crypto.abe.serialize.SimpleSerializable;
import it.unisa.dia.gas.jpbc.Element;

public class Ciphertext implements SimpleSerializable{
	@Serializable
	Policy p;
	@Serializable(group="GT")
	Element Cs; //GT
	@Serializable(group="G1")
	Element C;  //G1
}

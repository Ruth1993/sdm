package cn.edu.pku.ss.crypto.abe;

import it.unisa.dia.gas.jpbc.Element;

import java.util.ArrayList;
import java.util.List;

import cn.edu.pku.ss.crypto.abe.serialize.Serializable;
import cn.edu.pku.ss.crypto.abe.serialize.SimpleSerializable;

public class Policy implements SimpleSerializable {
	/* serialized */
	@Serializable
	int k; /* one if leaf, otherwise threshold */
	@Serializable
	String attr; /* attribute string if leaf, otherwise null */
	@Serializable(group = "G1")
	Element Cy; /* G_1, only for leaves */
	@Serializable(group = "G1")
	Element _Cy; /* G_1, only for leaves */
	// List<Policy> children; /* pointers to bswabe_policy_t's, len == 0 for
	// leaves */
	@Serializable
	public Policy[] children;

	/* only used during encryption */
	transient Polynomial q;

	/* only used during decryption */
	public transient int satisfiable;
	public transient int min_leaves;
	public transient int attri;
	public transient List<Integer> satl;
}
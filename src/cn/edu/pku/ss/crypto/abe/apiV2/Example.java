package cn.edu.pku.ss.crypto.abe.apiV2;

import java.io.File;

import crypto.abe.api.Server;

public class Example {
	
	private static final String prefix = "test_output/apiv2Example/";
	
	public static void main(String[] args) {
		Server server = new Server(); // Create Server's KeyPair (PK, SK)
		Client PKUClient = new Client(new String[]{"PKU", "Student"});
		Client THUClient = new Client(new String[]{"THU", "Student2"});
		Client TeacherClient = new Client(new String[]{"PKU", "Teacher"});
		//client从server处获取公钥字符串
		String PKJSONString = server.getPublicKeyInString();
		PKUClient.setPK(PKJSONString);
		THUClient.setPK(PKJSONString);
		TeacherClient.setPK(PKJSONString);

		//client将自己的属性信息发送给server,并获取私钥字符串
		String SKJSONString = server.generateSecretKey(PKUClient.getAttrs());
		PKUClient.setSK(SKJSONString);
		
		SKJSONString = server.generateSecretKey(THUClient.getAttrs());
		THUClient.setSK(SKJSONString);
		
		SKJSONString = server.generateSecretKey(TeacherClient.getAttrs());
		TeacherClient.setSK(SKJSONString);
		
		//加密
		String outputFileName = prefix + "test.cpabe";
		File in = new File(prefix + "README.md");
		String policy = "Student OR Teacher"; // Policy can be in a shape of "Student OR (Teacher & something)"
		PKUClient.enc(in, policy, outputFileName);
		
		//解密
		in = new File(outputFileName);
		THUClient.dec(in);
//		TeacherClient.dec(in);
	}
}

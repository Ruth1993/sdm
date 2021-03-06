package crypto.abe.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import databaseAccess.DBConnection;

public class Example {

	public static void main(String[] args) {
		Server server = new Server();
		Client PKUClient = new Client(new String[] { "PKU", "Student" });
		Client THUClient = new Client(new String[] { "THU", "Student" });
		Client TeacherClient = new Client(new String[] { "writer", "Teacher" });
		// client从server处获取公钥字符串
		String MKJSONString = server.getMasterKeyInString();
		System.out.println(MKJSONString);
		server.setMasterKey(
				"{\"MK\":\"RQAAABQhj4mzt9P9noqpFwIq8fVueBhF9EUAAACATn+ExX5XRex8jX/jsDS3mrfjEPyMcLuIZK6EE2lz8WgS3Zs5o7fEEv8M7q/NepgCD9q430XBk7fD5lTYQkQLJCMq4DvXNztYocBDGBoRlw4egH+k1nZR/rfBzNjgGkjSpgjKtZqSuf6DAxnb9j/or9F8ZA+NQmBHelG2rQSGAnE=\"}");
		MKJSONString = server.getMasterKeyInString();
		System.out.println(MKJSONString);

		String PKJSONString = server.getPublicKeyInString();
		System.out.println(PKJSONString);
		server.setPublicKey(
				"{\"PK\":\"RQAAAIAW7oKu9Bs4Qjc23HFB6NTfEBUgacyzbYGzyOPdz0ZNp/Mcd5hcAux6M+hxABpMvZ+tQYKKBMnkuPV/YxbhXr6bpRzSdbwy43IQWv4j8yjKaYj1jgeJNtAh+0thWig6RoOl9lLTJhoQhMx+AKvuGngrNb7oG9ii3T3ZbbVbVlb0tEUAAACAeb3Ijl6pavo09K+Ybs6L0FGWYKhnH3wEGCQ68BorNcxkLsfM/ZsQT90M1D5jVQ9PSO6VNEJCJOg2GBX47W4uK2yXmi+sBJYYM5HQ5OJ249MF1/VWT7sT1i8mes97haLz2ddI442wYOJ29nz5kyxbitFqPJ4OTvckit3trpFP9XBFAAAAgBUyBUz8VPJuLGlFs/w3aOi0xXDqOMZkvmWs8KFP6dAiHuBceJZggBEZ/1Z+kdo5ZbLt0QnonlnAKO+qn/P1UrdQX6/y5upYbEuPCpzms378Bhq4wrpA0kTjFbYo8EjwScMNwJlIdrp2NLD4oBO63aEHmagUobtm0XP7KmX0esy9RQAAAIARGlJnZLnE+rf+0W/y364R1pP9qkBEzqfHCx0DFWoosj8AEMG8vCMU2IYyySyGLlXlkJ95P/RgckWgjzUYJgiYcom+Z51mt0G4zgHgdSvYfK0rF1ZNZ50ez0lfuQEJsvGL9APn8Wgkx3fNMofXmwydcyCiYUOd/T5W/vMgXRTUyA==\"}");
		PKJSONString = server.getPublicKeyInString();
		System.out.println(PKJSONString);
		PKUClient.setPK(PKJSONString);
		THUClient.setPK(PKJSONString);
		TeacherClient.setPK(PKJSONString);

		// client将自己的属性信息发送给server,并获取私钥字符串
		String SKJSONString = server.generateSecretKey(PKUClient.getAttrs());
		System.out.println(SKJSONString);
		PKUClient.setSK(
				"{\"SK\":\"RQAAAICk+hxRRzRx06tMPUMjaK9miRiSLgAcUHDTjrGPcfryHuCeGpg/U1A/FBnYF2DcvNFixv98YdCFwnETYFnKTDDqG+Dnl2EXb1xJzE3vHveciF/9Y9S31LWfPoip3g1osQCjgiu/AtaNMY8AzM5Hjn32P14SSldu4c1T2XrvZhglt1sAAAACUwADUEtVRQAAAIBmMpuF+1BSmRwHpOmdSRDE3LPMMMfTb99IaE9Xq1I831LxtwRg3zxMg+L8foBfOjo4kUMZgcWi6RHJK1MOtQIXMpWdVBT9jSDKAI69vL2/GiwQFIRmxAMJTJSpGhUOuF54+qSgiMJY9afvmyrW+R0YLdILD2rx+pZEY1FqlJ7jjkUAAACAEdo+zdXVdXrU/OPJIefwdgFvLxUIt9zxYKZPNT9RLPiyijwG1aJsfrjO5wqjPRz31uCqOYUzh82BiSz/bgVCZ5rZ2x/6JBOe7tCWPBPCLoR0F6cJtfArIV6H3SN0KhDlUdxJfmHC7Jrl9CmE53AjwEXD9Fr9jCs3aUCUthWJVlRTAAdTdHVkZW50RQAAAIBmMpuF+1BSmRwHpOmdSRDE3LPMMMfTb99IaE9Xq1I831LxtwRg3zxMg+L8foBfOjo4kUMZgcWi6RHJK1MOtQIXMpWdVBT9jSDKAI69vL2/GiwQFIRmxAMJTJSpGhUOuF54+qSgiMJY9afvmyrW+R0YLdILD2rx+pZEY1FqlJ7jjkUAAACALH/Vp94VscjoN82C3Em1lqci6pOOLrxzQhAzK92DSl5w4vyroxtEgvcgCULV98mEB12m+P9PiDu2eMFiFXzdlwZPVWPaLUFJR8PDLsKnBsPMKksA34VtK6l1RXOxX1J69JJqcrLyY29HPJpYX8cqXBLGI40/ttBknojomw/D0SY=\"}");

		SKJSONString = server.generateSecretKey(THUClient.getAttrs());
		System.out.println(SKJSONString);
		THUClient.setSK(
				"{\"SK\":\"RQAAAIAWepvMI+PKlOjg+RjKnJkjC7EXaRJLsTsU1qrq9/4si5r1gwiN/kfFMyuQQ2BDvksBQdIOjn7RllF1pm6o3Li1of6FIuUErY1FoHXUbWt6Rr+XA1FnEQ5coIxhQbpTZ4b0Me1V/juD3tSWHjIbBsIdUNtqV4nV0H/pedeFunuE9VsAAAACUwADVEhVRQAAAIBOmdLtZTxfbjs15ztxFs3VZfXGYoQOt8zKZRQwwmWyNC6StweyQljdRwnO+DWhi9je2cPUkCbxV4BrnJaGRqzcb9uAKZ7Q1ELNxSssTdYGXiJv2Sg9//me/oNzyqHUMT7Z7zfa2qcMZYniTXKmy+YWGymD2ecXdNH5RoOrKX1e8EUAAACAkG9Io8gT94YA/uqzCyiwRcZGz/6PqxXCkuXSFVqSfEglLA6HRcTCs79bwHj5FbXhQFEwcwLOqy9iVrr9M9ztSm+G2/cIl63WGslQMjJJPr/xt+gRxVMZdMUbT0W+KAVdAAMVda6Ng1tdwzJBzE8aUV3jwAX4RtkbLGnlw4V2gWpTAAdTdHVkZW50RQAAAIBOmdLtZTxfbjs15ztxFs3VZfXGYoQOt8zKZRQwwmWyNC6StweyQljdRwnO+DWhi9je2cPUkCbxV4BrnJaGRqzcb9uAKZ7Q1ELNxSssTdYGXiJv2Sg9//me/oNzyqHUMT7Z7zfa2qcMZYniTXKmy+YWGymD2ecXdNH5RoOrKX1e8EUAAACAJvE/0gQMj+TnfkvFAAOt2yDbnohDezssctnyWMSUxDRM3e8rPNw9Fvrm8pEO85Fkd7pjTISh6yqA5KLZT04BQpLY67yaMgRd8sdQ+7isvMfaRcGzIbeuDUSCGTCm5cTaN4AZ5SEBVVuyCy3sjrNnxFOUbRasnxZI5bJCAUhNtVE=\"}");

		SKJSONString = server.generateSecretKey(TeacherClient.getAttrs());
		System.out.println(SKJSONString);
		TeacherClient.setSK(
				"{\"SK\":\"RQAAAICLVp2p1KbxyMWfwgFgpfESNNe3fE1x4HwVs0iJVdJijQHphajnkLTHQ4IhbMdH2i1NQ736yYAT39N5mj/MgdOMlFqDHRvri6wugmTv+Ee+UKdttA61NQhNcd2QLp6tknxsaU9tk9NeHHdTDKd8SLe6AWFDAoLAZ3esglOnu9Kj6FsAAAACUwAGd3JpdGVyRQAAAIBn9CKWqDXYDSsywZfec8qFo3SQlVG5q1/p0ReiZRZiJl+I4aBVKtRDfAPW7tfTD4regZqPpV+PwADwEMfkRUVzhw42j1rZsWcqpHWJc6jrpPx+z4FgJsh7Llv2rxNzIa6XMe4zlCk8Ky0FHGe/6ofOBDLsuiMV1N+iPvPU4zAW/0UAAACAiVnzQMaglTXCFDNHMyxCUCNAU6guoflID5yQKgGt46FJQgnhkddaw8BE4bYd55bXOseXUKIdcMmNJX36Gh1x3pqP4YXw3CdAyxu/gm5K+NDnp13WfdnD0bvSnRxOzMI5MzNPQ5MO8wQ2ORCeF/mSI8iHoloBmQ36wI2MKECIO/ZTAAdUZWFjaGVyRQAAAIBn9CKWqDXYDSsywZfec8qFo3SQlVG5q1/p0ReiZRZiJl+I4aBVKtRDfAPW7tfTD4regZqPpV+PwADwEMfkRUVzhw42j1rZsWcqpHWJc6jrpPx+z4FgJsh7Llv2rxNzIa6XMe4zlCk8Ky0FHGe/6ofOBDLsuiMV1N+iPvPU4zAW/0UAAACAdAhjdlz8t5EtJn8cBjLXKxq4TiQbLnhAO5HZDgG9vxaNqQ7dUr+4H+kwIrv1tUuPqU3+OUshSkCJpgXgDnWDHzywC0fG9SCgtTcMiY6kdTML+6VaC3XnXzMH28Y4FzEuAYagqgoMXXoLTR1MK7rs0qacFthVRVK4qyIALNw0dnk=\"}");

		// 加密
		String outputFileName = "test.cpabe";
		File in = new File("README.md");
		String policy = "( reader AND PKU ) OR ( writer AND Teacher ) OR id1";
		byte[] b1 = PKUClient.enc("esrdhtyj,nbvcderThe implementation of Ciphertext Policy Attribute Based Encryption in Java.", policy,
				outputFileName);
		System.out.println(b1);
		/*Connection connection = DBConnection.getConnection();
		String sql = "INSERT INTO testtable (ciphertext) VALUES(?)";
		PreparedStatement pstmt;
		byte[] bytes = null;
		try {
			pstmt = connection.prepareStatement(sql);

			// j a v a 2s . co m
			// byte[] buffer = "some data".getBytes();
			pstmt.setBytes(1, b1);
			pstmt.executeUpdate();
			pstmt.close();

			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM testtable");

			while (resultSet.next()) {
				bytes = resultSet.getBytes("ciphertext");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 解密
		/*
		 * in = new File(outputFileName);
		 * System.out.println(readAllBytesJava7(outputFileName));
		 * System.out.println(""); byte[] b =null; try { b =
		 * org.apache.commons.io.IOUtils.toByteArray(new FileInputStream(in));
		 * System.out.println(b); } catch (FileNotFoundException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch (IOException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		/*
		TeacherClient.dec(bytes);
		System.out.println();
		THUClient.dec(bytes);*/
	}
}

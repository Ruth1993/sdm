package main;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import cn.edu.pku.ss.crypto.abe.apiV2.Server;
import databaseAccess.DBConnection;
import sdm.*;


public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("SDM Project");
		Server server = new Server();
		
		//server.setMasterKey(
		//		"{\"MK\":\"RQAAABQhj4mzt9P9noqpFwIq8fVueBhF9EUAAACATn+ExX5XRex8jX/jsDS3mrfjEPyMcLuIZK6EE2lz8WgS3Zs5o7fEEv8M7q/NepgCD9q430XBk7fD5lTYQkQLJCMq4DvXNztYocBDGBoRlw4egH+k1nZR/rfBzNjgGkjSpgjKtZqSuf6DAxnb9j/or9F8ZA+NQmBHelG2rQSGAnE=\"}");
		//server.setPublicKey(
		//		"{\"PK\":\"RQAAAIAW7oKu9Bs4Qjc23HFB6NTfEBUgacyzbYGzyOPdz0ZNp/Mcd5hcAux6M+hxABpMvZ+tQYKKBMnkuPV/YxbhXr6bpRzSdbwy43IQWv4j8yjKaYj1jgeJNtAh+0thWig6RoOl9lLTJhoQhMx+AKvuGngrNb7oG9ii3T3ZbbVbVlb0tEUAAACAeb3Ijl6pavo09K+Ybs6L0FGWYKhnH3wEGCQ68BorNcxkLsfM/ZsQT90M1D5jVQ9PSO6VNEJCJOg2GBX47W4uK2yXmi+sBJYYM5HQ5OJ249MF1/VWT7sT1i8mes97haLz2ddI442wYOJ29nz5kyxbitFqPJ4OTvckit3trpFP9XBFAAAAgBUyBUz8VPJuLGlFs/w3aOi0xXDqOMZkvmWs8KFP6dAiHuBceJZggBEZ/1Z+kdo5ZbLt0QnonlnAKO+qn/P1UrdQX6/y5upYbEuPCpzms378Bhq4wrpA0kTjFbYo8EjwScMNwJlIdrp2NLD4oBO63aEHmagUobtm0XP7KmX0esy9RQAAAIARGlJnZLnE+rf+0W/y364R1pP9qkBEzqfHCx0DFWoosj8AEMG8vCMU2IYyySyGLlXlkJ95P/RgckWgjzUYJgiYcom+Z51mt0G4zgHgdSvYfK0rF1ZNZ50ez0lfuQEJsvGL9APn8Wgkx3fNMofXmwydcyCiYUOd/T5W/vMgXRTUyA==\"}");
		
		Person p1 = new Person(2, "Valentine");
		p1.setAttrs(new String[] { "id2", "employee", "company1" });
		String PKJSONString = server.getPublicKeyInString();
		p1.setPK(PKJSONString);
		String SKJSONString = server.generateSecretKey(p1.getAttrs());
		System.out.println(SKJSONString);
		p1.setSK(SKJSONString);
		//String SKJSONString = server.generateSecretKey(p1.getAttrs());
		//System.out.println(SKJSONString);
		//p1.setSK("{\"SK\":\"RQAAAIA4dsg5bZFfmhivOeGwdvoUrLfFTCUy83iXxJ0sPblxaRCrFhHsBe5mwZCEytlGE6xj7OjztN17yha4M+GUxT3/andNtMj8qWBKJdIgJvHQONbBYjYPjoJVjg6BZNoDCKQ11CD5yD3iulJ9fX42/4UTxLVBYuG7rtfj7XYLcmfS6lsAAAADUwADaWQyRQAAAIAjRQx+/jmTh6KZhOclyHbVcs6viB/hpl25w+DsjSKj9ZfDxmYyWSlgvKQHVipxQai60Gdrx0psPRXUTP4BYnhsDlotX86ifu9tGKZJobri9FsamNw9sDA3AFNSfi08zygKWIAIYGlUU2AEoRK2U5pdQNx643RK1Ta8y42A4l6ES0UAAACAdxaasAvvdL08qoNaKLE/ehVrItQxBSZ4N6mFTvdGigV/VPeLe3YOC4QcgFUaV384uNVfF2zijrubhx9dP5/6bHakzQKE2oKznmcTVKK1e11kK0Z6ao7NHBwyk8WNQA/+tZ6cDXGGccFfB90smOYSHHdOnABL4W6/mgBy79ns7cpTAAhlbXBsb3llZUUAAACAI0UMfv45k4eimYTnJch21XLOr4gf4aZducPg7I0io/WXw8ZmMlkpYLykB1YqcUGoutBna8dKbD0V1Ez+AWJ4bA5aLV/Oon7vbRimSaG64vRbGpjcPbAwNwBTUn4tPM8oCliACGBpVFNgBKEStlOaXUDceuN0StU2vMuNgOJehEtFAAAAgFiSXU+gnbGGxp6yL/r+6WxiC7yen4FBENOv3z242KzToRobndnxYUUICtUXeIxyNR5kqNZJt7cgA0VFWf8JApppAMKWHnP1CGXGUF5Dggrw95xDJ6EXxpyTaHBppn8kNdBGSoqOItviT4v4TPBUNRNdU7WSD9ypaG1RstqoYdqtUwAIY29tcGFueTFFAAAAgCNFDH7+OZOHopmE5yXIdtVyzq+IH+GmXbnD4OyNIqP1l8PGZjJZKWC8pAdWKnFBqLrQZ2vHSmw9FdRM/gFieGwOWi1fzqJ+720YpkmhuuL0WxqY3D2wMDcAU1J+LTzPKApYgAhgaVRTYAShErZTml1A3HrjdErVNrzLjYDiXoRLRQAAAIB210hH2yKd6ouGfsuQ+lVKskK1A8Z3qkumFGz3LwFfIKUoJzDqvOFX4BIMXbHGlWaJPxSrxbTssyJp30YXpogZHdQq/+2Izd7E9Qki+CWV+YrAJaGRvcVeFhijDu/5nHyDPQgOB+sS4Oapy6zAyughUHDe1iLejLC+Jd7HCkfCkw==\"}");

		p1.updateBasicInfoDB(2, "Name", "Birth date", "Birth place", "Gender","Nationality", "Address","number");
		ArrayList<String> bi = p1.readBasicInfoDB(2);
		System.out.println(bi.toString());
	}

}
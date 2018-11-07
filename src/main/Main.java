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

import crypto.abe.api.Server;
import databaseAccess.DBConnection;
import sdm.*;


public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("SDM Project");
		Server server = new Server();
		server.setMasterKey(
				"{\"MK\":\"RQAAABQhj4mzt9P9noqpFwIq8fVueBhF9EUAAACATn+ExX5XRex8jX/jsDS3mrfjEPyMcLuIZK6EE2lz8WgS3Zs5o7fEEv8M7q/NepgCD9q430XBk7fD5lTYQkQLJCMq4DvXNztYocBDGBoRlw4egH+k1nZR/rfBzNjgGkjSpgjKtZqSuf6DAxnb9j/or9F8ZA+NQmBHelG2rQSGAnE=\"}");
		server.setPublicKey(
				"{\"PK\":\"RQAAAIAW7oKu9Bs4Qjc23HFB6NTfEBUgacyzbYGzyOPdz0ZNp/Mcd5hcAux6M+hxABpMvZ+tQYKKBMnkuPV/YxbhXr6bpRzSdbwy43IQWv4j8yjKaYj1jgeJNtAh+0thWig6RoOl9lLTJhoQhMx+AKvuGngrNb7oG9ii3T3ZbbVbVlb0tEUAAACAeb3Ijl6pavo09K+Ybs6L0FGWYKhnH3wEGCQ68BorNcxkLsfM/ZsQT90M1D5jVQ9PSO6VNEJCJOg2GBX47W4uK2yXmi+sBJYYM5HQ5OJ249MF1/VWT7sT1i8mes97haLz2ddI442wYOJ29nz5kyxbitFqPJ4OTvckit3trpFP9XBFAAAAgBUyBUz8VPJuLGlFs/w3aOi0xXDqOMZkvmWs8KFP6dAiHuBceJZggBEZ/1Z+kdo5ZbLt0QnonlnAKO+qn/P1UrdQX6/y5upYbEuPCpzms378Bhq4wrpA0kTjFbYo8EjwScMNwJlIdrp2NLD4oBO63aEHmagUobtm0XP7KmX0esy9RQAAAIARGlJnZLnE+rf+0W/y364R1pP9qkBEzqfHCx0DFWoosj8AEMG8vCMU2IYyySyGLlXlkJ95P/RgckWgjzUYJgiYcom+Z51mt0G4zgHgdSvYfK0rF1ZNZ50ez0lfuQEJsvGL9APn8Wgkx3fNMofXmwydcyCiYUOd/T5W/vMgXRTUyA==\"}");
		
		Person p1 = new Person(2, "Valentine");
		p1.setAttrs(new String[] { "2", "employee", "company1" });
		String PKJSONString = server.getPublicKeyInString();
		p1.setPK(PKJSONString);
		p1.setSK("{\"SK\":\"RQAAAICAAXQ8/WDdOKBjv7q2MaxXH4LbgaO2XrL+bcxcgUXN3faEhMvyG4LgGMkKou3SjU7IaOy9Wfa0yyzQenfKJNV5FUWnGC48OSpUnaOV3Ht0f+mKvtymPE99RVhsYpfT3c16IdB0nks0jvwn35fXPqq4/TptaHZf2k94PuyuzIL5TVsAAAADUwABMkUAAACAUNCEkdIO/1OHRd2kJVkhJ5niuoWbREl6N7+TeMd5KRtSim90P7Aur+9WCnItw3Ha29CMaXmIGYoUuxCtLmgpOJtJTec+BjQcec8Wlhx4exvk8OR6VVGB3sQBKKY9ppbZ+KS/D8wnjgjhomBj6p/BBHBUQf3/YSNjXvIxRFzS4s1FAAAAgFpgGAiy61nB1jSw+HfYmm5lc0dHdWXnl4aregAqriccWUYFF+1cd4RxCRquRXpW8XCx6OKn6lVClfnNKOZ2yRxElZ9YJ8OoJ1FmElo6fecw15KkJXH7NnGEXIigDKASLvGOT1tJMEiHlTwYdCDmj7a+GLZFXKNFejQr0eHYt70+UwAIZW1wbG95ZWVFAAAAgFDQhJHSDv9Th0XdpCVZISeZ4rqFm0RJeje/k3jHeSkbUopvdD+wLq/vVgpyLcNx2tvQjGl5iBmKFLsQrS5oKTibSU3nPgY0HHnPFpYceHsb5PDkelVRgd7EASimPaaW2fikvw/MJ44I4aJgY+qfwQRwVEH9/2EjY17yMURc0uLNRQAAAIAhgaimFvhfjATSX/uaRlCzGiQ2tlWt8ExYVHkjgmMaFP179AwpO+M7OVle/vVH7ugVLEgTBc2kudyGOWcQ6gAwTm5uk7HaJPSbeAUC/eAIJh425JYJNJcjBRsIkEKy92yramxP5lMmMTtSpULV2MTLusm/rpeeTEo9h0v29ChNclMACGNvbXBhbnkxRQAAAIBQ0ISR0g7/U4dF3aQlWSEnmeK6hZtESXo3v5N4x3kpG1KKb3Q/sC6v71YKci3Dcdrb0IxpeYgZihS7EK0uaCk4m0lN5z4GNBx5zxaWHHh7G+Tw5HpVUYHexAEopj2mltn4pL8PzCeOCOGiYGPqn8EEcFRB/f9hI2Ne8jFEXNLizUUAAACAoC3F0hmFSONmeIp5h669B/hccDobyXnMkEwdCL+anzQNTWCBHZBtut3fw6PAhJOsAZ/fKm6+lUWDvgYvktC1nARELjZpajT3Dt0Hhu6gsplr1d++K/KYQLiMan4DT6EF9SetTA12L4LGnGIlJDnf9T0AzMKVSLqNYCIGnFnQ7o8=\"}");
		
		p1.updateBasicInfoDB(2, "Name", "Birth date", "Birth place", "Gender","Nationality", "Address","number");
		ArrayList<String> bi = p1.readBasicInfoDB(2);
		System.out.println(bi.toString());
	}

}
public static void main(String[] args) throws SQLException, NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.out.println("SDM Project");
		String newLine = System.getProperty("line.separator");
		InputStreamReader istream = new InputStreamReader(System.in);
		BufferedReader bufRead = new BufferedReader(istream);
		Server server = new Server();

		server.setMasterKey(KBConnection.getMasterKey());
		server.setPublicKey(KBConnection.getPublicKey());
		String PKJSONString = server.getPublicKeyInString();
		System.out.println(PKJSONString);
		boolean exit = false;
		while (!exit) {
			System.out.print("Insert your id: ");
			int id = Integer.parseInt(bufRead.readLine());
			Person p = new Person(id);
			p.setPK(PKJSONString);
			System.out.print("Insert you secret key: ");
			String sk = bufRead.readLine();
			p.setSK("{\"SK\":\"" + sk + "\"}");
			p.startGUI();

		}
	}

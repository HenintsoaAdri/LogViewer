package agent;

import java.io.File;

public class Main {
	public static void main(String[] args) throws Exception {
		Server server = null;
		Client client = null;
		try {
			server = new Server();
			server.start();
			System.out.println("Server started");
//			File temp = File.createTempFile("app", ".log");
//			temp.deleteOnExit();
//			LogFile log = new LogFile(temp);
//			log.setFileName("\\logs - Copie\\");
			
			client = new Client();
			LogFile received = (LogFile)client.connect("localhost", 2008);
			System.out.println("received " + received);
//			System.out.println(log.getFile().getAbsolutePath());
//			System.out.println("distant : "+ (log.getFile() != null? log.getFile().length():""));
			Main.display(received);
			System.out.println("Fini");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if (server != null)server.stop();
			if(client != null) client.dispose();
		}
	}
	public static void display(LogFile log){
		if(log.getChild() == null){
			System.out.println(log.getFileName());
		}
		else{
			for(LogFile f : log.getChild()){
				display(f);
			}
		}
	}
}

package agent;

import java.io.File;

public class Main {
	public static void main(String[] args) throws Exception {
		try {
			Server server = new Server();
			server.start();
			System.out.println("Server started");
			LogFile log = new LogFile(File.createTempFile("app", ".log"));
			log.setFileName("app.log");
			Client client = new Client(log);
			System.out.println(client.connect("localhost", 2008));
			System.out.println(log.getTempFile().getAbsolutePath());
			System.out.println("distant : "+ (log.getDistantFile() != null? log.getDistantFile().length():""));
			System.out.println("Fini");
			server.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

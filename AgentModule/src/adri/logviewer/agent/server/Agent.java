package adri.logviewer.agent.server;

import java.lang.instrument.Instrumentation;

public class Agent {
	public static void premain(String args, Instrumentation inst)throws Exception{
		int port = 0;
		try {
			port = Integer.parseInt(args);
			MinaAgentServer app = new MinaAgentServer(port);
			app.start();
			app.getLOGGER().trace("Server started...");
		} catch (NumberFormatException e) {
			System.out.println("Port invalide");
		}
	}
}
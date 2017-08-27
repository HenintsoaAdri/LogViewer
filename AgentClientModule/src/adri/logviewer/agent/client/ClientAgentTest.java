package adri.logviewer.agent.client;

import java.util.List;

import adri.logviewer.agent.file.LogFile;

public class ClientAgentTest {
	
	public void essaiAll() throws Exception{
		MinaClient client = new MinaClient();
		Object resultat = client.connect("localhost", 2008, "A://eclipse/logs");
		if(resultat instanceof List){
			System.out.println(((List<LogFile>)resultat).size());
			resultat.toString();
		}
	}
	
	public void testClient() throws Exception{
		MinaClient client = new MinaClient();
		Object detail = client.connect("localhost", 2008, "A://eclipse/logs/app-2017-07-18.log");
		if(detail instanceof List){
			LogFile f = ((List<LogFile>) detail).get(0);
			System.out.println(f.getFilename());
		}
		client.dispose();
	}

}

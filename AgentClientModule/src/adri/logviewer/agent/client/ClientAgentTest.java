package adri.logviewer.agent.client;

import java.util.List;

import org.junit.Test;

import adri.logviewer.agent.file.LogFile;

public class ClientAgentTest {
	
	@Test
	public void essaiAll() throws Exception{
		MinaClient client = new MinaClient();
		Object detail = client.connect("localhost", 2008, "");
		System.out.println(detail);
		List<LogFile> liste = (List<LogFile>)detail;
		for(LogFile f : liste){
			System.out.println(f.getDistantName());
		}
	}
	
	public void testClient() throws Exception{
		MinaClient client = new MinaClient();
		Object detail = client.connect("localhost", 2008, "app-2017-07-23.log");
		System.out.println(detail);
		List<LogFile> liste = (List<LogFile>)detail;
		if(liste instanceof List){
			LogFile f = liste.get(0);
			System.out.println(f.getFilename());
			System.out.println(f.getTempName());
		}
		client.dispose();
	}

}

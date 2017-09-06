package adri.logviewer.agent.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import adri.logviewer.agent.file.LogFile;

public class MinaClientHandler extends IoHandlerAdapter{

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
        LOGGER.error("Handler Error caught", cause);
        session.closeOnFlush();
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		System.out.println("message recus :");
		LOGGER.trace("message recus");
		try {
			List<LogFile> received = (List<LogFile>) message;
			if(received.size() == 1){
				LogFile f = received.get(0);
				FileOutputStream fos = null;
				try{
					File temp = File.createTempFile(f.getName(), ".log");
					temp.deleteOnExit();
					fos = new FileOutputStream(temp);
					fos.write(f.getFile());
					f.setTempName(temp.getAbsolutePath());
					System.out.println("Fichiers mis en cache");
					LOGGER.trace("Fichiers mis en cache");
				}catch(IOException e){
					throw e;
				}finally{
					if(fos != null){
						fos.close();
					}
				}
			}
			session.closeOnFlush();
		} catch (ClassCastException e) {
			throw new Exception((String)message);
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println("message envoyés : " + message.toString());
		LOGGER.trace("message envoyés : " + message.toString());
		super.messageSent(session, message);
	}
	
}

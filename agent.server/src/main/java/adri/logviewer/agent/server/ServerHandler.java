package adri.logviewer.agent.server;

import java.io.File;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.util.Properties;

import org.apache.mina.core.file.FilenameFileRegion;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerHandler extends IoHandlerAdapter{
	
	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private FileInputStream input;
    private final Properties properties;

	public ServerHandler(Properties properties) {
		this.properties = properties;
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) {
		session.write(cause.getMessage());
		cause.printStackTrace();
        LOGGER.error("Server Handler Exception caught", cause);
		session.closeNow();
	}

	@Override
	public void messageReceived(IoSession session, Object buf) throws Exception {
		String message = buf.toString();
    	LOGGER.trace("recherche de : " + message);
    	File f = LogFileSearcher.getInstance().getFile(message);
    	if(f.isDirectory()){
			session.write(LogFileSearcher.getInstance().search(f));
			LOGGER.trace("envoi de la liste LogFile");
			return;
    	}else{
			input = new FileInputStream(f);
    		session.write(new FilenameFileRegion(f, input.getChannel()));
			LOGGER.trace("envoi de " + f.getName());
    		return;
    	}
	}
	
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		if(input != null){
			input.getChannel().close();
			input.close();
		}
		session.closeOnFlush();
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) {
		session.closeNow();
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		InetSocketAddress adresse = (InetSocketAddress) session.getRemoteAddress();		
		if(!adresse.getAddress().getHostAddress().contentEquals(properties.getProperty("ipAdress").trim())){
			throw new Exception("Connexion refus�e � l'hote : " + adresse.getAddress().getHostAddress());
		}		
	}

}

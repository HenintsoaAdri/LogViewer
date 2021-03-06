package adri.logviewer.agent.server;

import java.net.InetSocketAddress;
import java.util.Properties;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinaAgentHandler extends IoHandlerAdapter {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final Properties properties;
    
    public MinaAgentHandler(Properties properties) {
		super();
		this.properties = properties;
	}

	@Override
    public void exceptionCaught( IoSession session, Throwable cause ) throws Exception {
        cause.printStackTrace();
        LOGGER.error("Handler Error caught", cause);
        session.write(cause.getMessage());
        session.closeNow();
    }
    
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		String str = message.toString().trim();
        if(str.equalsIgnoreCase("quit")) {
            session.closeOnFlush();
            return;
        }
        session.write(LogFileSearcher.getInstance().search(str));
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		session.closeOnFlush();
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		session.closeNow();
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		InetSocketAddress adresse = (InetSocketAddress) session.getRemoteAddress();		
		if(!adresse.getAddress().getHostAddress().contentEquals(properties.getProperty("ipAdress"))){
			throw new Exception("Connexion refus�e � l'hote : " + adresse.getAddress().getHostAddress());
		}		
	}
    
}

package adri.logviewer.agent.server;


import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinaAgentHandler extends IoHandlerAdapter {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    
    @Override
    public void exceptionCaught( IoSession session, Throwable cause ) throws Exception {
        cause.printStackTrace();
        LOGGER.error("Handler Error caught", cause);
        session.write(cause.getMessage());
        session.closeOnFlush();
    }
    
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		String str = message.toString();
        if(str.trim().equalsIgnoreCase("quit")) {
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
		session.closeOnFlush();
	}
    
}
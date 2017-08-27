package adri.logviewer.agent.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import javax.net.ssl.SSLContext;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationEncoder;
import org.apache.mina.filter.codec.textline.TextLineDecoder;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.filter.ssl.SslFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinaAgentServer{

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private int port = 2008;
	private IoAcceptor server;
	private SslFilter sslFilter;
	
//	public static void main(String[] args) throws Exception{
//		try{
//			int port = 0;
//			try{
//				if(!args[0].equalsIgnoreCase("-p")){
//					throw new Exception("Commande invalide : " + args[0]);
//				}
//				port = Integer.parseInt(args[1]);
//			}catch(IndexOutOfBoundsException e){
//				System.out.println("Valeur de port inexistante.");
//			}
//			MinaAgentServer app = new MinaAgentServer(port);
//			app.start();
//			app.LOGGER.trace("Server started...");
//		}catch(Exception e){
//			throw e;
//		}
//	}
	
	public MinaAgentServer(int port){
		try {
			setPort(port);
			setSslFilter(createSSLFilter());
			setServer(createServer());
		} catch (Exception e) {
			LOGGER.error("Erreur d'initialisation", e);
		}
	}

	public Logger getLOGGER() {
		return LOGGER;
	}

	public IoAcceptor getServer() {
		return server;
	}
	public void setServer(IoAcceptor server) {
		this.server = server;
	}

	public IoAcceptor createServer() throws IOException{
		  IoAcceptor acceptor = new NioSocketAcceptor();
		  
		  acceptor.getFilterChain().addLast( "sslFilter", getSslFilter() );
		  acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );
		  
		  ObjectSerializationEncoder encoder = new ObjectSerializationEncoder();
		  encoder.setMaxObjectSize(50000000);
		  
	      acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter(encoder, new TextLineDecoder(Charset.forName("UTF-8"))));
	      acceptor.setHandler(new MinaAgentHandler());
	      acceptor.getSessionConfig().setReadBufferSize(2048);
	      acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 30);
	      
	      return acceptor;
	}

	public SslFilter getSslFilter() {
		return sslFilter;
	}
	public void setSslFilter(SslFilter sslFilter) {
		this.sslFilter = sslFilter;
	}
	public SslFilter createSSLFilter() throws Exception{
		SSLContext context = null;
		SslFilter filter = null;
		try {
			context = SSLContext.getInstance("TLS");
			context.init(null, null, null);
			filter = new SslFilter(context);
			return filter;
		} catch (Exception e) {
			LOGGER.error("Erreur initialisation du context SSL", e);
			throw e;
		}
	}
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		if(port > 0){
			this.port = port;
		}else{
			LOGGER.warn("Port invalide : " + port);
			LOGGER.warn("AutoSet Port : " + getPort());
		}
	}
	public void start() throws IOException{
		getServer().bind( new InetSocketAddress(getPort()) );
	}
}

package adri.logviewer.agent.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Properties;

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
	private Properties properties;
	
	public static void main(String[] args) throws Exception{
	  InputStream in = null;
	  Properties properties = new Properties();
		try{
			File p = new File("app.properties");
			if(!p.exists()) throw new Exception("Le fichier de configuration 'app.properties' est introuvable.");
			in = new FileInputStream(p);
			properties.load(in);
			MinaAgentServer app = new MinaAgentServer(properties);
			app.start();
			app.LOGGER.trace("Le serveur a d�mar�...");
		}catch(Exception e){
			throw e;
		}finally{
			if(in != null){
				in.close();
			}
		}
	}
	
	public MinaAgentServer(Properties properties) throws Exception{
		try {
			this.setProperties(properties);
			setSslFilter(createSSLFilter());
			setServer(createServer());
		}catch (NumberFormatException e) {
			throw new Exception("Port invalide");
		}catch (Exception e) {
			LOGGER.error("Erreur d'initialisation", e);
			throw e;
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
	      acceptor.setHandler(new MinaAgentHandler(getProperties()));
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
			LOGGER.trace("AutoSet Port : " + getPort());
		}
	}
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) throws Exception {
		this.properties = properties;
		checkProperties(properties);
		setPort(Integer.parseInt(properties.getProperty("port")));
	}
	
	public void checkProperties(Properties properties) throws Exception{
		if(!properties.containsKey("ipAdress")){
			throw new Exception("la propri�t� 'ipAdress' est introuvable");
		}
		if(!properties.containsKey("path")){
			throw new Exception("la propri�t� 'path' est introuvable");
		}
		LOGGER.trace("Adresse IP : " + properties.getProperty("ipAdress"));
		LOGGER.trace("Dossier de log : " + properties.getProperty("path"));
	}

	public void start() throws IOException{
		getServer().bind( new InetSocketAddress(getPort()) );
	}
}

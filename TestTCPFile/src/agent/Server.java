package agent;

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
import org.apache.mina.filter.ssl.SslContextFactory;
import org.apache.mina.filter.ssl.SslFilter;
import org.apache.mina.filter.stream.FileRegionWriteFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private int port = 2008;
	private IoAcceptor server;
	private Properties properties;
	
	public Server() throws Exception {
		InputStream in = null;
		try {
			File p = new File("app.properties");
			if(!p.exists()) throw new Exception("Le fichier de configuration 'app.properties' est introuvable.");
			in = new FileInputStream(p);
			Properties properties = new Properties();
			properties.load(in);
			this.setProperties(properties);
			this.server = createServer(createSslFilter());
		}catch (NumberFormatException e) {
			e.printStackTrace();
			throw new Exception("Port invalide");
		}catch (Exception e) {
			LOGGER.error("Erreur d'initialisation", e);
			throw e;
		}finally{
			if(in != null){
				in.close();
			}
		}
	}
	private IoAcceptor createServer(SslFilter sslFilter) {
		IoAcceptor acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addFirst( "sslFilter", sslFilter );
	 	acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );
	 	ObjectSerializationEncoder encoder = new ObjectSerializationEncoder();
		acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter(encoder, new TextLineDecoder(Charset.forName("UTF-8"))));
	 	acceptor.getFilterChain().addLast("fileRegionWrite", new FileRegionWriteFilter());
	 	acceptor.setHandler(new ServerHandler(properties));
	    acceptor.getSessionConfig().setReadBufferSize(2048);
	    acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 20);
		return acceptor;
 	}
	public SslFilter createSslFilter() throws Exception{
		SSLContext context = null;
		SslFilter filter = null;
		try {
			context = new SslContextFactory().newInstance();
			filter = new SslFilter(context);
			return filter;
		} catch (Exception e) {
			LOGGER.error("Erreur initialisation du context SSL", e);
			throw e;
		}
	}
	private void setProperties(Properties properties) throws Exception {
		if(!properties.containsKey("ipAdress")){
			throw new Exception("la propri�t� 'ipAdress' est introuvable");
		}
		if(!properties.containsKey("path")){
			throw new Exception("la propri�t� 'path' est introuvable");
		}
		LOGGER.trace("Adresse IP : " + properties.getProperty("ipAdress"));
		LOGGER.trace("Dossier de log : " + properties.getProperty("path"));
		this.properties = properties;
		setPort(properties.getProperty("port"));
	}
	private void setPort(String port){
		if(port != null){
			setPort(Integer.parseInt(port.trim()));
			return;
		}
		LOGGER.trace("Valeur de port inexistante dans le fichier 'app.properties'. AutoSet Port : " + getPort());
	}
	private void setPort(int port) {
		if(port > 0){
			this.port = port;
		}else{
			LOGGER.warn("Port invalide : " + port);
			LOGGER.trace("AutoSet Port : " + getPort());
		}
	}
	public int getPort(){
		return port;
	}
	public void start() throws IOException{
		server.bind(new InetSocketAddress(getPort()));
		LOGGER.trace("Le serveur agent a d�marr�...");
	}
	public void stop()throws IOException{
		if(server != null){
			server.dispose();
		}
	}
}

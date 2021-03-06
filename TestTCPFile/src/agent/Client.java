package agent;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import javax.net.ssl.SSLContext;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationDecoder;
import org.apache.mina.filter.codec.textline.TextLineEncoder;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.filter.ssl.SslContextFactory;
import org.apache.mina.filter.ssl.SslFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client{
	
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private NioSocketConnector client;
	private IoSession session;
	private LogFile log;

	public Client() throws Exception {
		this.client = createClient(createSslFilter());
	}
	public Client(LogFile log) throws Exception {
		setLog(log);
		this.client = createClient(createSslFilter());
	}
	private void setLog(LogFile log) {
		this.log = log;
	}
	public Object connect(String host, int port, LogFile log)throws Exception{
		this.log = log;
		return connect(host, port);
	}
	public Object connect(String host, int port) throws Exception{
		try {
			for(int i = 0; i<5; i++){
				try {
					ConnectFuture connexion = client.connect(new InetSocketAddress(host, port));
					connexion.awaitUninterruptibly();
					setSession(connexion.getSession());
					LOGGER.trace("Utilisateur connect�");
					session.write(log != null ? log.getFileName() : "");
					ReadFuture read = session.read();
					read.awaitUninterruptibly();
					return read.getMessage();
				} catch (RuntimeIoException e) {
					Thread.sleep(5000);
					e.printStackTrace();
				}
			}
			throw new Exception("Connexion impossible");
		} catch (Exception e) {
			LOGGER.error("Connexion �chou�e", e);
			throw e;
		}
	}
	private NioSocketConnector createClient(SslFilter SslFilter){
		NioSocketConnector client = new NioSocketConnector();
		client.setConnectTimeoutMillis(30000);
	    client.setConnectTimeoutCheckInterval(5000);
		
		client.getFilterChain().addFirst("sslFilter",
				SslFilter);
		client.getFilterChain().addLast("cache", new FileCacheFilter(log));
		ObjectSerializationDecoder decoder = new ObjectSerializationDecoder();
		client.getFilterChain().addLast( "codec", new ProtocolCodecFilter(new TextLineEncoder(Charset.forName("UTF-8")), decoder));
		client.getFilterChain().addLast( "logger", new LoggingFilter() );
		client.setHandler(new ClientHandler());
		client.getSessionConfig().setReadBufferSize(2048);
		client.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 60);
		client.getSessionConfig().setUseReadOperation(true);
		return client;
	}
	private SslFilter createSslFilter() throws Exception {
	    SSLContext context = new SslContextFactory().newInstance();
	    SslFilter ssl = new SslFilter(context);
	    ssl.setUseClientMode(true);
	    return ssl;
	}
	private void setSession(IoSession session) {
		this.session = session;
	}
	public void closeSession(){
		if(session != null){
			session.closeOnFlush();
			LOGGER.trace("Session ferm�e");
		}
	}
	public void dispose(){
		if(client != null){
			client.dispose();
		}
	}
}

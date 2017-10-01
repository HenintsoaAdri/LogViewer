package adri.logviewer.agent.client;

import java.io.FileOutputStream;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;

import adri.logviewer.agent.file.LogFile;

public class FileCacheFilter extends IoFilterAdapter{
	LogFile temp;
	
	public FileCacheFilter(LogFile temp) {
		setTemp(temp);
	}
	
	public LogFile getTemp() {
		return temp;
	}
	public void setTemp(LogFile temp) {
		this.temp = temp;
	}

	@Override
	public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
		if(message instanceof IoBuffer){
			IoBuffer buffer = (IoBuffer) message;
			try{
				Object received = buffer.duplicate().getObject();
				if(received instanceof LogFile){
					nextFilter.messageReceived(session, buffer);
				}
			}catch(Exception e){
				FileOutputStream fos = null;
				if(buffer.remaining() > 0){
					byte[] bytes = new byte[buffer.remaining()];
					try {
						fos = new FileOutputStream(temp.getFile(), true);
						buffer.get(bytes);
						fos.write(bytes);
					}finally {
						if(fos != null){
							fos.close();
						}
					}
				}
			}
		}
	}
	
}

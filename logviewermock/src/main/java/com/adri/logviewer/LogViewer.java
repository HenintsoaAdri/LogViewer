package com.adri.logviewer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
        
public class LogViewer {
    
    private static final LogViewer VIEWER = new LogViewer();
    private static final Logger LOGGER = LogManager.getLogger(LogViewer.class);
    private LogViewer(){
    }
    public static LogViewer getInstance(){
        return VIEWER;
    }

    public Logger getLogger() {
        return LOGGER;
    }
    
    public static void main(String[] args)
    {
        LOGGER.trace("Entering application...");
        LOGGER.info("Hello Log4j2...");
        int i = 0;
        while (i<20) {
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				LOGGER.error("Erreur de thread",e);
			}
        	i++;
		}
        LOGGER.error("Something is wrong with this code", new Exception("Invalid message"));

        LOGGER.trace("Exiting application...");
    }
}

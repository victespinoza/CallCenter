package ar.com.callcenter.log4j;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public class LoggerUtil {

	public static final String LOGGER_NAME = "callcenter";
	
	public static void initLogger() {
	    try {
	      String filePath = "./logs/callcenter.log";
	      PatternLayout layout = new PatternLayout("%-5p %d %m%n");
	      RollingFileAppender appender = new RollingFileAppender(layout, filePath);
	      appender.setName(LOGGER_NAME);
	      appender.setMaxFileSize("1MB");
	      appender.activateOptions();
	      Logger.getRootLogger().addAppender(appender);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}
}

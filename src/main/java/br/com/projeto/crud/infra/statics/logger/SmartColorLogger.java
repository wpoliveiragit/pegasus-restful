package br.com.projeto.crud.infra.statics.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SmartColorLogger extends SmartColorMethodLogger {

	private SmartLog infoLog;
	private SmartLog warnLog;
	private SmartLog errorLog;

	public SmartColorLogger(Class<?> clazz) {
		createLogs(clazz);

		String currentClassLog = getCurrentClassLog(clazz);
		logConfigHandler(infoLog.logger, infoConfig(currentClassLog));
		logConfigHandler(warnLog.logger, warnConfig(currentClassLog));
		logConfigHandler(errorLog.logger, erroConfig(currentClassLog));
	}

	private void createLogs(Class<?> clazz) {
		String nameClass = clazz.getCanonicalName();
		infoLog = new SmartLog(Logger.getLogger(nameClass + 1), Level.INFO);
		warnLog = new SmartLog(Logger.getLogger(nameClass + 2), Level.WARNING);
		errorLog = new SmartLog(Logger.getLogger(nameClass + 3), Level.SEVERE);
	}

	// ## INFO
	public void info(String message) {
		printLog(infoLog, message, EMPIT_ARRAY_OBJECT);
	}

	public void info(String message, Object... objects) {
		printLog(infoLog, message, objects);
	}

	// ## WARNING
	public void warn(String message) {
		printLog(warnLog, message, EMPIT_ARRAY_OBJECT);
	}

	public void warn(String message, Object... objects) {
		printLog(warnLog, message, objects);
	}

	// ## ERROR
	public void error(String message) {
		printLog(errorLog, message, EMPIT_ARRAY_OBJECT);
	}

	public void error(String message, Object... objects) {
		printLog(errorLog, message, objects);
	}

}

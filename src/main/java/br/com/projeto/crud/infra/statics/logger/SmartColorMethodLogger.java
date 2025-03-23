package br.com.projeto.crud.infra.statics.logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.projeto.crud.infra.statics.logger.type.ColorLogType;
import lombok.RequiredArgsConstructor;

public class SmartColorMethodLogger {

	protected static final String INFO_LABEL = "INFO";
	protected static final String WARN_LABEL = "WARN";
	protected static final String ERRO_LABEL = "ERRO";

	protected static final String SPLIT_PATH = "\\.";
	protected static final String PIPE_PATH = ".";

	protected static final Object[] EMPIT_ARRAY_OBJECT = new Object[0];

	public static void logConfigHandler(Logger logger, Formatter formatter) {
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setFormatter(formatter);
		logger.setUseParentHandlers(false);
		logger.addHandler(consoleHandler);
		logger.setLevel(Level.ALL);
	}

	public static String getCurrentClassLog(Class<?> clazz) {
		String simpleName = clazz.getSimpleName();
		return Stream.of(clazz.getCanonicalName().split(SPLIT_PATH))
				.map(name -> simpleName.equals(name) ? name : name.charAt(0) + PIPE_PATH)//
				.collect(Collectors.joining());//
	}

	public static Formatter erroConfig(String currentClassLog) {
		SmartColorMessageConfigLogger configLog = new SmartColorMessageConfigLogger(ERRO_LABEL, currentClassLog);
		configLog.resetMessageLog();
		configLog.setSymbleColor(ColorLogType.RED_BOLD);
		configLog.setLevelColor(ColorLogType.RED_BOLD);
		configLog.setLogMessageColor(ColorLogType.RED_BOLD);

		configLog.addSymble();
		configLog.addLevel();
		configLog.addDate();
		configLog.addCurrentClassLog();
		configLog.addElapsedTimeApp();
		configLog.addLogMessage();
		return configLog;
	}

	public static Formatter warnConfig(String currentClassLog) {
		SmartColorMessageConfigLogger configLog = new SmartColorMessageConfigLogger(WARN_LABEL, currentClassLog);
		configLog.resetMessageLog();
		configLog.setSymbleColor(ColorLogType.YELLOW_BOLD);
		configLog.setLevelColor(ColorLogType.YELLOW_BOLD);
		configLog.setLogMessageColor(ColorLogType.YELLOW_BOLD);

		configLog.addSymble();
		configLog.addLevel();
		configLog.addDate();
		configLog.addCurrentClassLog();
		configLog.addElapsedTimeApp();
		configLog.addLogMessage();
		return configLog;
	}

	public static Formatter infoConfig(String currentClassLog) {
		SmartColorMessageConfigLogger configLog = new SmartColorMessageConfigLogger(INFO_LABEL, currentClassLog);
		configLog.resetMessageLog();
		configLog.setSymbleColor(ColorLogType.GREEN_BOLD);
		configLog.setLevelColor(ColorLogType.GREEN_BOLD);
		configLog.setLogMessageColor(ColorLogType.GREEN_BOLD);

		configLog.addSymble();
		configLog.addLevel();
		configLog.addDate();
		configLog.addCurrentClassLog();
		configLog.addElapsedTimeApp();
		configLog.addLogMessage();
		return configLog;
	}

	// static
	public static void printLog(SmartLog smartLog, String message, Object... objects) {
		smartLog.logger.log(smartLog.level,
				(objects == null || objects.length == 0) ? message : String.format(message, objects));
	}

	@RequiredArgsConstructor
	public static class SmartLog {
		final Logger logger;
		final Level level;
	}

}

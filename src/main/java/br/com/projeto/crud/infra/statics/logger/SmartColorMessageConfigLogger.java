package br.com.projeto.crud.infra.statics.logger;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.stream.Collectors;

import br.com.projeto.crud.infra.statics.logger.type.ColorLogType;
import br.com.projeto.crud.infra.statics.logger.type.SmartColorSymbleLoggerType;
import lombok.Setter;

class SmartColorMessageConfigLogger extends Formatter {

	private static final long START_TIME_APP = System.currentTimeMillis();
	private static final String TIME_FORMAT = "%1$td/%1$tm/%1$tY %1$tH:%1$tM:%1$tS.%1$tL";
	private static final String TEXT_FORMAT = "[%s]";
	private static final String TEXT_FORMAT_MILI_SECONDS = "[%s ms]";
	private static final String TEXT_MESSAGE_FORMAT = ">>> %s";
	private static final String TXT_BLACK = "";
	private static final String TXT_SPACE = " ";

	private static final String L_BRACKET = "[";
	private static final String R_BRACKET = "]";

	private String nameLevel;
	private String currentClassLog;
	private @Setter SmartColorSymbleLoggerType symble;
	private @Setter ColorLogType symbleColor;
	private @Setter ColorLogType dateColor;
	private @Setter ColorLogType levelColor;
	private @Setter ColorLogType currentClassLogColor;
	private @Setter ColorLogType elapsedTimeAppColor;
	private @Setter ColorLogType logMessageColor;

	private List<String> patternList;

	public SmartColorMessageConfigLogger(String nameLevel, String currentClassLog) {
		this.nameLevel = nameLevel;
		this.currentClassLog = currentClassLog;
		resetMessageLog();
		addSymble();
		addLevel();
		addDate();
		addCurrentClassLog();
		addElapsedTimeApp();
		addLogMessage();
	}

	public SmartColorMessageConfigLogger resetMessageLog() {
		this.symble = SmartColorSymbleLoggerType.CIRCLE;
		this.symbleColor = ColorLogType.BLACK_BOLD;
		this.levelColor = ColorLogType.BLACK_BOLD;
		this.dateColor = ColorLogType.BLUE_BOLD;
		this.currentClassLogColor = ColorLogType.BLUE_BOLD;
		this.elapsedTimeAppColor = ColorLogType.BLUE_BOLD;
		this.logMessageColor = ColorLogType.BLACK_BOLD;
		patternList = new ArrayList<String>();
		return this;
	}

	public void addSymble() {
		patternList.add(createThread(symbleColor, symble.getCode()));
	}

	public void addDate() {
		patternList.add(createThread(dateColor, TEXT_FORMAT));
	}

	public void addLevel() {
		patternList.add(createThread(levelColor, L_BRACKET + nameLevel + R_BRACKET));
	}

	public void addCurrentClassLog() {
		patternList.add(createThread(currentClassLogColor, L_BRACKET + currentClassLog + R_BRACKET));
	}

	public void addElapsedTimeApp() {
		patternList.add(createThread(elapsedTimeAppColor, TEXT_FORMAT_MILI_SECONDS));
	}

	public void addLogMessage() {
		patternList.add(createThread(logMessageColor, TEXT_MESSAGE_FORMAT));
	}

	private static String createThread(ColorLogType color, String text) {
		return color.getCode() + text + ColorLogType.RESET.getCode();
	}

	@Override
	public String format(LogRecord record) {
		String pattern = patternList.stream().map(e -> e + TXT_SPACE).collect(Collectors.joining());
		return String.format(pattern + "\n", String.format(TIME_FORMAT, record.getMillis()),
				(System.currentTimeMillis() - START_TIME_APP) + TXT_BLACK, record.getMessage());
	}

}
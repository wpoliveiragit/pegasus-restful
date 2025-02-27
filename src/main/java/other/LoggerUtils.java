//package other;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import lombok.Getter;
//
//public class LoggerUtils {
//	// Como usar
//	// private static final LoggerUtils LOG =
//	// LoggerUtils.createLoggerSize30(LoggerUtils.class);
//
//	private Logger log;
//	private @Getter Class<?> clazz;
//
//	private LoggerUtils(String size, Class<?> clazz) {
//		this.clazz = clazz;
//		log = LoggerFactory.getLogger(String.format(size, clazz.getSimpleName()));
//	}
//
//	private LoggerUtils(Class<?> clazz) {
//		this.clazz = clazz;
//		log = LoggerFactory.getLogger(clazz.getSimpleName());
//	}
//
//	private LoggerUtils(String size, String classSimpleName) {
//		log = LoggerFactory.getLogger(String.format(size, clazz.getSimpleName()));
//	}
//
//	public static final LoggerUtils createLoggerSize30(Class<?> clazz) {
//		return new LoggerUtils("%-30s", clazz);
//	}
//
//	public static final LoggerUtils createLoggerSize(Class<?> clazz) {
//		return new LoggerUtils(clazz);
//	}
//
//	public void infoLoadingBean() {
//		log.info("-- LOADING BEAN >>> " + clazz.getSimpleName());
//	}
//
//	public void infoLoadingBean(String message) {
//		log.info("-- LOADING BEAN >>> " + clazz.getSimpleName() + " :: " + message);
//	}
//
//	public void infoCreateBean() {
//		log.info("-- CREATE BEAN >>> " + clazz.getSimpleName());
//	}
//
//	public void infoCreateBean(String message) {
//		log.info("-- CREATE BEAN >>> " + clazz.getSimpleName() + " :: " + message);
//	}
//
//	public void info(String format) {
//		log.info(format);
//	}
//
//	/** {} -##- {} */
//	public void infoFormatTwo(Object obj1, Object obj2) {
//		log.info("{} -::- {}", obj1, obj2);
//	}
//
//	public void info(String format, Object... arguments) {
//		log.info(format, arguments);
//	}
//
//}

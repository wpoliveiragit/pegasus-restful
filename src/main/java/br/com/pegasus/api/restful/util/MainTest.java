package br.com.pegasus.api.restful.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainTest {
    static Logger logger = LoggerFactory.getLogger(MainTest.class.getSimpleName());

    public static void main(String[] args) {

        logger.info("log-info");
        logger.info("log-info {}", "teste");
        x();

    }

    private static void x() {
        logger.warn("log-warning");
        y();
    }

    private static void y() {
        logger.error("log-error");
    }
}

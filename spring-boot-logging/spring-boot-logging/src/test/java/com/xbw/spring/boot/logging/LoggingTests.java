package com.xbw.spring.boot.logging;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * https://github.com/xbw/logs-in-one
 */
@SpringBootTest
public class LoggingTests {
    @Test
    void slf4j() {
        org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
        Assertions.assertEquals(ch.qos.logback.classic.Logger.class, logger.getClass());
        if (logger.isErrorEnabled()) {
            logger.error("{} error", org.slf4j.Logger.class.getName());
        }
        if (logger.isWarnEnabled()) {
            logger.warn("{} warn", org.slf4j.Logger.class.getName());
        }
        if (logger.isInfoEnabled()) {
            logger.info("{} info", org.slf4j.Logger.class.getName());
        }
        if (logger.isDebugEnabled()) {
            logger.debug("{} debug", org.slf4j.Logger.class.getName());
        }
        if (logger.isTraceEnabled()) {
            logger.trace("{} trace", org.slf4j.Logger.class.getName());
        }
    }

    @Test
    void log4j2() {
        org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(this.getClass());
        Assertions.assertEquals(org.apache.logging.slf4j.SLF4JLogger.class, logger.getClass());
        if (logger.isFatalEnabled()) {
            logger.fatal(org.apache.logging.log4j.Level.FATAL);
        }
        if (logger.isErrorEnabled()) {
            logger.error(org.apache.logging.log4j.Level.ERROR);
        }
        if (logger.isWarnEnabled()) {
            logger.warn(org.apache.logging.log4j.Level.WARN);
        }
        if (logger.isInfoEnabled()) {
            logger.info(org.apache.logging.log4j.Level.INFO);
        }
        if (logger.isDebugEnabled()) {
            logger.debug(org.apache.logging.log4j.Level.DEBUG);
        }
        if (logger.isTraceEnabled()) {
            logger.trace(org.apache.logging.log4j.Level.TRACE);
        }

        if (logger.isFatalEnabled()) {
            logger.log(org.apache.logging.log4j.Level.FATAL, org.apache.logging.log4j.Level.FATAL);
        }
        if (logger.isErrorEnabled()) {
            logger.log(org.apache.logging.log4j.Level.ERROR, org.apache.logging.log4j.Level.ERROR);
        }
        if (logger.isWarnEnabled()) {
            logger.log(org.apache.logging.log4j.Level.WARN, org.apache.logging.log4j.Level.WARN);
        }
        // Create and use a new custom level "DIAG".
        logger.log(org.apache.logging.log4j.Level.forName("DIAG", 350), org.apache.logging.log4j.Level.getLevel("DIAG"));
        if (logger.isInfoEnabled()) {
            logger.log(org.apache.logging.log4j.Level.INFO, org.apache.logging.log4j.Level.INFO);
        }
        if (logger.isDebugEnabled()) {
            logger.log(org.apache.logging.log4j.Level.DEBUG, org.apache.logging.log4j.Level.DEBUG);
        }
        // This creates the "VERBOSE" level if it does not exist yet.
        org.apache.logging.log4j.Level VERBOSE = org.apache.logging.log4j.Level.forName("VERBOSE", 550);
        logger.log(VERBOSE, VERBOSE); // use the custom VERBOSE level
        if (logger.isTraceEnabled()) {
            logger.log(org.apache.logging.log4j.Level.TRACE, org.apache.logging.log4j.Level.TRACE);
        }
        logger.log(org.apache.logging.log4j.Level.ALL, org.apache.logging.log4j.Level.ALL);
    }

    @Test
    void log4j() {
        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());
        Assertions.assertEquals(org.apache.log4j.Logger.class, logger.getClass());
        logger.fatal(org.apache.log4j.Level.FATAL);
        logger.error(org.apache.log4j.Level.ERROR);
        logger.warn(org.apache.log4j.Level.WARN);
        if (logger.isInfoEnabled()) {
            logger.info(org.apache.log4j.Level.INFO);
        }
        if (logger.isDebugEnabled()) {
            logger.debug(org.apache.log4j.Level.DEBUG);
        }
        if (logger.isTraceEnabled()) {
            logger.trace(org.apache.log4j.Level.TRACE);
        }
    }

    @Test
    void jcl() {
        org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass());
        Assertions.assertEquals("org.apache.commons.logging.LogAdapter$Slf4jLocationAwareLog", logger.getClass().getName());
        if (logger.isFatalEnabled()) {
            logger.fatal("fatal");
        }
        if (logger.isErrorEnabled()) {
            logger.error("error");
        }
        if (logger.isWarnEnabled()) {
            logger.warn("warn");
        }
        if (logger.isInfoEnabled()) {
            logger.info("info");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("debug");
        }
        if (logger.isTraceEnabled()) {
            logger.trace("trace");
        }
    }

    @Test
    void jul() {
        Logger logger = Logger.getLogger(this.getClass().getName());
        Assertions.assertEquals(java.util.logging.Logger.class, logger.getClass());
        logger.severe(Level.SEVERE.getName());
        logger.warning(Level.WARNING.getName());
        logger.info(Level.INFO.getName());
        logger.config(Level.CONFIG.getName());
        logger.fine(Level.FINE.getName());
        logger.finer(Level.FINER.getName());
        logger.finest(Level.FINEST.getName());

        logger.log(Level.SEVERE, Level.SEVERE.getName());
        logger.log(Level.WARNING, Level.WARNING.getName());
        logger.log(Level.INFO, Level.INFO.getName());
        logger.log(Level.CONFIG, Level.CONFIG.getName());
        logger.log(Level.FINE, Level.FINE.getName());
        logger.log(Level.FINER, Level.FINER.getName());
        logger.log(Level.FINEST, Level.FINEST.getName());
        logger.log(Level.ALL, Level.ALL.getName());
    }
}

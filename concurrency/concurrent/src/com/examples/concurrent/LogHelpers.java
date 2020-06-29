package com.examples.concurrent;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

public class LogHelpers {

    private static final String ARROW = " ---------> ";
    
    private LogHelpers() {}

    public static void info(final Logger logger, final String msg, final Object... args) {
        
        final var formatted = Thread.currentThread() + ARROW + String.format(msg, args);

        logger.info(formatted);
    }

    public static void error(final Logger logger, final String msg, final Object... args) {
        
        final var formatted = Thread.currentThread() + ARROW + String.format(msg, args);

        logger.severe(formatted);

    }

    public static void error(final Logger logger, final Throwable t) {
        
        final var writer = new StringWriter();
        try (var buffer = new PrintWriter(writer)) {

            t.printStackTrace(buffer);

            buffer.flush();

            final var formatted = Thread.currentThread() + ARROW + writer.toString();

            logger.severe(formatted);
        }
    }
}
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

public class LogHelpers {
    
    private LogHelpers() {}

    public static void info(final Logger logger, final String msg, final Object... args) {
        
        final var formatted = Thread.currentThread() + " --> " + String.format(msg, args);

        logger.info(formatted);
    }

    public static void error(final Logger logger, final String msg, final Object... args) {
        
        final var formatted = Thread.currentThread() + " --> " + String.format(msg, args);

        logger.severe(formatted);

    }

    public static void error(final Logger logger, final Throwable t) {
        
        final var writer = new StringWriter();
        try (var buffer = new PrintWriter(writer)) {

            t.printStackTrace(buffer);

            buffer.flush();

            final var formatted = Thread.currentThread() + " --> " + writer.toString();

            logger.severe(formatted);
        }
    }
}
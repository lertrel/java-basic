import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(10);

    public static Executor getTreadPool() {
        return EXECUTOR;
    }

    public static void main(String[] args) throws Exception {

        LogHelpers.info(LOGGER, "App.main() - starts");

        TaskList.getTasks()
                .stream()
                .forEach(r -> {
                    //Before executing
                    final long t1 = System.currentTimeMillis();
                    LogHelpers.info(LOGGER, "%s - starts at %s", r.toString(), t1);

                    r.run(LOGGER);

                    //After executed
                    final long t2 = System.currentTimeMillis();
                    LogHelpers.info(LOGGER, "%s - ends at %s", r.toString(), t2);
        
                    //Calcuated elaped time
                    final long took = t2 - t1;
                    LogHelpers.info(LOGGER, "%s - used %s ms", r.toString(), took);
                });

        LogHelpers.info(LOGGER, "App.main() - ends");
        
        EXECUTOR.shutdown();
    }
}

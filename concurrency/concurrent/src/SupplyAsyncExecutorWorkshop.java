import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SupplyAsyncExecutorWorkshop extends AbstractTask {

    public SupplyAsyncExecutorWorkshop(String name) {
        super(name);
    }

    public SupplyAsyncExecutorWorkshop() {
    }

    @Override
    protected void execute(Logger logger) throws TaskException {

        var one = CompletableFuture.supplyAsync(() -> {

            try {
                TimeUnit.SECONDS.sleep(2);
                LogHelpers.info(logger, "Async Executed by %s", getClass().getName());
            } catch (InterruptedException e) {
                LogHelpers.error(logger, e);
            }

            return "Hello, world!!! with Executor";
        }, App.getTreadPool());

        try {
            LogHelpers.info(logger, "Result = %s", one.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new TaskException(this, e);
        }
    }

}
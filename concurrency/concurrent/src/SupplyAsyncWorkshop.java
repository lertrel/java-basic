import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SupplyAsyncWorkshop extends AbstractTask {

    public SupplyAsyncWorkshop(String name) {
        super(name);
    }

    public SupplyAsyncWorkshop() {
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

            return "Hello, world!!!";
        });

        try {
            LogHelpers.info(logger, "Result = %s", one.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new TaskException(this, e);
        }
    }

}
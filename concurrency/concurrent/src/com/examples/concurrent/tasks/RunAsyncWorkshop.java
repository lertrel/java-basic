package com.examples.concurrent.tasks;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.examples.concurrent.AbstractTask;
import com.examples.concurrent.LogHelpers;
import com.examples.concurrent.TaskException;

public class RunAsyncWorkshop extends AbstractTask {

    public RunAsyncWorkshop(String name) {
        super(name);
    }

    public RunAsyncWorkshop() {
    }

    @Override
    protected void execute(Logger logger) throws TaskException {

        CompletableFuture<Void> one = CompletableFuture.runAsync(() -> {

            try {
                TimeUnit.SECONDS.sleep(2);
                LogHelpers.info(logger, "Async Executed by %s", getClass().getName());
            } catch (InterruptedException e) {
                LogHelpers.error(logger, e);
            }
        });

        try {
            one.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new TaskException(this, e);
        }
    }

}
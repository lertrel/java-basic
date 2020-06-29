package com.examples.concurrent.tasks;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import com.examples.concurrent.AbstractTask;
import com.examples.concurrent.LogHelpers;
import com.examples.concurrent.TaskException;

public class CompletablaFutureTimeoutWorkshop extends AbstractTask {

    public CompletablaFutureTimeoutWorkshop() {
        super();
    }
    
    public CompletablaFutureTimeoutWorkshop(String name) {
        super(name);
    }

    @Override
    protected void execute(Logger logger) throws TaskException {

        CompletableFuture<String> one = new CompletableFuture<>();

        try {
            one.get(2, TimeUnit.SECONDS);
        } catch (InterruptedException|ExecutionException e) {
            throw new TaskException(this, e);
        } catch (TimeoutException e) {
            LogHelpers.error(logger, e);
        }

    }

}
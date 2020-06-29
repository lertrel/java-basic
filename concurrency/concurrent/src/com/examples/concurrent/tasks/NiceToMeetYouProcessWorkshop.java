package com.examples.concurrent.tasks;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.examples.concurrent.AbstractTask;
import com.examples.concurrent.App;
import com.examples.concurrent.LogHelpers;
import com.examples.concurrent.TaskException;

public class NiceToMeetYouProcessWorkshop extends AbstractTask {

    public NiceToMeetYouProcessWorkshop(String name) {
        super(name);
    }

    public NiceToMeetYouProcessWorkshop() {
    }

    protected void waitFor(Logger logger, String action, long time) {

        try {
            TimeUnit.SECONDS.sleep(time);
            LogHelpers.info(logger, "Perform action %s inside %s", action, getClass().getSimpleName());
        } catch (InterruptedException e) {
            LogHelpers.error(logger, e);
        }

}

    @Override
    protected void execute(Logger logger) throws TaskException {

        var fork = App.getTreadPool();

        var getUserFirstName = CompletableFuture.supplyAsync(() -> {

            waitFor(logger, "getUserFirstName", 4);

            return "John";
        }, fork);

        var getUserLastName = CompletableFuture.supplyAsync(() -> {

            waitFor(logger, "getUserLastName", 3);

            return "Doe";        
        }, fork);

        var getUserFullname = getUserFirstName.thenCombineAsync(getUserLastName, (f, l) -> {

            waitFor(logger, "getUserFullname", 1);

            return f + " " + l;
        }, fork).thenApplyAsync(fullname -> {

            waitFor(logger, "upperCase", 1);

            return fullname.toUpperCase();

        }, fork);

        var greetUser = getUserFullname.thenAcceptAsync(userName -> {

            waitFor(logger, "greetUser", 1);

            LogHelpers.info(logger, "Nice to meet you %s", userName);            
        }, fork);

        try {
            greetUser.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new TaskException(this, e);
        }
    }

}
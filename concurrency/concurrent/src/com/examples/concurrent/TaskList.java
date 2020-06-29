package com.examples.concurrent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.examples.concurrent.tasks.CompletablaFutureTimeoutWorkshop;
import com.examples.concurrent.tasks.NiceToMeetYouProcessWorkshop;
import com.examples.concurrent.tasks.RunAsyncWorkshop;
import com.examples.concurrent.tasks.SupplyAsyncExecutorWorkshop;
import com.examples.concurrent.tasks.SupplyAsyncWorkshop;

public class TaskList {

    private static final List<Task> TASKS = new ArrayList<>();

    static {

        TASKS.add(new CompletablaFutureTimeoutWorkshop());
        TASKS.add(new RunAsyncWorkshop());
        TASKS.add(new SupplyAsyncWorkshop());
        TASKS.add(new SupplyAsyncExecutorWorkshop());
        TASKS.add(new NiceToMeetYouProcessWorkshop());
        

    }

    private TaskList() {}
    
    public static List<Task> getTasks() {

        return Collections.unmodifiableList(TASKS);
    }
}
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import java.util.logging.Logger;

public abstract class AbstractTask implements Task {

    private final String name;

    public AbstractTask(final String name) {
        this.name = name;
    }

    public AbstractTask() {
        this.name = null;
    }

    protected abstract void execute(Logger logger) throws TaskException;

    @Override
    public void run(final Logger logger) {
        
        try {
			execute(logger);
		} catch (TaskException e) {
            
            LogHelpers.error(logger, "Error found in %s", e.getTask());
            LogHelpers.error(logger, e);
            
		}

    }
    
    @Override
    public String getName() {
        return (null == name) ? getClass().getName(): name;
    }

    @Override
    public String toString() {
        return "AbstractTask [name=" + getName() + "]";
    }

}
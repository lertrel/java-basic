package com.examples.concurrent;

public class TaskException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final transient AbstractTask task;

    public TaskException(AbstractTask task, Throwable cause) {

        super(task.getName(), cause);

        this.task = task;
    }

    public AbstractTask getTask() {
        return task;
    }
}

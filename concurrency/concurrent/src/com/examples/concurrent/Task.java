package com.examples.concurrent;
import java.util.logging.Logger;

public interface Task {

    void run(final Logger logger);
    
    String getName();

}
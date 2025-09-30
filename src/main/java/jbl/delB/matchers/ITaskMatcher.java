package jbl.delB.matchers;

import jbl.delB.model.Task;

public interface ITaskMatcher {

    /**
     * Tests if the given task matches the condition
     * @param task the task to check
     * @return {@code true} if the task matches the condition,
     *         {@code false} otherwise
     */
    boolean match(Task task);
}

package jbl.delB.matchers;

import jbl.delB.enums.TaskPrio;
import jbl.delB.model.Task;

public class PrioMatcher implements ITaskMatcher {
    private TaskPrio prio;


    /**
     * Creates a new matcher for the given priority.
     *
     * @param prio the priority to match against
     */
    public PrioMatcher(TaskPrio prio) {
        this.prio = prio;
    }

    /**
     * Checks if the task has the same priority as this matcher.
     *
     * @param task the task to check
     * @return {@code true} if the task's priority equals the matcher's priority,
     * {@code false} otherwise
     */
    @Override
    public boolean match(Task task) {
        if (task.getPrio() == prio) {
            return true;
        }

        return false;
    }


}

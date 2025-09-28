package jbl.delB.matchers;

import jbl.delB.enums.TaskPrio;
import jbl.delB.model.Task;

public class PrioMatcher implements ITaskMatcher{
    private TaskPrio prio;

    public PrioMatcher(TaskPrio prio) {
        this.prio = prio;
    }


    @Override
    public boolean match(Task task){
        if(task.getPrio() == prio){
            return true;
        }

        return false;
    }



}

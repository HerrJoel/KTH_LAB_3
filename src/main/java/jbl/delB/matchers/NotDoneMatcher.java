package jbl.delB.matchers;

import jbl.delB.enums.TaskState;
import jbl.delB.model.Task;

public class NotDoneMatcher implements ITaskMatcher {

    /**
     * Checks if the given task is not done.
     * @param task the task to check
     * @return {@code true} if the task's state is not DONE, {@code false} otherwise
     */
    @Override
   public boolean match(Task task){
       if (task.getState() != TaskState.DONE){
           return true;
       }else{
           return false;}
   }
}

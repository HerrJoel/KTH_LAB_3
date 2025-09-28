package jbl.delB.matchers;

import jbl.delB.enums.TaskState;
import jbl.delB.model.Task;

public class NotDoneMatcher implements ITaskMatcher {


    @Override
   public boolean match(Task task){
       if (task.getState() != TaskState.DONE){
           return true;
       }else{
           return false;}
   }
}

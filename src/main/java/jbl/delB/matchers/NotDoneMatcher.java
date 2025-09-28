package jbl.delB.matchers;

import jbl.delB.model.Task;

public class NotDoneMatcher implements ITaskMatcher {
   public boolean match(Task task){
       if (task.getState() != TaskState.DONE){
           return true;
       }else{
           return false;}
   }
}

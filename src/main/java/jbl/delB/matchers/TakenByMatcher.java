package jbl.delB.matchers;

import jbl.delB.model.Task;

public class TakenByMatcher implements ITaskMatcher {
    private String takenBy;

    public TakenByMatcher(String takenBy) {
        this.takenBy = takenBy;
    }



     /** Checks if the task is taken by the user
     * @param task the task to check
     * @return {@code true} if the task is taken by the user,
     *         {@code false} otherwise
     */
    @Override
    public boolean match(Task task){
        if(task.getTakenBy() == null){
            return false;
        }

        if(task.getTakenBy().equals(takenBy)){
            return true;
        }

        return false;
    }

}

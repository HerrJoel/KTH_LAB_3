package jbl.delB.model;

import jbl.delB.enums.TaskPrio;
import jbl.delB.enums.TaskState;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Comparable<Task>, Serializable {

    public String description;
    public int id;
    public String takenBy;
    public TaskState state;
    public LocalDate lastUpdate;
    public TaskPrio prio;


    Task(String descr, TaskPrio prio, int id){
        this.description=descr;
        this.prio=prio;
        this.id=id;

        this.lastUpdate= LocalDate.now();
        this.state=TaskState.TO_DO;
    }

    public void setTakenBy(String takenBy) {
        if (this.takenBy != null) {
            throw new IllegalStateException("Task has been taken by: " + this.takenBy);
        }

        this.takenBy = takenBy;
        this.lastUpdate = LocalDate.now();
    }

    public void setState(TaskState state) {
        this.state = state;
        this.lastUpdate = LocalDate.now();
    }
    public void setPrio(TaskPrio prio) {
        this.prio = prio;
        this.lastUpdate = LocalDate.now();
    }


    @Override
    public int compareTo(Task o) {
        return 0;
    }

    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", takenBy='" + takenBy + '\'' +
                ", state=" + state +
                ", lastUpdate=" + lastUpdate +
                ", prio=" + prio +
                '}';
    }
}

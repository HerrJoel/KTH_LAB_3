package jbl.delB.model;

import jbl.delB.enums.TaskPrio;
import jbl.delB.enums.TaskState;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a task in the project management system.
 * A task has a description, an ID, a priority, and a state.
 * It can optionally be taken by a user and keeps track of its last update date.
 *
 * Implements {@link Comparable} to allow sorting by priority first,
 * and description alphabetically when priorities are equal.
 *
 * Implements {@link Serializable} so tasks can be serialized and stored.
 */
public class Task implements Comparable<Task>, Serializable {


    private final String description;

    private final int id;

    private String takenBy;

    private TaskState state;

    private LocalDate lastUpdate;

    private TaskPrio prio;



    /**
     * Constructs a new Task with the given description, priority, and ID.
     * The task is created with default state {@link TaskState#TO_DO} and
     * the lastUpdate set to the current date.
     *
     * @param descr short description of the task
     * @param prio  initial priority
     * @param id    unique identifier
     */
    Task(String descr, TaskPrio prio, int id) {
        this.description = descr;
        this.prio = prio;
        this.id = id;

        this.lastUpdate = LocalDate.now();
        this.state = TaskState.TO_DO;
    }

    /** @return the description of the task */
    public String getDescription() {
        return description;
    }

    /** @return the task ID */
    public int getId() {
        return id;
    }

    /** @return the user who has taken this task, or null if none */
    public String getTakenBy() {
        return takenBy;
    }

    /** @return the current state of the task */
    public TaskState getState() {
        return state;
    }

    /** @return the date of the last update */
    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    /** @return the priority of the task */
    public TaskPrio getPrio() {
        return prio;
    }

    /**
     * Assigns the task to a user. Can only be done once.
     *
     * @param takenBy the name of the user
     * @throws IllegalStateException if the task is already taken
     */
    public void setTakenBy(String takenBy) {
        if (this.takenBy != null) {
            throw new IllegalStateException("Task has been taken by: " + this.takenBy);
        }
        this.takenBy = takenBy;
        this.lastUpdate = LocalDate.now();
    }

    /**
     * Updates the state of the task and refreshes {@code lastUpdate}.
     *
     * @param state the new state
     */
    public void setState(TaskState state) {
        this.state = state;
        this.lastUpdate = LocalDate.now();
    }

    /**
     * Updates the priority of the task and refreshes {@code lastUpdate}.
     *
     * @param prio the new priority
     */
    public void setPrio(TaskPrio prio) {
        this.prio = prio;
        this.lastUpdate = LocalDate.now();
    }

    /**
     * Compares this task with another task.
     * Tasks are ordered by priority first, then by description alphabetically.
     *
     * @param other the other task to compare with
     * @return a negative integer, zero, or a positive integer as this task
     *         is less than, equal to, or greater than the specified task
     */
    @Override
    public int compareTo(Task other) {
        int prioCompare = this.prio.compareTo(other.prio);
        if (prioCompare < 0) {
            return -1;
        } else if (prioCompare > 0) {
            return 1;
        } else {
            return this.description.compareTo(other.description);
        }
    }

    /**
     * Checks if this task is equal to another object.
     * Two tasks are considered equal if they have the same priority and description.
     *
     * @param object the object to compare with
     * @return true if the object is a Task with the same prio and description, false otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Task other)) {
            return false;
        }
        return this.prio == other.prio &&
                this.description.equals(other.description);
    }

    /**
     * Returns a string representation of the task including all its fields.
     *
     * @return a string with task details
     */
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

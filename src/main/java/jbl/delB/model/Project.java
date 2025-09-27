package jbl.delB.model;

import jbl.delB.enums.ProjectState;
import jbl.delB.enums.TaskPrio;
import jbl.delB.enums.TaskState;
import jbl.delB.matchers.ITaskMatcher;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project implements Comparable<Project>, Serializable {
    private String title;
    private int id;
    private String description;
    private LocalDate createdDate;
    private int nextTaskId;
    private List<Task> tasks = new ArrayList<>();

    Project(String title, int id, String description) {
        this.title = title;
        this.id = id;
        this.description = description;
        this.createdDate = LocalDate.now();
        this.nextTaskId = 1;
    }

    public Task addTask(String desc, TaskPrio pr) {
        Task createTask = new Task(desc, pr, nextTaskId);
        tasks.add(createTask);
        nextTaskId++;


        return createTask;
    }


    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getCreated() {
        return createdDate;
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public int getNextTaskId() {
        return nextTaskId;
    }

    public LocalDate getLastUpdated(){
        return null;
    }



    @Override
    public int compareTo(Project other) {
        return this.title.compareTo(other.title);
    }

    @Override
    public String toString() {
        return "Project{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                ", nextTaskId=" + nextTaskId +
                ", tasks=" + tasks +
                '}';
    }

    public Task getTaskById(int idFinder) {
        for(Task s : tasks){
            if(s.getId() == idFinder){
                return s;
            }

        }
        return null;
    }

    public boolean removeTask(Task task) {

        return tasks.remove(task);
    }

    public ProjectState getProjectState() {
        boolean checker = true;

        if (tasks.isEmpty()) {
            return ProjectState.EMPTY;
        }
        for (Task r : tasks) {
            if (r.getState() != TaskState.DONE) {
                checker = false;
                break;
            }
        }
        if (!checker) {
            return ProjectState.ONGOING;
        } else {
            return ProjectState.COMPLETED;
        }
    }


}

package jbl.delB.model;

import jbl.delB.enums.ProjectState;
import jbl.delB.enums.TaskPrio;
import jbl.delB.enums.TaskState;
import jbl.delB.matchers.ITaskMatcher;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;





/**
 * Represents a project that contains tasks.
 *
 * A project has a title, id, description and a creation date.
 * Tasks can be added, removed and searched using design strategy with matchers.
 *
 * Implements {@link Comparable} to allow sorting by project title  through compareTo and equals,
 * and {@link Serializable} to allow saving/loading from file.
 *
 * @author Joel B. Lagerqvist
 * @version 1.0
 */
public class Project implements Comparable<Project>, Serializable {
    private final String title;
    private final int id;
    private final String description;
    private final LocalDate createdDate;
    private int nextTaskId;
    private final List<Task> tasks = new ArrayList<>();


    /**
     * Created  project requires a given title, id and description.
     * The created date is set to the current date through LocalDate.
     *
     * @param title the project title
     * @param id the project id
     * @param description description of the project
     */
    // package-private (kan användas av alla klasser i samma paket (model) inte utanför)
    Project(String title, int id, String description) {
        this.title = title;
        this.id = id;
        this.description = description;
        this.createdDate = LocalDate.now();
        this.nextTaskId = 1;
    }




/** Gets the project title
 * @return title of project*/
    public String getTitle() {
        return title;
    }

    /** Gets the project Id
     * @return Id of project*/
    public int getId() {
        return id;
    }

    /** Gets the project Description
     * @return description of project */
    public String getDescription() {
        return description;
    }

    /** Gets the project date of creation
     * @return date of project creation*/
    public LocalDate getCreated() {
        return createdDate;
    }


    /** Gets the project tasks
     * @return  copied list of all tasks*/
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }


    /** Gets the upcoming project id*/
    public int getNextTaskId() {
        return nextTaskId;
    }


    /** Adds task to project by receiving argument of description and title. Adding
     *  private class variable nextTaskId.
     * @param desc description of new task
     * @param pr priority of new task
     * @return new task
     * */
    // Skapar Task utifrån konstruktor inuti Task
    public Task addTask(String desc, TaskPrio pr) {
        Task createTask = new Task(desc, pr, nextTaskId);
        tasks.add(createTask);
        nextTaskId++;


        return createTask;
    }


    /** Finding latest update among all tasks in project.
     * @return created task if no task exists within project
     *         latest update of tasks within project
     * */
    // Undersöker när senaste LocalDate som ett projekt har redigerats
    public LocalDate getLastUpdated(){
        if (tasks.isEmpty()) {
            return createdDate;
        }
        LocalDate lastDate = createdDate;
        for(Task t : tasks){
            if(t.getLastUpdate().isAfter(lastDate)){ //isAfter jämför 2 LocalDate, om task.getLastUpdated() (som finns i Task.java) är ett senare datum än lastDate --> return true.
                lastDate = t.getLastUpdate();
            }
        }

        return lastDate;
    }
    /** Finding task though id
     * @param idFinder id of searched task
     * @return null if no tasks match id
     *         task owener of given id
     * */

    // Skickar in Id och tittar igenom samtliga tasks för att hitta matchande Id
    public Task getTaskById(int idFinder) {
        for(Task s : tasks){
            if(s.getId() == idFinder){
                return s;
            }

        }
        return null;
    }

    /** Looking thought projects task status.
     * if task found incomplete
     * @return ProjectState.EMPTY: if no tasks exists
     *         ProjectState.ONGOING: if task found with incomplete status
     *         ProjectState.COMPLETED: if all tasks are complete
     * */
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




    /**
     * Compares this project to another by title in alphabetic order.
     *
     * @param other the project being compared to
     * @return a negative number if this title comes before,
     *         zero if equal,
     *         or a positive number if after
     */
// KRÄVS AV Comparable<Project>
    // Sorterar enligt titel alfabetisk ordning
    //
    @Override
    public int compareTo(Project other) {
        return this.title.compareTo(other.title); // jämför titlarna, om lika --> return 0, om this.title kommer för alfabetiskt --> return < 0, annars return > 0
    }



    /**
     * Checks if two projects have same titles. Makes sure it's a project object with (project).
     *
     * @param o the object to compare with
     * @return true if the titles are the same or reference points at same place in the memory
     *         false if it's not an object of probject or the titles don't match
     */
    // Är titlarna lika?
    //Används i projectManager isTitleUnique
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Jämför referenserna (namnen på objekten) om de pekar på samma minne. this representerar objektet som vi skickar anroppet med, o repreenterar det vi skickar med i anroppet) --> this.(o)
        if(!(o instanceof Project)) return false; // Om o inte är ett Project objekt --> return false
        Project oth = (Project) o; // garanterar att o är ett Project objekt och kopierar över det till other

        return this.title.equals(oth.title); // jämför titlarna, om lika--> return true

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




    /**
     * Removes the given task from the project.
     *
     * @param task the task to remove
     * @return true if the task was removed
     *         false if not found
     */
    public boolean removeTask(Task task) {

        return tasks.remove(task);
    }


    /**
     * Finds all tasks in this project that match the given matcher object from the design strategy.
     * The result is sorted according to the natural order of Task created in class compareTo.
     *
     * @param matcher the matcher object used to filter tasks
     * @return a sorted list of matching tasks
     */
// Fyller Listan matchFinder med de Tasks som matchas i projekt som undersöks utifrån ITaskMatcher.
    public List<Task> findTasks(ITaskMatcher matcher){
        List<Task> matchFinder = new ArrayList<>();
        for (Task m : tasks) {
            if (matcher.match(m)) {

                matchFinder.add(m);
            }
        }

        matchFinder.sort(null); //Sorterar utifrån compareTo inuti Task.  1st prio --> 2nd descript

        return matchFinder;

    }



}

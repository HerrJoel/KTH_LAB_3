package jbl.delB.model;

import jbl.delB.enums.ProjectState;
import jbl.delB.enums.TaskPrio;
import jbl.delB.enums.TaskState;
import jbl.delB.matchers.ITaskMatcher;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


// Comparable<Project> gör det möjligt att jämföra projekt med andra projekt (sortera titel i compareTo)
// Serializable gör möjligt att läsa textfiler till projekt
public class Project implements Comparable<Project>, Serializable {
    private String title;
    private int id;
    private String description;
    private LocalDate createdDate;
    private int nextTaskId;
    private List<Task> tasks = new ArrayList<>();

    // package-private (kan användas av alla klasser i samma paket (model) inte utanför)
    Project(String title, int id, String description) {
        this.title = title;
        this.id = id;
        this.description = description;
        this.createdDate = LocalDate.now();
        this.nextTaskId = 1;
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



    // Skapar Task utifrån konstruktor inuti Task
    public Task addTask(String desc, TaskPrio pr) {
        Task createTask = new Task(desc, pr, nextTaskId);
        tasks.add(createTask);
        nextTaskId++;


        return createTask;
    }




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


    // Skickar in Id och tittar igenom samtliga tasks för att hitta matchande Id
    public Task getTaskById(int idFinder) {
        for(Task s : tasks){
            if(s.getId() == idFinder){
                return s;
            }

        }
        return null;
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


// KRÄVS AV Comparable<Project>
    // Sorterar enligt titel alfabetisk ordning
    //
    @Override
    public int compareTo(Project other) {
        return this.title.compareTo(other.title); // jämför titlarna, om lika --> return 0, om this.title kommer för alfabetiskt --> return < 0, annars return > 0
    }


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



    public boolean removeTask(Task task) {

        return tasks.remove(task);
    }



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

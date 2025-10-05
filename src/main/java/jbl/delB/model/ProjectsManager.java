package jbl.delB.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jbl.delB.exceptions.TitleNotUniqueException;

/**
 * Represents a project manager over all created projects.
 * The project manager is capable of sorting, removing and adding new projects with title, description and a unique id
 * and storing them in a list.
 *
 * @author Joel B. Lagerqvist
 *
 */


public class ProjectsManager {
    private int nextProjectId;


    private final List<Project> projects = new ArrayList<>();

    public ProjectsManager() {
        this.nextProjectId = 1;

    }

    /**
     Gets next project id.
     @return the next upcoming project id.
     */
    public int getNextProjectId() {
        return nextProjectId;
    }
    /**
     Gets list of all projects.
     @return list of all projects.
     */
    public List<Project> getProjects() {
        return new ArrayList<>(projects);
    }
    /**
     Adds new project to the project list.
     Checking if title is unique.
     Adding up nextProjectId for next project.
     @param title the title for the new project.
     @param descr the description of the new project.
     @throws TitleNotUniqueException if wished title is not unique.
     @return the newly created project.
     */
    public Project addProject(String title, String descr){

        if(!isTitleUnique(title)){
            throw new TitleNotUniqueException("Titel '" + title + "' already exist.");

    }

        Project newProject = new Project(title, nextProjectId, descr);
        projects.add(newProject);

        nextProjectId++;

        return newProject;
}
    /**
     Checks for unique titles within project list.
     compares the given title with all existing project titles.
     @return if match is not found, return true.
             if match is found, return false.
     */
public boolean isTitleUnique(String title){
        Project titleTry = new Project(title, -1, "");
    for(Project p :  projects){
        if(p.equals(titleTry)) {
            return false;
        }
        }

    return true;
}
    /**
     Clears all existing projects within list.
     Copies over received list into newly emptied list.
     Updates nextProjectId to be the highest ID in the new list + 1 to prepare for next created project.
     @param newList new list to replace old list.
     */
public void setProjects(List<Project> newList){
    projects.clear();
    projects.addAll(newList);


        nextProjectId = getHighestId() + 1;
}

    /**
     Removes project from list.
     @param r project to be removed.
     */
public void removeProject(Project r){
        projects.remove(r);
}

    /**
     Finds project through its id.
     Looking through all projects to find matching id.
     @param id of project of interest.
     @return if match is found, return project.
             if not return null.

     */
public Project getProjectById(int id){
        for(Project p : projects){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
}

    /**
     Looks through project list to find the highest id number.
     If higher number is found, set return param to that number.
     @return highest id number which was found.
     */
public int getHighestId(){
    int newId = 0;
    for(Project p : projects){
        if(p.getId() > newId){
            newId = p.getId();
        }
    }

    return newId;
}
    /**
     Looks for project titles through title String.
     Creates list to add all matching titles.
     If matching title is found, it's added to the list.
     @param titleStr String to compare existing project titles to.
     @return list of all matching titles.
     */
public List<Project> findProjects(String titleStr){
        List<Project> finds = new ArrayList<>();
        for(Project p : projects){
            if(p.getTitle().equalsIgnoreCase(titleStr)){
                finds.add(p);
            }
        }
        return finds;
}

}

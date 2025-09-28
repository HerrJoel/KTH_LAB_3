package jbl.delB.model;

import java.util.ArrayList;
import java.util.List;
import jbl.delB.exceptions.TitleNotUniqueException;



public class ProjectsManager {
    private int nextProjectId;


    private List<Project> projects = new ArrayList<>();

    public ProjectsManager() {
        this.nextProjectId = 1;

    }

    public int getNextProjectId() {
        return nextProjectId;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public Project addProject(String title, String descr){

        if(!isTitleUnique(title)){
            throw new TitleNotUniqueException("Titel '" + title + "' already exist.");

    }

        Project newProject = new Project(title, nextProjectId, descr);
        projects.add(newProject);

        nextProjectId++;

        return newProject;
}

public boolean isTitleUnique(String title){
        Project titleTry = new Project(title, -1, "");
    for(Project p :  projects){
        if(p.equals(titleTry)) {
            return false;
        }
        }

    return true;


}

public void setProjects(List<Project> newList){
    projects.clear();
    projects.addAll(newList);

    //anpassar nextProjectId för nästa Project som ska läggas till (+1)
        nextProjectId = getHighestId() + 1;
}


public void removeProject(Project r){
        projects.remove(r);
}


public Project getProjectById(int id){
        for(Project p : projects){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
}


public int getHighestId(){
    int newId = 0;
    for(Project p : projects){
        if(p.getId() > newId){
            newId = p.getId();
        }
    }

    return newId;
}

public List<Project> findProjects(String titleStr){
        List<Project> finds = new ArrayList<>();
        for(Project p : projects){
            if(p.getTitle().equalsIgnoreCase(titleStr)){ //Kollar efter liknande strängar i listan med equalsIgnoreCase
                finds.add(p);
            }
        }

        return finds;
}

}

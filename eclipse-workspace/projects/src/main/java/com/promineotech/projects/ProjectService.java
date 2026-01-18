package com.promineotech.projects;

public class ProjectService {

    private ProjectDao projectDao = new ProjectDao();

    // CREATE
    public Project addProject(Project project) {
        return projectDao.insertProject(project);
    }

    // READ (by ID)
    public Project fetchProjectById(int projectId) {
        Project project = projectDao.fetchProjectById(projectId);
        if (project == null) {
            throw new DbException("Project with ID " + projectId + " does not exist.");
        }
        return project;
    }

    // UPDATE
    public void modifyProjectDetails(Project project) {
        boolean success = projectDao.modifyProjectDetails(project);
        if (!success) {
            throw new DbException("Unable to update project with ID " + project.getProjectId());
        }
    }

    // DELETE  
    public boolean deleteProject(Integer projectId) {
        return projectDao.deleteProject(projectId);
    }
}
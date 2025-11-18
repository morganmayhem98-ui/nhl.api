package com.promineotech.projects;

import java.math.BigDecimal;
import java.util.Scanner;


public class ProjectsApp {

    // Scanner for reading user input from console
    private Scanner scanner = new Scanner(System.in);
    
    // Service layer handles business logic and talks to DAO
    private ProjectService projectService = new ProjectService();
    
    // Holds the project the user is currently working on
    private Project curProject;

    // Entry point of the application
    public static void main(String[] args) {
        new ProjectsApp().start();
    }

    // Main application loop — runs until user exits
    private void start() {
        while (true) {
            printMenu();
            int selection = getIntInput("Enter a menu selection: ");

            switch (selection) {
                case 1: createProject(); break;           // Add new project
                case 2: selectProject(); break;           // Load existing project by ID
                case 3: printCurrentProject(); break;     // Display current project details
                case 4: updateProjectDetails(); break;    // Modify current project
                case 5: deleteCurrentProject(); break;    // Permanently remove project
                case 0: exitApp();                        // Exit program
                default: System.out.println("Invalid selection. Try again.");
            }
        }
    }

    // Displays the main menu
    private void printMenu() {
        System.out.println("\n==== Project Management ====");
        System.out.println("1) Create project");
        System.out.println("2) Select project");
        System.out.println("3) Print current project");
        System.out.println("4) Update project details");
        System.out.println("5) Delete current project");
        System.out.println("0) Exit");
    }

    // Option 5: Permanently delete the current project 
    private void deleteCurrentProject() {
        if (curProject == null) {
            System.out.println("No project is currently selected.");
            return;
        }

        // Show user exactly what they're about to delete
        System.out.println("\nWARNING: You are about to permanently delete this project:");
        System.out.println("ID: " + curProject.getProjectId());
        System.out.println("Name: " + curProject.getProjectName());
        System.out.println("Estimated hours: " + curProject.getEstimatedHours());
        System.out.println("This cannot be undone!");

        // Force user to type 'DELETE' to confirm
        String confirm = getStringInput("Type 'DELETE' to confirm: ");
        if (!"DELETE".equalsIgnoreCase(confirm.trim())) {
            System.out.println("Delete cancelled. Project is safe.");
            return;
        }

        // Call service layer to delete from database
        boolean deleted = projectService.deleteProject(curProject.getProjectId());
        if (deleted) {
            System.out.println("Project ID " + curProject.getProjectId() + " has been permanently deleted.");
            curProject = null; // Clear current project from memory
        } else {
            System.out.println("Delete failed. Project may already be gone.");
        }
    }

    // Option 1: Create a brand new project
    private void createProject() {
        String name = getStringInput("Project name: ");
        BigDecimal est = getDecimalInput("Estimated hours: ");
        BigDecimal act = getDecimalInput("Actual hours: ");
        Integer diff = getIntInput("Difficulty (1-5): ");
        String notes = getStringInput("Notes: ");

        // Build a new Project object
        Project project = new Project();
        project.setProjectName(name);
        project.setEstimatedHours(est);
        project.setActualHours(act);
        project.setDifficulty(diff);
        project.setNotes(notes);

        // Save to database via service → DAO → MySQL
        projectService.addProject(project);
        System.out.println("Project created with ID: " + project.getProjectId());
    }

    // Option 2: Load a project from database by its ID
    private void selectProject() {
        int id = getIntInput("Enter project ID: ");
        curProject = projectService.fetchProjectById(id);
        if (curProject != null) {
            System.out.println("Current project set to: " + curProject.getProjectName());
        } else {
            System.out.println("No project found with ID: " + id);
        }
    }

    // Option 3: Display all details of the current project
    private void printCurrentProject() {
        if (curProject == null) {
            System.out.println("No project selected.");
            return;
        }
        System.out.println("\nCurrent Project:");
        System.out.println("ID: " + curProject.getProjectId());
        System.out.println("Name: " + curProject.getProjectName());
        System.out.println("Estimated hours: " + curProject.getEstimatedHours());
        System.out.println("Actual hours: " + curProject.getActualHours());
        System.out.println("Difficulty: " + curProject.getDifficulty());
        System.out.println("Notes: " + (curProject.getNotes() != null ? curProject.getNotes() : "(none)"));
    }

    // Option 4: Update any field of the current project (press Enter to keep value)
    private void updateProjectDetails() {
        if (curProject == null) {
            System.out.println("Please select a project first.");
            return;
        }
        System.out.println("Press ENTER to keep current values.\n");

        String name = getStringInput("Project name [" + curProject.getProjectName() + "]: ");
        BigDecimal est = getDecimalInput("Estimated hours [" + curProject.getEstimatedHours() + "]: ");
        BigDecimal act = getDecimalInput("Actual hours [" + curProject.getActualHours() + "]: ");
        Integer diff = getIntInput("Difficulty (1-5) [" + curProject.getDifficulty() + "]: ");
        String notes = getStringInput("Notes [" + (curProject.getNotes() != null ? curProject.getNotes() : "(none)") + "]: ");

        // Create updated version
        Project updated = new Project();
        updated.setProjectId(curProject.getProjectId());
        updated.setProjectName(name.isBlank() ? curProject.getProjectName() : name);
        updated.setEstimatedHours(est == null ? curProject.getEstimatedHours() : est);
        updated.setActualHours(act == null ? curProject.getActualHours() : act);
        updated.setDifficulty(diff == null ? curProject.getDifficulty() : diff);
        updated.setNotes(notes.isBlank() ? curProject.getNotes() : notes);

        // Save changes and refresh current project
        projectService.modifyProjectDetails(updated);
        curProject = projectService.fetchProjectById(updated.getProjectId());
        System.out.println("Project updated successfully.");
    }

    // Option 0: exit the application
    private void exitApp() {
        System.out.println("Goodbye!");
        scanner.close();  // Release system resource
        System.exit(0);
    }

    // Helper: Get string input from user
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Helper: Get integer with validation and allow blank 
    private Integer getIntInput(String prompt) {
        while (true) {
            String input = getStringInput(prompt);
            if (input.isBlank()) return null;
            try {
                return Integer.valueOf(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again or leave blank.");
            }
        }
    }

    // Helper: Get decimal number with validation and allow blank
    private BigDecimal getDecimalInput(String prompt) {
        while (true) {
            String input = getStringInput(prompt);
            if (input.isBlank()) return null;
            try {
                return new BigDecimal(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid decimal number (e.g., 12.5). Try again or leave blank.");
            }
        }
    }
}

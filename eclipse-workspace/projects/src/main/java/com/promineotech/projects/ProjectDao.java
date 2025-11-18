package com.promineotech.projects;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProjectDao {

    private static final String INSERT_PROJECT_SQL =
            "INSERT INTO project (project_name, estimated_hours, actual_hours, difficulty, notes) "
          + "VALUES (?, ?, ?, ?, ?)";

    // CREATE
    public Project insertProject(Project project) {
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_PROJECT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, project.getProjectName());
            stmt.setBigDecimal(2, project.getEstimatedHours());
            stmt.setBigDecimal(3, project.getActualHours());
            stmt.setObject(4, project.getDifficulty());
            stmt.setString(5, project.getNotes());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    project.setProjectId(rs.getInt(1));
                    return project;
                } else {
                    throw new DbException("Unable to retrieve auto-generated project ID.");
                }
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    // READ by ID
    public Project fetchProjectById(int projectId) {
        String sql = "SELECT * FROM project WHERE project_id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, projectId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Project project = new Project();
                    project.setProjectId(rs.getInt("project_id"));
                    project.setProjectName(rs.getString("project_name"));
                    project.setEstimatedHours(rs.getBigDecimal("estimated_hours"));
                    project.setActualHours(rs.getBigDecimal("actual_hours"));
                    project.setDifficulty(rs.getInt("difficulty"));
                    project.setNotes(rs.getString("notes"));
                    return project;
                }
                return null;
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    // UPDATE
    public boolean modifyProjectDetails(Project project) {
        String sql = """
                UPDATE project
                SET project_name = ?,
                    estimated_hours = ?,
                    actual_hours = ?,
                    difficulty = ?,
                    notes = ?
                WHERE project_id = ?
                """;

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, project.getProjectName());
            stmt.setBigDecimal(2, project.getEstimatedHours());
            stmt.setBigDecimal(3, project.getActualHours());
            stmt.setObject(4, project.getDifficulty());
            stmt.setString(5, project.getNotes());
            stmt.setInt(6, project.getProjectId());

            return stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    // DELETE 
    public boolean deleteProject(Integer projectId) {
        String sql = "DELETE FROM project WHERE project_id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, projectId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DbException("Failed to delete project ID: " + projectId, e);
        }
    }
}

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlacementSystem {

    // Register student
    public boolean registerStudent(String studentId, String name, String department, int year) {
        try (Connection conn = MySQLDatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO students(student_id, name, department, year) VALUES(?, ?, ?, ?)")) {
            ps.setString(1, studentId);
            ps.setString(2, name);
            ps.setString(3, department);
            ps.setInt(4, year);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false; // Student already exists
        }
    }

    // Register company
    public boolean registerCompany(String companyId, String name, String role, String eligibility, int openings) {
        try (Connection conn = MySQLDatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO companies(company_id, name, role, eligibility_criteria, openings) VALUES(?, ?, ?, ?, ?)")) {
            ps.setString(1, companyId);
            ps.setString(2, name);
            ps.setString(3, role);
            ps.setString(4, eligibility);
            ps.setInt(5, openings);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false; // Company already exists
        }
    }

    // Apply for company
    public boolean applyForCompany(String studentId, String companyId) {
        try (Connection conn = MySQLDatabaseManager.getConnection()) {

            // Check student exists
            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM students WHERE student_id = ?")) {
                ps.setString(1, studentId);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) return false;
            }

            // Check company exists
            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM companies WHERE company_id = ?")) {
                ps.setString(1, companyId);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) return false;
            }

            // Insert application
            try (PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO applications(student_id, company_id, application_date, status) VALUES(?, ?, ?, 'applied')")) {
                ps.setString(1, studentId);
                ps.setString(2, companyId);
                ps.setLong(3, System.currentTimeMillis());
                ps.executeUpdate();
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Mark student as selected
    public boolean selectStudent(String studentId, String companyId) {
        try (Connection conn = MySQLDatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE applications SET status = 'selected' WHERE student_id = ? AND company_id = ?")) {
            ps.setString(1, studentId);
            ps.setString(2, companyId);
            int updated = ps.executeUpdate();
            return updated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // View student applications
    public List<String> viewApplications(String studentId) {
        List<String> appliedCompanies = new ArrayList<>();
        try (Connection conn = MySQLDatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT company_id, status FROM applications WHERE student_id = ?")) {
            ps.setString(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                appliedCompanies.add(rs.getString("company_id") + " : " + rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appliedCompanies;
    }

    // View students selected for a company
    public List<String> viewSelectedStudents(String companyId) {
        List<String> selectedStudents = new ArrayList<>();
        try (Connection conn = MySQLDatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT student_id FROM applications WHERE company_id = ? AND status = 'selected'")) {
            ps.setString(1, companyId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                selectedStudents.add(rs.getString("student_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selectedStudents;
    }
}

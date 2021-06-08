package Server.Model;

import java.io.Serializable;
import java.sql.*;

/**
 * The catalogue of courses
 */
public class CourseCatalogue implements Serializable {
    /**
     * The connection to database
     */
    private Connection conn;

    /**
     * Construct the course catalogue with the connection given
     * @param conn the connection to the database
     */
    public CourseCatalogue(Connection conn) {
        this.conn = conn;
    }

    /**
     * Add a new course to the catalogue with the given details
     * Only to be used by admin
     * @param name the name of the new course
     * @param secnum the number of section the course will have
     * @param seccap the maximum students per section
     */
    public void addCourseToCatalouge(String name, int secnum, int seccap) {
        try {
            int id = getRowCount();
            String query = "INSERT INTO courses(courseid,coursename,numofsecs,seccap) VALUES (?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, secnum);
            pstmt.setInt(4, seccap);
            pstmt.executeUpdate();
            for (int i = 1; i <= secnum; i++) {
                createCourseSection(id, i, seccap);
            }
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Count the number of courses in the database
     * @return the number of courses in database
     * @throws SQLException
     */
    private int getRowCount() throws SQLException {
        String query = "SELECT COUNT(*) FROM courses";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        rs.next();
        int result = rs.getInt("count(*)");
        return result;
    }

    /**
     * Create a section for a specific course
     * @param courseid the id of that course
     * @param secNum the section number
     * @param secCap the maximum students of the section
     */
    public void createCourseSection(int courseid, int secNum, int secCap) {
        try {
            String query = "INSERT INTO sections(courseid,secnum,seccap,numofstudents) VALUES (?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, courseid);
            pstmt.setInt(2, secNum);
            pstmt.setInt(3, secCap);
            pstmt.setInt(4, 0);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Search for a course in the catalogue
     * @param courseName the name of the course to search for
     * @return the info of the course or course not found
     */
    public String searchCat(String courseName) {
        String result = "";
        try {
            String query = "SELECT * FROM courses WHERE coursename=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, courseName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result += rs.getString("coursename") + "\n";
                result += "Course ID: " + rs.getString("courseid") + "\n";
                int id = rs.getInt("courseid");
                query = "SELECT * FROM sections WHERE courseid=?";
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1, rs.getString("courseid"));
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    result += "Section " + rs.getString("secnum") + ": ";
                    result += rs.getString("numofstudents") + "/" + rs.getString("seccap") + "\n";
                }
                query = "SELECT * FROM preregs WHERE courseid=?";
                pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, id);
                rs = pstmt.executeQuery();
                result += "Prerequisites :";
                while (rs.next()) {
                    result += " " + rs.getString("preregname") + ",";
                }
                pstmt.close();
                result += "\n";
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Course not found";
    }

    /**
     * Print info of a course in the result set
     * @param rs the result set
     * @return the info of the course
     */
    public String printCourse(ResultSet rs) throws SQLException {
        String result = "\n";
        result += rs.getString("coursename") + "\n";
        result += "Course ID: " + rs.getString("courseid") + "\n";
        int id = rs.getInt("courseid");
        String query = "SELECT * FROM sections WHERE courseid=?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            result += "Section " + rs.getString("secnum") + ": ";
            result += rs.getString("numofstudents") + "/" + rs.getString("seccap") + "\n";
        }
        query = "SELECT * FROM preregs WHERE courseid=?";
        pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();
        result += "Prerequisites :";
        while (rs.next()) {
            result += " " + rs.getString("preregname") + ",";
        }
        result += "\n________________________________________\n";
        return result;
    }

    /**
     * Print all courses in the catalogue
     * @return the info of all courses in the catalogue
     */
    public String toString() {
        String st = "All courses in the catalogue: \n";
        try {
            String query = "SELECT * FROM courses";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                st += printCourse(rs);
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return st;
    }

}

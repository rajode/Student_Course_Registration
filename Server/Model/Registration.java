package Server.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represent a student registration in a course
 */
public class Registration {
    /**
     * Id of that student
     */
    private int studentId;

    /**
     * Id of a course
     */
    private int courseId;

    /**
     * The section number
     */
    private int section;

    /**
     * Grade of that student
     */
    private String grade;

    /**
     * The connection to the database
     */
    private Connection conn;

    /**
     * Construct a registration with given values
     *
     * @param studentId student id
     * @param courseId  course id
     * @param section   the section number
     * @param grade     the grade
     * @param conn      the connection to database
     */
    public Registration(int studentId, int courseId, int section, String grade, Connection conn) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.section = section;
        this.grade = grade;
        this.conn = conn;
    }

    /**
     * Add the registration to the ongoingregistrations table.
     * Represent a course currently enrolled.
     */
    public void ongoingRegistration() {
        try {
            String query = "INSERT INTO ongoingregistrations (studentid,courseid,secnum,grade) values (?,?,?,?)";
            prepareQuery(query);
            increaseStudent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add the registration to the completedregistrations table.
     * Represent a completed course.
     */
    public void completedRegistration() {
        try {
            String query = "INSERT INTO completedregistrations (studentid,courseid,secnum,grade) values (?,?,?,?)";
            prepareQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove a course registration
     */
    public void removeRegistration() {
        try {
            String query = "DELETE FROM ongoingregistrations WHERE courseid=? AND studentid=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, String.valueOf(courseId));
            pstmt.setString(2, String.valueOf(studentId));
            pstmt.executeUpdate();
            pstmt.close();
            decreseStudent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lower the student count of a course section
     */
    private void decreseStudent() {
        try {
            String query = "UPDATE sections SET numofstudents=numofstudents-1 WHERE secnum=? AND courseid=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, String.valueOf(section));
            pstmt.setString(2, String.valueOf(courseId));
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Increase the student count of a course section
     */
    private void increaseStudent() throws SQLException {
        String query = "UPDATE sections SET numofstudents=numofstudents+1 WHERE secnum=? AND courseid=?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, section);
        pstmt.setInt(2, courseId);
        pstmt.executeUpdate();
        pstmt.close();
    }

    /**
     * Helper function, prepare a query and execute it
     *
     * @param query the query to be prepared and executed
     */
    private void prepareQuery(String query) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, studentId);
        pstmt.setInt(2, courseId);
        pstmt.setInt(3, section);
        pstmt.setString(4, grade);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public int getCourseId() {
        return courseId;
    }

    /**
     * Print the info of the registration
     * @return registration info
     */
    public String toString() {
        String st = "";
        try {
            String query = "SELECT * FROM courses WHERE courseid=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, String.valueOf(courseId));
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            st += rs.getString("coursename") + "\n";
            pstmt.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        st += "Section " + section + "\n";
        st += "Grade: " + grade;
        st += "\n-----------------------------------------------------------\n";
        return st;
    }
}

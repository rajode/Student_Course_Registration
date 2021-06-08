package Server.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Represent a user
 */
public class User {
    /**
     * The user name
     */
    private String name;

    /**
     * The user id
     */
    private int id;

    /**
     * The user access level
     */
    private int accessLevel;

    /**
     * The connection to database
     */
    private Connection conn;

    /**
     * The student completed courses
     */
    private ArrayList<Registration> completeCourses;

    /**
     * The student ongoing courses
     */
    private ArrayList<Registration> studentRegList;

    /**
     * Construct a user with the given values
     *
     * @param name
     * @param studentId
     * @param accessLevel
     * @param conn
     */
    public User(String name, int studentId, int accessLevel, Connection conn) {
        this.name = name;
        this.id = studentId;
        this.accessLevel = accessLevel;
        this.conn = conn;
        initializeCompletedCourses();
        initializeStudentRegList();
    }

    /**
     * Initialize the completed courses array from the database
     */
    private void initializeCompletedCourses() {
        try {
            String query = "SELECT * FROM completedregistrations WHERE studentid=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            completeCourses = new ArrayList<>();
            while (rs.next()) {
                completeCourses.add(new Registration(rs.getInt("studentid"), rs.getInt("courseid"), rs.getInt("secnum"), rs.getString("grade"), conn));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize the ongoing courses from the database
     */
    private void initializeStudentRegList() {
        try {
            String query = "SELECT * FROM ongoingregistrations WHERE studentid=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            studentRegList = new ArrayList<>();
            while (rs.next()) {
                studentRegList.add(new Registration(rs.getInt("studentid"), rs.getInt("courseid"), rs.getInt("secnum"), rs.getString("grade"), conn));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getStudentName() {
        return name;
    }

    public int getStudentId() {
        return id;
    }

    /**
     * Print the info of the student
     * @return info of the student
     */
    public String toString() {
        String st = "Student Name: " + getStudentName() +
                " ID: " + getStudentId() + "\n";
        return st;
    }

    /**
     * Add a registration to the student ongoing courses
     * @param registration
     */
    public void addRegistration(Registration registration) {
        studentRegList.add(registration);
    }


    public int getAccessLevel() {
        return accessLevel;
    }

    /**
     * Remove a course from student ongoing list
     * @return the status of the remove operation
     */
    public String removeCourse(String courseName) {
        try {
            int i;
            String query = "SELECT * FROM courses WHERE coursename=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, courseName);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                return "Course not found";
            }
            int id = rs.getInt("courseid");
            pstmt.close();
            int size = studentRegList.size();
            for (i = 0; i < size; i++) {
                if (studentRegList.get(i).getCourseId() == id) {
                    studentRegList.get(i).removeRegistration();
                    studentRegList.remove(i);
                    break;
                }
            }
            if (i == size) {
                return "Not currently registered in this course";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Course removed successfully";
    }

    /**
     * Check if student meet the prereg of a course
     * @param id the id of that course
     * @return true if meet, false if not
     */
    private boolean checkPre(int id) {
        boolean x = true;
        try {
            String query = "SELECT * FROM preregs WHERE courseid=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    if (!checkCompletedCourse(rs.getInt("preregid"))) {
                        x = false;
                        break;
                    }
                }
            }
            pstmt.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    /**
     * Check if student have completed a course
     * @param id the id of that course
     * @return true if have completed, false if not
     */
    private boolean checkCompletedCourse(int id) {
        boolean x = false;
        for (Registration registration : completeCourses) {
            if (id == registration.getCourseId()) {
                x = true;
                break;
            }
            x = false;
        }
        return x;
    }

    /**
     * Check if the student already enrolled in the given course
     * @param id the id of that course
     * @return true if already enrolled, false otherwise
     */
    private boolean checkDuplicate(int id) {
        boolean x = false;
        for (Registration registration : studentRegList) {
            if (id == registration.getCourseId()) {
                x = true;
                break;
            }
            x = false;
        }
        return x;
    }

    /**
     * Print all student courses
     */
    public String printStudentCourses() {
        String s = "Completed courses: \n";
        for (Registration r : completeCourses) {
            s += r.toString();
        }
        s += "Current courses: \n";
        for (Registration r : studentRegList) {
            s += r.toString();
        }
        s += "\0";
        return s;
    }

    /**
     * Check the availability of the section
     * @param id the course id
     * @param section the section number
     * @return true if available, false if full
     */
    private boolean checkSectionAvailability(int id, int section) throws SQLException {
        String query = "SELECT * FROM sections WHERE courseid=? AND secnum=?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, id);
        pstmt.setInt(2, section);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        if (rs.getInt("numofstudents") < rs.getInt("seccap")) {
            rs.close();
            return true;
        } else {
            rs.close();
            return false;
        }
    }

    /**
     * Add a course to student ongoing list
     * @param section the section
     * @param courseName the name of the course
     * @return status update
     */
    public String addCourse(String courseName, int section) {
        String result = "";
        try {
            System.out.println(courseName);
            String query = "SELECT * FROM courses WHERE coursename=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, courseName);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                result = "Course not found";
                return result;
            }
            int courseid = rs.getInt("courseid");
            if (checkDuplicate(courseid)) {
                result = "Already registered in this course";
                return result;
            }
            if (studentRegList.size() >= 6) {
                result = "Maximum registration reached";
                return result;
            }
            if (!checkPre(courseid)) {
                result = "Prerequisite not met";
                return result;
            }
            if (rs.getInt("numofsecs") < section || section <= 0) {
                result = "No such section";
                return result;
            }
            if (!checkSectionAvailability(courseid, section)) {
                result = "Section is full";
                return result;
            }
            Registration reg = new Registration(id, courseid, section, "TBA", conn);
            System.out.print(id);
            studentRegList.add(reg);
            reg.ongoingRegistration();

            query = "SELECT * FROM sections WHERE courseid=? AND secnum=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, courseid);
            pstmt.setInt(2, section);

            rs = pstmt.executeQuery();
            rs.next();

            if (rs.getInt("numofstudents") >= 8) {
                result = "Minimum student fulfilled, section is run";
            } else {
                result = "Minimum student unfulfilled, waiting for more student before adding to offering list";
            }

            pstmt.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

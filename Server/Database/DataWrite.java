package Server.Database;

/**
 * Initialize the database
 */
public class DataWrite {
    public static void main (String []args){
        CourseWrite courseWrite=new CourseWrite();
        courseWrite.initializeConnection();
        courseWrite.createUsersTable();
        courseWrite.createCoursesTable();
        courseWrite.createCompletedRegistrationsTable();
        courseWrite.createOngoingRegistrationsTable();
        courseWrite.createPreRegsTable();
        courseWrite.createSectionsTable();
        courseWrite.populateUser();
        courseWrite.createCourses();
    }
}

package Server.Database;

import Server.Model.*;

import java.io.IOException;
import java.sql.*;

/**
 * A database to simulate a school
 */
public class CourseWrite implements Database {
    /**
     * The connection to database
     */
    private Connection conn;

    /**
     * The catalogue
     */
    private CourseCatalogue cat;

    /**
     * Initialize the connection to server
     */
    public void initializeConnection() {
        try {
            // Register JDBC driver
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            cat = new CourseCatalogue(conn);
        } catch (SQLException e) {
            System.out.println("Problem");
            e.printStackTrace();
        }
    }

    /**
     * Create the users table
     */
    public void createUsersTable() {
        String sql = "CREATE TABLE users " + "(studentid INTEGER not NULL, " + " studentname VARCHAR(255), "
                + "accesslevel BIT not NULL," + "username VARCHAR(255)," + "password VARCHAR(255)," + " PRIMARY KEY ( studentid ))";

        try {
            Statement stmt = conn.createStatement(); // construct a statement
            stmt.executeUpdate(sql); // execute my query (i.e. sql)
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Table can NOT be created!");
        }
        System.out.println("Created user table in given database...");
    }

    /**
     * Insert a user into the users table
     * @param name
     * @param id
     * @param accesslevel
     * @param username
     * @param password
     */
    public void insertUser(String name, int id, int accesslevel, String username, String password) {
        try {
            String query = "INSERT INTO users (studentid,studentname,accesslevel,username,password) values(?,?,?,?,?)";
            PreparedStatement pStat = conn.prepareStatement(query);
            pStat.setInt(1, id);
            pStat.setString(2, name);
            pStat.setInt(3, accesslevel);
            pStat.setString(4, username);
            pStat.setString(5, password);
            int rowCount = pStat.executeUpdate();
            System.out.println("row Count = " + rowCount);
            pStat.close();
        } catch (SQLException e) {
            System.out.println("problem inserting user");
            e.printStackTrace();
        }
    }

    /**
     * Create the ongoingregistraions table
     */
    public void createOngoingRegistrationsTable() {
        String sql = "CREATE TABLE ongoingregistrations " + "(studentid INTEGER not NULL, " + "courseid INTEGER not NULL, " + " grade VARCHAR(255), "
                + "secnum INTEGER not NULL )";

        try {
            Statement stmt = conn.createStatement(); // construct a statement
            stmt.executeUpdate(sql); // execute my query (i.e. sql)
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Table can NOT be created!");
        }
        System.out.println("Created table in given database...");
    }

    /**
     * Create the completedregistrations table
     */
    public void createCompletedRegistrationsTable() {
        String sql = "CREATE TABLE completedregistrations " + "(studentid INTEGER not NULL, " + "courseid INTEGER not NULL, " + " grade VARCHAR(255), "
                + "secnum INTEGER not NULL )";

        try {
            Statement stmt = conn.createStatement(); // construct a statement
            stmt.executeUpdate(sql); // execute my query (i.e. sql)
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Table can NOT be created!");
        }
        System.out.println("Created table in given database...");
    }

    /**
     * Create the courses table
     */
    public void createCoursesTable() {
        String sql = "CREATE TABLE courses " + "(courseid INTEGER not NULL, " + " coursename VARCHAR(255), "
                + "numofsecs INTEGER not NULL, " + "seccap INTEGER not NULL, " + " PRIMARY KEY ( courseid ))";

        try {
            Statement stmt = conn.createStatement(); // construct a statement
            stmt.executeUpdate(sql); // execute my query (i.e. sql)
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Table can NOT be created!");
        }
        System.out.println("Created table in given database...");
    }

    /**
     * Create the preregs table
     */
    public void createPreRegsTable() {
        String sql = "CREATE TABLE preregs " + "(courseid INTEGER not NULL, " + " preregid INTEGER not NULL, "
                + "preregname VARCHAR (255))";

        try {
            Statement stmt = conn.createStatement(); // construct a statement
            stmt.executeUpdate(sql); // execute my query (i.e. sql)
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Table can NOT be created!");
        }
        System.out.println("Created prereg table in given database...");
    }

    /**
     * Add courses to the courses table
     */
    public void createCourses() {
        cat.addCourseToCatalouge("ENGG 233", 2, 50);
        cat.addCourseToCatalouge("ENSF 337", 2, 50);
        cat.addCourseToCatalouge("ENEL 353", 2, 50);
        cat.addCourseToCatalouge("ENSF 409", 2, 50);
        cat.addCourseToCatalouge("CPSC 319", 2, 50);
        cat.addCourseToCatalouge("ENCM 369", 2, 50);
        cat.addCourseToCatalouge("ENSF 480", 2, 50);
        cat.addCourseToCatalouge("ECON 201", 2, 50);
        cat.addCourseToCatalouge("ECON 203", 2, 50);
        cat.addCourseToCatalouge("ECON 209", 2, 50);
        cat.addCourseToCatalouge("COMS 363", 2, 50);

        setPreReg(5, 1, "ENSF 337");
        setPreReg(5, 2, "ENEL 353");
        setPreReg(3, 1, "ENSF 337");
        setPreReg(4, 1, "ENSF 337");
        setPreReg(6, 3, "ENSF 409");
        setPreReg(6, 5, "ENCM 369");

        Registration r1=new Registration(300673,1,1,"A",conn);
        r1.completedRegistration();

        Registration r2=new Registration(300673,2,1,"A",conn);
        r2.completedRegistration();
    }

    /**
     * Set the prereg of a course
     * @param courseid the course's id
     * @param preregid the pre reg course's id
     * @param preregname the name of prereg course
     */
    private void setPreReg(int courseid, int preregid, String preregname) {
        try {
            String query = "INSERT INTO preregs (courseid,preregid,preregname) values(?,?,?)";
            PreparedStatement pStat = conn.prepareStatement(query);
            pStat.setInt(1, courseid);
            pStat.setInt(2, preregid);
            pStat.setString(3, preregname);
            int rowCount = pStat.executeUpdate();
            System.out.println("row Count = " + rowCount);
            pStat.close();
        } catch (SQLException e) {
            System.out.println("problem inserting user");
            e.printStackTrace();
        }
    }

    /**
     * Create the sections table
     */
    public void createSectionsTable() {
        String sql = "CREATE TABLE sections " + "(courseid INTEGER not NULL, " + " secnum INTEGER not NULL, " + " seccap INTEGER not NULL, "
                + " numofstudents INTEGER not NULL )";

        try {
            Statement stmt = conn.createStatement(); // construct a statement
            stmt.executeUpdate(sql); // execute my query (i.e. sql)
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Table can NOT be created!");
        }
        System.out.println("Created section table in given database...");
    }

    /**
     * Create users and insert to users table
     */
    public void populateUser() {
        insertUser("Abigail Hardscrabble", 300447, 0, "admin1", "12345678");
        insertUser("James P. Sullivan", 300673, 1, "secondyear", "12345678");
        insertUser("Randall Boggs", 300866, 1, "generic2", "12345678");
        insertUser("Scott Squibbles", 300064, 1, "generic3", "12345678");
        insertUser("Don Carlton", 300383, 1, "generic4", "12345678");
        insertUser("Username", 300284, 1, "generic5", "12345678");
        insertUser("Derek Knight", 300083, 1, "generic6", "12345678");
        insertUser("Brock Pearson", 300459, 1, "generic7", "12345678");
        insertUser("Johnny J. Worthington", 300351, 1, "generic8", "12345678");
        insertUser("Claire Wheeler", 300664, 1, "generic9", "12345678");
        insertUser("Chet Alexander,", 300363, 1, "generic10", "12345678");
        insertUser("Sheri Squibbles", 300671, 1, "generic11", "12345678");
        insertUser("Karen Graves, ", 300399, 1, "generic12", "12345678");
        insertUser("Frank McCay", 300413, 1, "generic13", "12345678");
        insertUser("Carrie Williams", 300228, 1, "generic14", "12345678");
    }
}

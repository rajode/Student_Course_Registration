package Server.Controller;

import Server.Model.*;

import java.io.*;
import java.net.Socket;
import java.sql.*;

/**
 * The controller of each thread
 */
public class SocketController implements Runnable, Database {
    /**
     * The user
     */
    private User user;

    /**
     * Connection to database
     */
    private Connection conn;

    /**
     * The course catalogue
     */
    private CourseCatalogue cat;

    /**
     * Input stream from socket
     */
    private ObjectInputStream socketIn;

    /**
     * Output stream to socket
     */
    private ObjectOutputStream socketOut;

    /**
     * Construct a socketcontroller with given socket
     * @param socket
     * @throws IOException
     */
    public SocketController(Socket socket) throws IOException {
        initializeConnection();
        this.cat = new CourseCatalogue(conn);
        this.socketOut = new ObjectOutputStream(socket.getOutputStream());
        this.socketIn = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * Initialize the connection to database server
     */
    public void initializeConnection() {
        try {
            // Register JDBC driver
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Problem");
            e.printStackTrace();
        }
    }

    /**
     * The menu
     * @throws Exception
     */
    public void switchCase() throws Exception {
        while (true) {
            String choice = socketIn.readObject().toString();
            String[] data = choice.split(" ");
            switch (Integer.parseInt(data[0])) {
                case 0:
                    login(data[1], data[2]);
                    break;
                case 1:
                    socketOut.writeObject(cat.toString() + "\0");
                    break;
                case 2:
                    searchCourse(data[1] + " " + data[2]);
                    break;
                case 3:
                    addCourse(data[1]+" "+data[2], data[3]);
                    break;
                case 4:
                    removeCourse(data[1] + " " + data[2]);
                    break;
                case 5:
                    socketOut.writeObject(user.printStudentCourses());
                    break;
                case 6:
                    user = null;
                    break;
                case 7:
                    cat.addCourseToCatalouge(data[1] + " " + data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4]));
                    break;
                default:
                    socketOut.writeObject("Invalid selection. Please try again.");
                    break;
            }
        }
    }

    /**
     * The login function
     * @param username the username
     * @param password the password
     * @throws IOException,SQLException
     */
    private void login(String username, String password) throws IOException,SQLException {
        String query = "SELECT * FROM users WHERE username=? AND password=?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        if (rs==null) {
            socketOut.writeObject("-1");
        } else {
            initializeUser(rs);
            socketOut.writeObject(user.getAccessLevel());
        }
        pstmt.close();
    }

    /**
     * Search for a course and print out course found
     * @throws IOException
     */
    public void searchCourse(String name) throws IOException {
        socketOut.writeObject(cat.searchCat(name));
    }

    /**
     * Add a course
     * @param name name of the course
     * @param section the section to be enrolled in
     * @throws IOException
     */
    public void addCourse(String name, String section) throws IOException {
        socketOut.writeObject(user.addCourse(name, Integer.parseInt(section)) + "\0");
    }

    /**
     * Remove a course
     * @param name the course name
     * @throws IOException
     */
    public void removeCourse(String name) throws IOException {
        socketOut.writeObject(user.removeCourse(name) + "\0");
    }

    /**
     * Initialize the user
     * @param rs the info of the user
     * @throws SQLException
     */
    private void initializeUser(ResultSet rs) throws SQLException {
        rs.next();
        this.user = new User(rs.getString("studentname"), rs.getInt("studentid"), rs.getInt("accesslevel"), conn);
    }

    /**
     * Run the thread
     */
    public void run() {
        try {
            switchCase();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
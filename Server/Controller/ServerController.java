package Server.Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The server
 */
public class ServerController implements Database {
    /**
     * The ServerSocket of the server
     */
    private ServerSocket serverSocket;

    /**
     * The ThreadPool to handle multiple clients
     */
    private ExecutorService pool;

    private Connection conn;

    /**
     * Construct a server with values provided in the arguments.
     * @param port the portNumber of localhost
     */
    public ServerController(int port) {
        try {
            serverSocket = new ServerSocket(port);
            pool = Executors.newCachedThreadPool();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Run the server
     * @throws IOException
     */
    public void runTheServer() throws IOException {
        while (true) {
            SocketController socketController = new SocketController(serverSocket.accept());
            pool.execute(socketController);
        }
    }

    /**
     * Main function
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ServerController server = new ServerController(9898);
        System.out.println("Server running");
        server.runTheServer();
    }
}

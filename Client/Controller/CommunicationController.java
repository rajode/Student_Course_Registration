package Client.Controller;

import java.awt.EventQueue;
import java.io.*;
import java.net.Socket;
import Client.View.*;


public class CommunicationController 
{

	private Socket aSocket;
	private ObjectOutputStream socketOut;
	private ObjectInputStream socketIn;

/**
 * Constructor that starts the connection between server and client
 */
	public CommunicationController() 
	{
		try {
            aSocket = new Socket("localhost", 9898);
            socketOut = new ObjectOutputStream(aSocket.getOutputStream());
            socketIn = new ObjectInputStream(aSocket.getInputStream());
            
        } catch (IOException e) {
            System.out.println("Error connecting to socket (CLIENT)");
            e.printStackTrace();
        }
		
	
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		CommunicationController mC = new CommunicationController();
		LoginController lgnController = new LoginController(mC.socketOut,mC.socketIn,new LoginFrame());
	}


	
	
}

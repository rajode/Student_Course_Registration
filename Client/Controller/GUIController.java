package Controller;

import java.io.*;
import java.net.Socket;

public class GUIController 
{
	
	View.MainFrame myFrame;
	Model.Student myStudent;
	CommunicationController myComms;
	
	private Socket aSocket;
	private ObjectOutputStream socketOut;
	private ObjectInputStream socketIn;
	
	public GUIController(View.MainFrame myFrame, Model.Student myStudent, CommunicationController myComms)
	{
		this.myFrame = myFrame;
		this.myStudent = myStudent;
		this.myComms = myComms;
		
		
		try {
            aSocket = new Socket("local host", 8099);
            socketOut = new ObjectOutputStream(aSocket.getOutputStream());
            socketIn = new ObjectInputStream(aSocket.getInputStream());
            
        } catch (IOException e) {
            System.out.println("Error connecting to socket (CLIENT)");
            e.printStackTrace();
        }
	}
	
	
	public void communicate()
	{
		try 
		{
			while(true) 
			{
				
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	private void sendObject(Object obj) throws IOException
    {
        socketOut.writeObject(obj);
        socketOut.flush();
    }
	
	
}

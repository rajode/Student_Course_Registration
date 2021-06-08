package Client.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import Client.View.AdminFrame;
import Client.View.LoginFrame;
import Client.View.MainFrame;


public class LoginController {

    private ObjectOutputStream socketOut;
    private ObjectInputStream socketIn;
    private LoginFrame myLoginFrame;
    private String sendToServer;

    /**
     * Create the login controller with given values.
     * @param socOut
     * @param socIn
     * @param loginFrame
     */
    public LoginController(ObjectOutputStream socOut, ObjectInputStream socIn, LoginFrame loginFrame) {
        socketOut = socOut;
        socketIn = socIn;

        myLoginFrame = loginFrame;

        myLoginFrame.getBtnLogin().addActionListener(new LoginListener());

        myLoginFrame.getBtnExit().addActionListener(new ExitListener());

    }

/**
 * Inner Class that listens to Login button.
 * @author Abid,Ragya,Long
 *
 */
    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            sendToServer = "0 " + myLoginFrame.getTfUsername().getText() + " " + myLoginFrame.getpasswordField().getText();
            try {
                socketOut.writeObject(sendToServer);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                String response = socketIn.readObject().toString();
                if (response.equals("0")) {
                    myLoginFrame.dispose();
    
                    AdminController myAdminController = new AdminController(socketOut, socketIn, new AdminFrame());
                }
                else if(response.equals("1")) {
                	 myLoginFrame.dispose();
                     MainController myMainController = new MainController(socketOut, socketIn, new MainFrame());
                    }
                 else {
                    JOptionPane.showMessageDialog(null, "Wrong Username or Password.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException | ClassNotFoundException e1) {
                e1.printStackTrace();
            }

        }
    }

/**
 * Inner Class that listens to exit button.
 * @author Abid,Ragya,Long
 *
 */
    class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        }
    }

}

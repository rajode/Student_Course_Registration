package View;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.*;


public class MainFrame extends JFrame
{

    JButton b1, b2, b3 ,b4, b5;
    //MyListener listener;

    public MainFrame () {
        super ("Main Window");
        //listener = new MyListener (this);
        JFrame mainFrame = new JFrame();
        JPanel bottomButtons = new JPanel();
        FlowLayout f = new FlowLayout();
        bottomButtons.setLayout(f);

        Container c = new Container();
        c = getContentPane();

        //creating bottom buttons
        b1 = new JButton ("Search Courses");
        b2 = new JButton ("Add Courses");
        b3 = new JButton("Remove Course");
        b4 = new JButton("View Catalgoue's Courses");
        b5 = new JButton("View Student's Courses");
        bottomButtons.add(b1);
        bottomButtons.add(b2);
        bottomButtons.add(b3);
        bottomButtons.add(b4);
        bottomButtons.add(b5);
      //creating center JText area
        JTextArea textArea = new JTextArea (20,30);
        textArea.setEditable(false);
        JScrollPane vScrollPane = new JScrollPane(textArea);
        JPanel centreTextArea = new JPanel();
        vScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        vScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        centreTextArea.add(vScrollPane);
      //creating top label area
        JLabel topLabel = new JLabel ("An application to maintain Student Records",SwingConstants.CENTER);
        JPanel topPanel = new JPanel();
        topPanel.add(topLabel);

        //adding panels to Frame
        c.add(bottomButtons,BorderLayout.SOUTH);
        c.add(centreTextArea,BorderLayout.CENTER);
        c.add(topLabel,BorderLayout.NORTH);
        setVisible(true);
        pack();


//        b1.addActionListener(new B1Listener (this));
//        b2.addActionListener(new B2Listener (this));
    }
}



    
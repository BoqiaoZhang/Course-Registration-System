package ui.gui.operations.studentoperations;

import model.Student;
import model.University;
import ui.gui.StudentOperationWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class StudentConfirmationWindow extends JFrame implements ActionListener {
    protected Student stu;
    protected University uni;
    protected JLabel successfulIcon;
    protected JLabel successfulSentence;
    protected static String SAVING_ICON_STORE = "./data/saved.png";
    protected JButton btnOK;

    //EFFECTS: Create a new window as a confirmation window
    //         after successfully saving data of a student's course list
    public StudentConfirmationWindow(University uni, Student stu) {
        this.stu = stu;
        this.uni = uni;
        init();
    }

    //EFFECTS:initialize all fields
    public void init() {
        successfulSentence = new JLabel();
        setTextForSuccessfulSentence(); //abstract method for setting the text of the successfulSentence

        ImageIcon image = new ImageIcon(SAVING_ICON_STORE);
        successfulIcon = new JLabel(image);
        btnOK = new JButton("OK");

        btnOK.addActionListener(this);

        this.setLayout(null);

        setBounds();

        addListeners();

        addComponents();

        setBounds(1,1,500,600);
        setTextForTitle(); //abstract method, setting the distinct title of each window

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //EFFECTS: a helper method of init
    //         add all components to the window
    public void addComponents() {
        add(successfulIcon);
        add(successfulSentence);
        add(btnOK);
    }

    /*//EFFECTS: start a single student-saving window, just for test
    public static void main(String[] args) {
        new SuccessConfirmationWindow(new University("Test University"),
                new Student("TestStaff"));
    }*/

    //EFFECTS: default method doing nothing
    @Override
    public void actionPerformed(ActionEvent e) {
        //default
    }

    //EFFECTS: a helper method of init
    //         set bounds for all the components
    public void setBounds() {
        successfulIcon.setBounds(20,20,400,300);
        successfulSentence.setBounds(20,350,200,20);
        btnOK.setBounds(20,400,60,20);
    }

    //EFFECTS: add listeners for all the buttons
    public void addListeners() {
        addListenerForOKButton();
    }

    //EFFECTS: add listener for "OK" button.
    public void addListenerForOKButton() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentOperationWindow(uni,stu);
                setVisible(false);
            }
        });
    }

    public abstract void setTextForTitle();

    public abstract void setTextForSuccessfulSentence();
}



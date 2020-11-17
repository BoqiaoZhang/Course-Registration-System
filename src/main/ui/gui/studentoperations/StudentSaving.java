package ui.gui.studentoperations;

import model.Student;
import model.University;
import model.UniversityStaff;
import ui.gui.DstaffOperationMenu;
import ui.gui.DstudentOperationMenu;
import ui.gui.staffoperations.StaffAdding;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentSaving extends JFrame implements ActionListener {
    private Student stu;
    private University uni;
    private JLabel savingSuccessfullyIcon;
    private JLabel savingSuccessfullySentence;
    private static String SAVING_ICON_STORE = "./data/saved.png";
    private JButton btnOK;

    //EFFECTS: Create a new window as a confirmation window
    //         after successfully saving data of a student's course list
    public StudentSaving(University uni,Student stu) {
        this.stu = stu;
        this.uni = uni;
        init();
    }

    //EFFECTS:initialize all fields
    public void init() {
        savingSuccessfullySentence = new JLabel("Successfully saved.");
        ImageIcon image = new ImageIcon(SAVING_ICON_STORE);
        savingSuccessfullyIcon = new JLabel(image); //TODO: Add an icon here later!!!!!!
        btnOK = new JButton("OK");

        btnOK.addActionListener(this);

        this.setLayout(null);

        setBounds();

        addListeners();

        addComponents();

        setBounds(1,1,500,600);
        setTitle("Saving Confirmation Page");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //EFFECTS: a helper method of init
    //         add all components to the window
    public void addComponents() {
        add(savingSuccessfullyIcon);
        add(savingSuccessfullySentence);
        add(btnOK);
    }

    //EFFECTS: start a single student-saving window, just for test
    public static void main(String[] args) {
        new StudentSaving(new University("Test University"),
                new Student("TestStaff"));
    }

    //EFFECTS: default method doing nothing
    @Override
    public void actionPerformed(ActionEvent e) {
        //default
    }

    //EFFECTS: a helper method of init
    //         set bounds for all the components
    public void setBounds() {
        savingSuccessfullyIcon.setBounds(20,20,400,300);
        savingSuccessfullySentence.setBounds(20,350,200,20);
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
                new DstudentOperationMenu(uni,stu);
                setVisible(false);
            }
        });
    }
}


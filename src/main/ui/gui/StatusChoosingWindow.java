package ui.gui;

import model.University;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusChoosingWindow extends JFrame implements ActionListener {
    private static University uni;
    private JLabel questionAboutStatus;
    private JRadioButton choosingStaff;
    private JRadioButton choosingStudent;
    private JButton btnOK;
    private JButton btnExit;

    //EFFECTS:Create a new status-choosing window
    public StatusChoosingWindow(University uni) {
        init(uni);
    }

    //EFFECTS: initialize all the fields
    public void init(University uni) {
        this.uni = uni;
        questionAboutStatus = new JLabel("Are you staff or a student?");
        questionAboutStatus.setBounds(20,20,300,20);

        choosingStaff = new JRadioButton("staff");
        choosingStaff.setBounds(20,60,100,20);
        choosingStaff.addActionListener(this);
        choosingStudent = new JRadioButton("student");
        choosingStudent.setBounds(20,80,100,20);
        choosingStudent.addActionListener(this);

        setLayout(null);

        btnOK = new JButton("Enter");
        btnExit = new JButton("Exit");
        btnOK.setBounds(60,120,60,20);
        btnExit.setBounds(125,120,60,20);

        addComponents();

        setBounds(1,1,500,600);
        setTitle("Course Registration System Login Page");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //EFFECTS:add all the components to our window
    public void addComponents() {
        add(questionAboutStatus);
        add(choosingStaff);
        add(choosingStudent);
        add(btnOK);
        add(btnExit);
    }

    //EFFECTS: start a single status-choosing window, just for test
    public static void main(String[] args) {
        new StatusChoosingWindow(new University("Test University"));
    }

    //EFFECTS: If "staff" button is clicked, go to the loginStaff window;
    //         If "student" button is clicked, go to the loginStudent window.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == choosingStaff && e.getSource() != choosingStudent) {
            new LoginStaffWindow(uni);
        } else if (e.getSource() != choosingStaff && e.getSource() == choosingStudent) {
            new LoginStudentWindow(uni);
        } else if (e.getSource() != choosingStaff && e.getSource() != choosingStudent) {
            System.out.println("Please select whether you are staff or a student.");
        } else if (e.getSource() == choosingStaff && e.getSource() == choosingStudent) {
            System.out.println("Please selection only one of the given two options");
        }
        setVisible(false);
    }
}

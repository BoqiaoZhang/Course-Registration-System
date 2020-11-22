package ui.gui.operations.staffoperations;

import model.University;
import model.UniversityStaff;
import ui.gui.StaffOperationWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffViewing extends JFrame implements ActionListener {
    private UniversityStaff staff;
    private University uni;
    private JLabel instruction;
    private JLabel courseList;
    private JButton btnOK;

    //EFFECTS: create a new window for staff-viewing operation
    public StaffViewing(University uni,UniversityStaff staff) {
        this.staff = staff;
        this.uni = uni;
        init();
    }

    //EFFECTS: a helper method, initializing all components
    public void init() {
        instruction = new JLabel("You are viewing the current university course list");
        courseList = new JLabel(staff.viewAllCourses(uni));
        btnOK = new JButton("OK");

        btnOK.addActionListener(this);

        this.setLayout(null);

        setBounds();

        addListeners();

        addComponents();

        setBounds(1,1,800,600);
        setTitle("Course Adding Page");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //EFFECTS: a helper method of init
    //         add all components
    public void addComponents() {
        add(instruction);
        add(courseList);
        add(btnOK);
    }

    //EFFECTS: create a single staff-loading window just for test
    public static void main(String[] args) {
        new StaffViewing(new University("Test University"),
                new UniversityStaff("TestStaff",0));
    }

    //EFFECTS: default method doing nothing
    @Override
    public void actionPerformed(ActionEvent e) {
        //default
    }

    //EFFECTS: set bounds for all components
    public void setBounds() {
        instruction.setBounds(20,20,500,20);
        courseList.setBounds(20,50,700,20);
        btnOK.setBounds(20,100,60,20);
    }

    //EFFECTS: add listeners for all the buttons
    public void addListeners() {
        addListenerForAddButton();
    }

    //EFFECTS: add listener for "OK" button.
    public void addListenerForAddButton() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StaffOperationWindow(uni,staff);
                setVisible(false);
                staff.viewAllCourses(uni); //TODO: Remember to comment this line out!!!
            }
        });
    }
}



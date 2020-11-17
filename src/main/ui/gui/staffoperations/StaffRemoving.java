package ui.gui.staffoperations;

import model.University;
import model.UniversityStaff;
import ui.gui.DstaffOperationMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffRemoving extends JFrame implements ActionListener {
    private UniversityStaff staff;
    private University uni;
    private JLabel instruction;
    private JLabel lblCourseName;
    private JLabel lblCourseNumber;
    private JTextField txtCourseName;
    private JTextField txtCourseNumber;
    private JButton btnOK;

    //EFFECTS: create a new window for staff-removing operation
    public StaffRemoving(University uni,UniversityStaff staff) {
        this.staff = staff;
        this.uni = uni;
        init();
    }

    //EFFECTS: a helper method, initializing all components
    public void init() {
        instruction = new JLabel("Please provide the following information of the course you want to remove");
        lblCourseName = new JLabel("The course name");
        lblCourseNumber = new JLabel("The course number");
        txtCourseName = new JTextField(15);
        txtCourseNumber = new JTextField(15);
        btnOK = new JButton("Remove");

        btnOK.addActionListener(this);

        this.setLayout(null);

        setBounds();

        addListeners();

        addComponents();

        setBounds(1,1,500,600);
        setTitle("Course Removing Page");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //EFFECTS: a helper method of init
    //         add all components
    public void addComponents() {
        add(instruction);
        add(lblCourseName);
        add(lblCourseNumber);
        add(txtCourseName);
        add(txtCourseNumber);
        add(btnOK);
    }

    //EFFECTS: create a single staff-removing window just for test
    public static void main(String[] args) {
        new StaffRemoving(new University("Test University"),
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
        lblCourseName.setBounds(20,60,150,20);
        lblCourseNumber.setBounds(20,100,150,20);
        txtCourseName.setBounds(180,60,60,20);
        txtCourseNumber.setBounds(180,100,60,20);
        btnOK.setBounds(20,200,60,20);
    }

    //EFFECTS: add listeners for all the buttons
    public void addListeners() {
        addListenerForAddButton();
    }

    //EFFECTS: add listener for "Remove" button.
    public void addListenerForAddButton() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                staff.removeCourse(uni,txtCourseName.getText(),txtCourseNumber.getText());
                new DstaffOperationMenu(uni,staff);
                setVisible(false);
                System.out.println(staff.viewAllCourses(uni)); //TODO: Remember to comment this line out!!!
            }
        });
    }
}

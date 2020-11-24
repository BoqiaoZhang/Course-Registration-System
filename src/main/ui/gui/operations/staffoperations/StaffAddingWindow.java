package ui.gui.operations.staffoperations;

import model.Course;
import model.University;
import model.UniversityStaff;
import ui.gui.StaffOperationWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffAddingWindow extends JFrame implements ActionListener {
    private UniversityStaff staff;
    private University uni;
    private Course cou;
    private JLabel instruction;
    private JLabel lblCourseName;
    private JLabel lblCourseNumber;
    private JLabel lblTotalSeats;
    private JTextField txtCourseName;
    private JTextField txtCourseNumber;
    private JTextField txtTotalSeats;
    private JButton btnAdd;
    private JButton btnBack;


    //EFFECTS: Create a new window for staff-adding operation
    public StaffAddingWindow(University uni, UniversityStaff staff) {
        this.staff = staff;
        this.uni = uni;
        init();
    }

    //EFFECTS: a helper method of init
    public void init() {
        instruction = new JLabel("Please provide the following information of the course you want to add");
        lblCourseName = new JLabel("The course name");
        lblCourseNumber = new JLabel("The course number");
        lblTotalSeats = new JLabel("The total seats");
        txtCourseName = new JTextField(15);
        txtCourseNumber = new JTextField(15);
        txtTotalSeats = new JTextField(15);
        btnAdd = new JButton("Add");
        btnBack = new JButton("Back");

        btnAdd.addActionListener(this);

        this.setLayout(null);

        setBounds();

        addListeners();

        addComponents();

        setBounds(1,1,500,600);
        setTitle("Course Adding Page");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //EFFECTS: a helper method of init
    //         add all components to the window
    public void addComponents() {
        add(instruction);
        add(lblCourseName);
        add(lblCourseNumber);
        add(lblTotalSeats);
        add(txtCourseName);
        add(txtCourseNumber);
        add(txtTotalSeats);
        add(btnAdd);
        add(btnBack);
    }

    //EFFECTS: start a single staff-adding window, just for test
    public static void main(String[] args) {
        new StaffAddingWindow(new University("Test University"),
                new UniversityStaff("TestStaff",0));
    }

    //EFFECTS: default method doing nothing
    @Override
    public void actionPerformed(ActionEvent e) {
        //default
    }

    //EFFECTS: a helper method of init
    //         set bounds for all the components
    public void setBounds() {
        instruction.setBounds(20,20,500,20);
        lblCourseName.setBounds(20,60,150,20);
        lblCourseNumber.setBounds(20,100,150,20);
        lblTotalSeats.setBounds(20,140,150,20);
        txtCourseName.setBounds(180,60,60,20);
        txtCourseNumber.setBounds(180,100,60,20);
        txtTotalSeats.setBounds(180,140,60,20);
        btnAdd.setBounds(20,200,60,20);
        btnBack.setBounds(100,200,60,20);
    }

    //EFFECTS: add listeners for all the buttons
    public void addListeners() {
        addListenerForAddButton();
        addListenerForBackBtn();
    }

    //EFFECTS: add listener for "add" button.
    public void addListenerForAddButton() {
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cou = new Course(txtCourseName.getText(),
                        Integer.parseInt(txtCourseNumber.getText()),
                        Integer.parseInt(txtTotalSeats.getText()));
                staff.addNewCourse(uni,cou);
                new StaffOperationWindow(uni,staff);
                setVisible(false);
                System.out.println(staff.viewAllCourses(uni)); //TODO: Remember to comment this line out!!!
            }
        });
    }

    //EFFECTS: when clicking Back button, go back to the staffOperationWindow
    public void addListenerForBackBtn() {
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new StaffOperationWindow(uni,staff);
            }
        });
    }
}


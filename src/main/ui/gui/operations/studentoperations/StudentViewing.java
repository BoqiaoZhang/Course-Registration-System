package ui.gui.operations.studentoperations;

import model.Student;
import model.University;
import ui.gui.StudentOperationWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentViewing extends JFrame implements ActionListener {
    private Student stu;
    private University uni;
    private JLabel instruction;
    private JLabel courseList;
    private JButton btnOK;

    private JLabel lblIntroductionForDropping;
    private JLabel lblDropReminder;
    private JLabel lblDrop;
    private JTextField txtDropCourse;
    private JButton btnDrop;

    //EFFECTS: create a window for students' viewing and dropping operations
    public StudentViewing(University uni, Student stu) {
        this.stu = stu;
        this.uni = uni;
        init();
    }

    //EFFECTS:initialize all fields
    public void init() {
        instruction = new JLabel("You are viewing your registered course list");
        courseList = new JLabel(stu.viewAllRegisteredCourses());
        btnOK = new JButton("Back");

        String instruction = "If you want to drop a course, please provide the course information.";
        lblIntroductionForDropping = new JLabel(instruction);
        String reminder = "Reminder: In the form of CourseNameCourseNumber (e.g. MATH100), without white space.";
        lblDropReminder = new JLabel(reminder);
        lblDrop = new JLabel("Drop the course: ");
        txtDropCourse = new JTextField(50);
        btnDrop = new JButton("Drop");

        btnOK.addActionListener(this);

        this.setLayout(null);

        setBounds();

        addListeners();

        addComponents();

        setBounds(1,1,800,600);
        setTitle("Course Viewing Page");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //EFFECTS: a helper method of init
    //         add all components to the window
    public void addComponents() {
        add(instruction);
        add(courseList);
        add(btnOK);
        add(lblIntroductionForDropping);
        add(lblDropReminder);
        add(lblDrop);
        add(txtDropCourse);
        add(btnDrop);
    }

    //EFFECTS: start a single student-viewing window, just for test
    public static void main(String[] args) {
        new StudentViewing(new University("Test University"),
                new Student("TestStudent"));
    }

    //EFFECTS: default method doing nothing
    @Override
    public void actionPerformed(ActionEvent e) {
        //default
    }

    //EFFECTS:set bounds for all components
    public void setBounds() {
        instruction.setBounds(20,20,500,20);
        courseList.setBounds(20,50,700,20);
        btnOK.setBounds(20,100,60,20);

        lblIntroductionForDropping.setBounds(20,160,500,20);
        lblDropReminder.setBounds(20,190,600,20);
        lblDrop.setBounds(20,220,120,20);
        txtDropCourse.setBounds(150,220,200,20);
        btnDrop.setBounds(20,270,50,20);
    }

    //EFFECTS:add listeners for all buttons
    public void addListeners() {
        addListenerForAddButton();
        addListenerForDropButton();
    }

    //EFFECTS: add listener for "Back" button
    public void addListenerForAddButton() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentOperationWindow(uni,stu);
                setVisible(false);
            }
        });
    }

    //EFFECTS: add listener for "Drop" button
    public void addListenerForDropButton() {
        btnDrop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseInfo = txtDropCourse.getText();
                stu.dropCourse(uni,courseInfo);
                new StudentDropping(uni,stu);
                setVisible(false);
            }
        });
    }
}

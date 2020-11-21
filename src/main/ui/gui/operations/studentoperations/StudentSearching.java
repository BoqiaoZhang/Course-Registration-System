package ui.gui.operations.studentoperations;

import model.Student;
import model.University;
import ui.gui.DstudentOperationMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentSearching extends JFrame implements ActionListener {
    private University uni;
    private Student stu;
    private JLabel instruction;
    private JLabel reminder;
    private JLabel lblSearch;
    private JLabel lblTrueSearch;
    private JLabel lblFalseSearch;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JButton btnOK;

    private JLabel lblCheckSeatsIntroduction;
    private JButton btnCheckSeats;
    private JLabel lblTrueCheck;
    private JLabel lblFalseCheck;

    private JLabel lblRegister;
    private JButton btnRegister;

    //EFFECTS: create a window for students' searching, checkingSeats and registering
    public StudentSearching(University uni, Student stu) {
        this.uni = uni;
        this.stu = stu;
        init();
    }

    //EFFECTS:initialize all fields
    public void init() {
        initFields();

        btnSearch.addActionListener(this);
        addListenerForOK();
        addListenerForCheckSeats();
        addListenerForRegister();

        this.setLayout(null);
        setBounds();
        setVisibles();
        addComponents();

        setBounds(1,1,700,600);
        setTitle("Course Searching Page");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //EFFECTS: a helper method of init just because of max line limit of each method
    public void initFields() {
        instruction = new JLabel("Please provide the course information below.");
        String form = "Reminder: In the form of CourseNameCourseNumber (e.g. MATH100), without white space.";
        reminder = new JLabel(form);
        lblSearch = new JLabel("Search for: ");
        txtSearch = new JTextField(25);
        btnOK = new JButton("Back");
        btnSearch = new JButton("Search");
        lblTrueSearch = new JLabel("The course is available.");
        lblFalseSearch = new JLabel("Sorry, the course is not found.");

        lblCheckSeatsIntroduction = new JLabel("Want to check the remaining seats of this course? ");
        btnCheckSeats = new JButton("Check seats");
        lblTrueCheck = new JLabel("There are available seats.");
        lblFalseCheck = new JLabel("Sorry, this course is full");

        lblRegister = new JLabel("Want to register for this course? ");
        btnRegister = new JButton("Register");
    }

    //EFFECTS: add listener for "OK" button.
    public void addListenerForOK() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DstudentOperationMenu(uni,stu);
                setVisible(false);
            }
        });
    }

    //EFFECTS: add listener for "check seats" button.
    public void addListenerForCheckSeats() {
        btnCheckSeats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean result = stu.checkSeats(uni,txtSearch.getText());
                if (result) {
                    lblTrueCheck.setVisible(true);
                    lblFalseCheck.setVisible(false);
                    lblRegister.setVisible(true);
                    btnRegister.setVisible(true);
                    btnOK.setBounds(120,330,80,20);
                } else {
                    lblTrueCheck.setVisible(false);
                    lblFalseCheck.setVisible(true);
                }
            }
        });
    }

    //EFFECTS: add listener for "register" button.
    public void addListenerForRegister() {
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stu.registerCourse(uni,txtSearch.getText());
                new StudentRegistering(uni,stu);
                setVisible(false);
            }
        });
    }

    //EFFECTS: When clicking Search button, respond as below
    //         if the course is in the university course list, make "lblTrueSearch" label visible
    //         also, make "check seats" button/labels/textFields visible;
    //         if the course cannot be found, make "lblFalseSearch" label visible
    @Override
    public void actionPerformed(ActionEvent e) {
        if (stu.searchCourse(uni,txtSearch.getText())) {
            lblCheckSeatsIntroduction.setVisible(true);
            btnCheckSeats.setVisible(true);
            lblTrueSearch.setVisible(true);
            lblFalseSearch.setVisible(false);
            btnOK.setBounds(120,230,50,20);
        } else {
            lblTrueSearch.setVisible(false);
            lblFalseSearch.setVisible(true);
            lblCheckSeatsIntroduction.setVisible(false); ;
            btnCheckSeats.setVisible(false);
            lblTrueCheck.setVisible(false);
            lblFalseCheck.setVisible(false);
            lblRegister.setVisible(false);
            btnRegister.setVisible(false);
            btnOK.setBounds(100,160,50,20);
        }
    }

    //EFFECTS: a helper method of init
    //         add all components to the window
    public void addComponents() {
        add(instruction);
        add(reminder);
        add(lblSearch);
        add(lblTrueSearch);
        add(lblFalseSearch);
        add(txtSearch);
        add(btnSearch);
        add(btnOK);
        add(lblCheckSeatsIntroduction);
        add(btnCheckSeats);
        add(lblTrueCheck);
        add(lblFalseCheck);
        add(lblRegister);
        add(btnRegister);
    }

    //EFFECTS: a helper method of init
    //         set bounds for all the components
    public void setBounds() {
        instruction.setBounds(20,20,600,20);
        reminder.setBounds(20,40,600,20);
        lblSearch.setBounds(20,70,800,20);
        txtSearch.setBounds(110,70,200,20);
        lblTrueSearch.setBounds(20,120,200,20);
        lblFalseSearch.setBounds(20,120,200,20);
        btnSearch.setBounds(20,160,50,20);
        btnOK.setBounds(100,160,50,20);
        lblCheckSeatsIntroduction.setBounds(20,200,500,20);
        btnCheckSeats.setBounds(20,230,80,20);
        lblTrueCheck.setBounds(20,260,200,20);
        lblFalseCheck.setBounds(20,260,200,20);
        lblRegister.setBounds(20,310,500,20);
        btnRegister.setBounds(20,330,80,20);
    }

    //EFFECTS: a helper method of init
    //         initially, set "search" related visible
    //                    "check seats" or "register" related invisible
    public void setVisibles() {
        lblTrueSearch.setVisible(false);
        lblFalseSearch.setVisible(false);

        lblCheckSeatsIntroduction.setVisible(false);
        btnCheckSeats.setVisible(false);
        lblTrueCheck.setVisible(false);
        lblFalseCheck.setVisible(false);

        lblRegister.setVisible(false);
        btnRegister.setVisible(false);
    }

    //EFFECTS: start a single student-searching window, just for test
    public static void main(String[] args) {
        new StudentSearching(new University("Test University"),
                new Student("TestStudent"));
    }
}

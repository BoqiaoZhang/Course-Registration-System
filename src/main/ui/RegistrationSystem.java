package ui;

import model.Course;
import model.Student;
import model.University;
import model.UniversityStaff;
import java.util.Scanner;

public class RegistrationSystem {
    private String inputName;
    private int inputNumber;
    private String inputUniversityName;
    private University uni;
    private UniversityStaff staff;
    private Student stu;
    private Course c1;
    private Scanner input1;
    private Scanner input2;
    private Scanner input3;

    public RegistrationSystem() {
        input1 = new Scanner(System.in);
        input2 = new Scanner(System.in);
        input3 = new Scanner(System.in);
        login();
    }

    public void login() {
        String command = null;
        loginDisplayMenu();
        command = input1.next();

        processTypeCommand(command);
    }

    public void studentLogin() {
        String command1 = null;
        String command2 = null;
        String command3 = null;
        studentLoginDisplayMenu();
        command1 = input1.next();
        command2 = input2.next();
        command3 = input3.next();
        Student s = new Student(command1,Integer.parseInt(command2),command3);

        studentRegistrationSystem(s);
    }

    public void staffLogin() {
        System.out.println("Staff,haha");
    }

    // EFFECTS: displays menu of options to user
    private void loginDisplayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tStudent");
        System.out.println("\tUniversityStaff");
    }

    private void studentLoginDisplayMenu() {
        System.out.println("\nPlease fill in information");
        System.out.println("\tName");
        System.out.println("\tStudent Number");
        System.out.println("\tUniversity Name");
    }

    private void staffLoginDisplayMenu() {
        System.out.println("\nPlease fill in information");
        System.out.println("\tName");
        System.out.println("\tStaff Number");
        System.out.println("\tUniversity Name");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processTypeCommand(String command) {
        if (command.equals("Student")) {
            studentLogin();
        } else if (command.equals("UniversityStaff")) {
            staffLogin();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void studentRegistrationSystem(Student s) {
        String command = null;
        studentRegistrationDisplayMenu();
        command = input1.next();
        processStudentOperationCommand(command,s);
    }

    private void studentRegistrationDisplayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tsearch a course");
        System.out.println("\tcheck seats for a course");
        System.out.println("\tregister a course");
        System.out.println("\tdrop a course");
        System.out.println("\tview all registered courses");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processStudentOperationCommand(String command,Student s) {
        if (command.equals("search a course")) {
            studentSearchingDisplayMenu(s);
        } else if (command.equals("check seats for a course")) {
            studentCheckingSeatsDisplayMenu(s);
        } else if (command.equals("register a course")) {
            studentRegisterDisplayMenu(s);
        } else if (command.equals("drop a course")) {
            studentDropDisplayMenu(s);
        } else if (command.equals("view all registered courses")) {
            System.out.println(s.viewAllRegisteredCourses());
        } else {
            System.out.println("Operation not valid...");
        }
    }

    void processSearchingCommand(Student s) {
        String command = null;
        studentSearchingDisplayMenu(s);
        command = input1.next();
        s.searchCourse()
    }

    void studentSearchingDisplayMenu(Student s) {
        System.out.println("\tplease type the course name here");
    }

    void studentCheckingSeatsDisplayMenu(Student s) {
        System.out.println("\tplease type the course name here");
    }

    void studentRegisterDisplayMenu(Student s) {
        System.out.println("\tplease type the course name here");
    }

    void studentDropDisplayMenu(Student s) {
        System.out.println("\tplease type the course name here");
    }
}

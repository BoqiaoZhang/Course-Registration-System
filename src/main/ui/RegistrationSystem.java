package ui;

import model.Course;
import model.Student;
import model.University;
import model.UniversityStaff;

import java.util.ArrayList;
import java.util.Scanner;

public class RegistrationSystem {
    private University uni;
    private Scanner input1;
    private Scanner input2;
    private Scanner input3;


    public RegistrationSystem() {
        input1 = new Scanner(System.in);
        input2 = new Scanner(System.in);
        input3 = new Scanner(System.in);
        universityChoose();
    }

    private void universityChoose() {
        String command = null;
        universityMenu();
        command = input1.next();
        uni = new University(command);
        login();
    }

    private void universityMenu() {
        System.out.println("\tWhich university are you from?");
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
        bothloginDisplayMenu();
        command1 = input1.next();
        command2 = input2.next();
        Student s = new Student(command1, Integer.parseInt(command2));

        studentRegistrationSystem(s);
    }

    public void staffLogin() {
        String command1 = null;
        String command2 = null;
        bothloginDisplayMenu();
        command1 = input1.next();
        command2 = input2.next();
        UniversityStaff staff = new UniversityStaff(command1, Integer.parseInt(command2));

        staffSystem(staff);
    }

    private void staffSystem(UniversityStaff staff) {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            staffAddingRemovingMenu();
            command = input1.next();
            command = command.toLowerCase();
            if (command.equals("l")) {
                keepGoing = false;
                System.out.println("Logged out!");
            } else {
                if (command.equals("a")) {
                    addingSystem(staff);
                } else if (command.equals("r")) {
                    removingSystem(staff);
                } else if (command.equals("b")) {
                    login();
                } else {
                    System.out.println("Selection not valid...");
                }
            }
        }
    }

    private void addingSystem(UniversityStaff staff) {
        String command1 = null;
        String command2 = null;
        String command3 = null;
        System.out.println("Please provide the information about course name, course number and total available seats");
        command1 = input1.next();
        command2 = input2.next();
        command3 = input3.next();
        Course cou = new Course(command1, Integer.parseInt(command2), Integer.parseInt(command3));
        staff.addNewCourse(uni, cou);
    }

    private void removingSystem(UniversityStaff staff) {
        String command1 = null;
        String command2 = null;
        System.out.println("Please provide the course name and course number of the course you want to remove.");
        command1 = input1.next();
        command2 = input2.next();

        ArrayList<Course> courseList = uni.getUniversityCourseList();
        for (Course cou : courseList) {
            if ((cou.getCourseName().equals(command1)) && (Integer.parseInt(command2) == cou.getCourseNumber())) {
                courseList.remove(cou);
            }
        }
    }

    private void staffAddingRemovingMenu() {
        System.out.println("\nDo you want to add or remove a course");
        System.out.println("\ta -> add");
        System.out.println("\tr -> remove");
        System.out.println("\tl -> log out");
        System.out.println("\tb -> back to the log in page");
    }

    // EFFECTS: displays menu of options to user
    private void loginDisplayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tStudent");
        System.out.println("\tUniversityStaff");
    }

    private void bothloginDisplayMenu() {
        System.out.println("\nPlease fill in information");
        System.out.println("\tName");
        System.out.println("\tStudent/Staff Number");
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
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            studentRegistrationDisplayMenu();
            command = input1.next();

            if (command.equals("l")) {
                keepGoing = false;
                System.out.println("Logged out!");
            } else {
                processStudentOperationCommand(command, s);
            }
        }
    }

    private void studentRegistrationDisplayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ts -> search a course");
        System.out.println("\tc -> check seats for a course");
        System.out.println("\tr -> register a course");
        System.out.println("\td -> drop a course");
        System.out.println("\tv -> view all registered courses");
        System.out.println("\tl -> logout");
        System.out.println("\tb -> back to the log in page");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processStudentOperationCommand(String command, Student s) {
        if (command.equals("s")) {
            doSearchCommand(s);
        } else if (command.equals("c")) {
            studentOperationDisplayMenu(s);        //Remember:Similarly, we need "doCheckSeats" method
        } else if (command.equals("r")) {
            studentOperationDisplayMenu(s);        //Similarly
        } else if (command.equals("d")) {
            studentOperationDisplayMenu(s);        //Similarly
        } else if (command.equals("v")) {
            System.out.println(s.viewAllRegisteredCourses());     //Similarly
        } else if (command.equals("b")) {
            login();
        } else {
            System.out.println("Operation not valid...");
        }
    }

    void processSearchingCommand(Student s) {
        String command = null;
        studentOperationDisplayMenu(s);
        command = input1.next();
        s.searchCourse(uni, command);
    }

    void processCheckingSeatsCommand(Student s) {
        String command = null;
        studentOperationDisplayMenu(s);
        command = input1.next();
        s.checkSeats(uni, command);
    }

    void processRegisterCommand(Student s) {
        String command = null;
        studentOperationDisplayMenu(s);
        command = input1.next();
        s.registerCourse(uni, command);
    }

    void processDropCommand(Student s) {
        String command = null;
        studentOperationDisplayMenu(s);
        command = input1.next();
        s.dropCourse(uni, command);
    }


    void studentOperationDisplayMenu(Student s) {
        System.out.println("\tplease type the course name here");
    }

    void doSearchCommand(Student s) {
        String command = null;
        studentOperationDisplayMenu(s);
        command = input1.next();
        s.searchCourse(uni, command);
    }

    void doCheckSeatsCommand(Student s) {
        String command = null;
        studentOperationDisplayMenu(s);
        command = input1.next();
        s.checkSeats(uni, command);
    }

    void doRegisterCommand(Student s) {
        String command = null;
        studentOperationDisplayMenu(s);
        command = input1.next();
        s.registerCourse(uni, command);
    }

    void doDropCommand(Student s) {
        String command = null;
        studentOperationDisplayMenu(s);
        command = input1.next();
        s.dropCourse(uni, command);
    }

}

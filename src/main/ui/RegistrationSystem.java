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

    //EFFECTS: instantiate the three possible inputs and run the Registration system
    public RegistrationSystem() {
        input1 = new Scanner(System.in);
        input2 = new Scanner(System.in);
        input3 = new Scanner(System.in);
        universityChoose();
    }

    //EFFECTS: process the user command about the university they are in
    private void universityChoose() {
        String command = null;
        universityMenu();
        command = input1.next();
        uni = new University(command);
        login();
    }

    //EFFECTS: display the universityChoosing
    private void universityMenu() {
        System.out.println("\tWhich university are you from? Please just use the abbreviation of the university name.");
    }

    //EFFECTS: process login command, separating users into two groups, student and university staff.
    public void login() {
        String command = null;
        loginDisplayMenu();
        command = input1.next();

        processTypeCommand(command);
    }

    //EFFECTS: process student login command
    public void studentLogin() {
        String command1 = null;
        String command2 = null;
        bothLoginDisplayMenu();
        command1 = input1.next();
        command2 = input2.next();
        Student s = new Student(command1, Integer.parseInt(command2));

        studentRegistrationSystem(s);
    }

    //EFFECTS: process staff login command
    public void staffLogin() {
        String command1 = null;
        String command2 = null;
        bothLoginDisplayMenu();
        command1 = input1.next();
        command2 = input2.next();
        UniversityStaff staff = new UniversityStaff(command1, Integer.parseInt(command2));

        staffSystem(staff);
    }

    //EFFECTS: run the staff system
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

    //EFFECTS: add a course to the university course list
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

    //EFFECTS:remove a course from the current university course list
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

    //EFFECTS: display the add/remove menu for university staff
    private void staffAddingRemovingMenu() {
        System.out.println("\nDo you want to add or remove a course");
        System.out.println("\ta -> add");
        System.out.println("\tr -> remove");
        System.out.println("\tl -> log out");
        System.out.println("\tb -> back to the log in page");
    }

    // EFFECTS: displays menu of user groups (student or university staff)
    private void loginDisplayMenu() {
        System.out.println("\nAre you a student or university staff? Please press s or u for selection:");
        System.out.println("\ts -> Student");
        System.out.println("\tu -> UniversityStaff");
    }

    //EFFECTS: display the instructions for users to provide their login information
    private void bothLoginDisplayMenu() {
        System.out.println("\nLogin Page: Please type your name in the first line and your number in the second line");
        System.out.println("\tName");
        System.out.println("\tStudent/Staff Number");
    }

    // EFFECTS: processes user command based on their user group
    private void processTypeCommand(String command) {
        if (command.equals("s")) {
            studentLogin();
        } else if (command.equals("u")) {
            staffLogin();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //EFFECTS: run the student registration system
    private void studentRegistrationSystem(Student s) {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            studentRegistrationDisplayMenu();
            command = input1.next();
            command = command.toLowerCase();

            if (command.equals("l")) {
                keepGoing = false;
            } else {
                processStudentOperationCommand(command, s);
            }
        }
        System.out.println("Logged out!");
    }

    //EFFECTS: display the student registration menu
    private void studentRegistrationDisplayMenu() {
        String confirm = "please confirm you have now registered the course by viewing your registered course list";
        System.out.println("Important: Please first search a course and check seats before registration!");
        System.out.println("Important: Please search a course before checking the seats!");
        System.out.println("Important: If you want to drop a course, " + confirm);
        System.out.println("\nSelect from:");
        System.out.println("\ts -> search a course");
        System.out.println("\tc -> check seats for a course");
        System.out.println("\tr -> register a course");
        System.out.println("\td -> drop a course");
        System.out.println("\tv -> view all registered courses");
        System.out.println("\tl -> logout");
        System.out.println("\tb -> back to the log in page");
    }

    // EFFECTS: processes student users' commands
    private void processStudentOperationCommand(String command, Student s) {
        if (command.equals("s")) {
            processSearchingCommand(s);
        } else if (command.equals("c")) {
            processCheckingSeatsCommand(s);        //Remember:Similarly, we need "doCheckSeats" method
        } else if (command.equals("r")) {
            processRegisterCommand(s);        //Similarly
        } else if (command.equals("d")) {
            processDropCommand(s);        //Similarly
        } else if (command.equals("v")) {
            System.out.println(s.viewAllRegisteredCourses());     //Similarly
        } else if (command.equals("b")) {
            login();
        } else {
            System.out.println("Operation not valid...");
        }
    }

    //EFFECTS: make a searching in the university course list
    void processSearchingCommand(Student s) {
        String command = null;
        studentOperationDisplayMenu(s);
        command = input1.next();
        if (s.searchCourse(uni, command)) {
            System.out.println("The course is available.");
        } else {
            System.out.println("Sorry, the course is not found.");
        }
    }

    //REQUIRES: Before checking seats, a student must first search the course of interest
    //EFFECTS:check the available seats for a course
    void processCheckingSeatsCommand(Student s) {
        String command = null;
        studentOperationDisplayMenu(s);
        command = input1.next();
        if (s.checkSeats(uni, command)) {
            System.out.println("There are available seats. You can register this course now.");
        } else {
            System.out.println("Sorry, this course is full.");
        }
    }

    //REQUIRES: Only when the course in the university list, can a student make a registration for this course.
    //EFFECTS: register a course
    void processRegisterCommand(Student s) {
        String command = null;
        studentOperationDisplayMenu(s);
        command = input1.next();
        s.registerCourse(uni, command);
        System.out.println("The course has been successfully registered.");
    }

    //REQUIRES: The course of interest must be in the current registered list
    //EFFECTS: drop a course
    void processDropCommand(Student s) {
        String command = null;
        studentOperationDisplayMenu(s);
        command = input1.next();
        s.dropCourse(uni, command);
        System.out.println("The course has been successfully dropped.");
    }

    //EFFECTS: display a general instruction about typing the course information
    void studentOperationDisplayMenu(Student s) {
        String form = "in the form of CourseNameCourseNumber (e.g. MATH100), without white space.";
        System.out.println("\tPlease type the course information here, " + form);
    }
}

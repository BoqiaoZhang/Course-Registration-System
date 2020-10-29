package ui;

import model.Course;
import model.Student;
import model.University;
import model.UniversityStaff;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class RegistrationSystem {
    //Reminder:i)   For this program each running, all the staff and students are regarded from the same university.
    //         ii)  We are supposed to first login as staff to set tup the university course list,then login as students
    //         iii) If we login multiple times, we need to logout the same times if we want to entirely stop running
    //              this program (Similar as opening many tabs on Chrome and we need to close all tabs at the end).

    private Student stu;
    private University uni;
    private final Scanner input1;
    private final Scanner input2;
    private final Scanner input3;
    private JsonWriter universityJsonWriter;
    private JsonReader universityJsonReader;
    private JsonWriter studentJsonWriter;
    private JsonReader studentJsonReader;
    private static final String UNIVERSITY_JSON_STORE = "./data/UBC.json";
    private static final String STUDENT_JSON_STORE = "./data/Student.json";

    //EFFECTS: instantiate the three possible inputs and run the Registration system
    public RegistrationSystem() {
        input1 = new Scanner(System.in);
        input2 = new Scanner(System.in);
        input3 = new Scanner(System.in);
        universityJsonWriter = new JsonWriter(UNIVERSITY_JSON_STORE);
        universityJsonReader = new JsonReader(UNIVERSITY_JSON_STORE);
        studentJsonWriter = new JsonWriter(STUDENT_JSON_STORE);
        studentJsonReader = new JsonReader(STUDENT_JSON_STORE);
        universityChoose();
    }

    //EFFECTS: process the user command about the university they are in
    private void universityChoose() {
        universityMenu();
        String command = input1.next();
        uni = new University(command);
        login();
    }

    //EFFECTS: display the universityChoosing
    private void universityMenu() {
        System.out.println("\tWhich university are you from? Please just use the abbreviation of the university name.");
    }

    //EFFECTS: process login command, separating users into two groups, student and university staff.
    public void login() {
        String command;
        loginDisplayMenu();
        command = input1.next();

        processTypeCommand(command);
    }

    //EFFECTS: process student login command
    public void studentLogin() {
        String command1;
        String command2;
        bothLoginDisplayMenu();
        command1 = input1.next();
        command2 = input2.next();
        stu = new Student(command1);

        studentRegistrationSystem(stu);
    }

    //EFFECTS: process staff login command
    public void staffLogin() {
        String command1;
        String command2;
        bothLoginDisplayMenu();
        command1 = input1.next();
        command2 = input2.next();
        UniversityStaff staff = new UniversityStaff(command1, Integer.parseInt(command2));

        staffSystem(staff);
    }

    //EFFECTS: run the staff system
    @SuppressWarnings("checkstyle:MethodLength")
    private void staffSystem(UniversityStaff staff) {        //over max lines!!!!!!!!!!!!!!1
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            staffAddingRemovingMenu();
            command = input1.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
                System.out.println("Logged out!");
            } else {
                if (command.equals("a")) {
                    addingSystem(staff);
                } else if (command.equals("r")) {
                    removingSystem(staff);
                } else if (command.equals("v")) {
                    System.out.println(staff.viewAllCourses(uni));
                } else if (command.equals("b")) {
                    login();
                } else if (command.equals("s")) {
                    saveUniversityCourseList();
                } else if (command.equals("l")) {
                    loadUniversityCourseList();
                } else {
                    System.out.println("Selection not valid...");
                }
            }
        }
    }

    //EFFECTS: add a course to the university course list
    private void addingSystem(UniversityStaff staff) {
        String command1;
        String command2;
        String command3;
        System.out.println("\nPlease provide the following information about the course you want to add:");
        System.out.println("\tcourse name");
        System.out.println("\tcourse number");
        System.out.println("\ttotal seats available");
        command1 = input1.next();
        command2 = input2.next();
        command3 = input3.next();
        Course cou = new Course(command1, Integer.parseInt(command2), Integer.parseInt(command3));
        staff.addNewCourse(uni, cou);
    }

    //EFFECTS:remove a course from the current university course list
    private void removingSystem(UniversityStaff staff) {
        String command1;
        String command2;
        System.out.println("\nPlease provide the following information about the course you want to remove:");
        System.out.println("\tcourse name");
        System.out.println("\tcourse number");
        command1 = input1.next();
        command2 = input2.next();

        staff.removeCourse(uni, command1, command2);
    }

    //EFFECTS: display the add/remove menu for university staff
    private void staffAddingRemovingMenu() {
        System.out.println("\nPlease select one operation:");
        System.out.println("\ta -> add a course");
        System.out.println("\tr -> remove a course");
        System.out.println("\tv -> view all the courses in the university course list");
        System.out.println("\ts -> save the current course list");
        System.out.println("\tl -> load the current course list");
        System.out.println("\tq -> log out");
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
        System.out.println("\nLogin Page: Please provide the following information.");
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
        String command;

        while (keepGoing) {
            studentRegistrationDisplayMenu();
            command = input1.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
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
        System.out.println("\nSelect from:");
        System.out.println("\tsearch -> search for a course");
        System.out.println("\tcheck -> check seats for a course");
        System.out.println("\tregister -> register a course");
        System.out.println("\tdrop -> drop a course");
        System.out.println("\tview -> view all registered courses");
        System.out.println("\tquit -> logout");
        System.out.println("\tback -> back to the log in page");
        System.out.println("\tsave -> save my registered course list");
        System.out.println("\tload -> load my registered course list");
        System.out.println("Important: Please first search a course and check seats before registration!");
        System.out.println("Important: Please search a course before checking the seats!");
        System.out.println("Important: If you want to drop a course, " + confirm);
    }

    // EFFECTS: processes student users' commands
    private void processStudentOperationCommand(String command, Student s) {
        if (command.equals("search")) {
            processSearchingCommand(s);
        } else if (command.equals("check")) {
            processCheckingSeatsCommand(s);
        } else if (command.equals("register")) {
            processRegisterCommand(s);
        } else if (command.equals("drop")) {
            processDropCommand(s);
        } else if (command.equals("view")) {
            System.out.println(s.viewAllRegisteredCourses());
        } else if (command.equals("back")) {
            login();
        } else if (command.equals("save")) {
            saveStudentCourseList();
        } else if (command.equals("load")) {
            loadStudentCourseList();
        } else {
            System.out.println("Operation not valid...");
        }
    }

    //EFFECTS: make a searching in the university course list
    void processSearchingCommand(Student s) {
        String command;
        studentOperationDisplayMenu();
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
        String command;
        studentOperationDisplayMenu();
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
        String command;
        studentOperationDisplayMenu();
        command = input1.next();
        s.registerCourse(uni, command);
        System.out.println("The course has been successfully registered.");
    }

    //REQUIRES: The course of interest must be in the current registered list
    //EFFECTS: drop a course
    void processDropCommand(Student s) {
        String command;
        studentOperationDisplayMenu();
        command = input1.next();
        s.dropCourse(uni, command);
        System.out.println("The course has been successfully dropped.");
    }

    //EFFECTS: display a general instruction about typing the course information
    void studentOperationDisplayMenu() {
        String form = "in the form of CourseNameCourseNumber (e.g. MATH100), without white space.";
        System.out.println("\tPlease type the course information here, " + form);
    }

    // EFFECTS: saves the universityCourseList to file
    private void saveUniversityCourseList() {
        try {
            universityJsonWriter.open();
            universityJsonWriter.write(uni);
            universityJsonWriter.close();
            System.out.println("Saved the course list of " + uni.getUniversityName() + " to " + UNIVERSITY_JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + UNIVERSITY_JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads universityCourseList from file
    private void loadUniversityCourseList() {
        try {
            uni = universityJsonReader.readUniversity();
            System.out.println("Loaded " + uni.getUniversityName() + " from " + UNIVERSITY_JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + UNIVERSITY_JSON_STORE);
        }
    }

    // EFFECTS: saves the Student information to file
    private void saveStudentCourseList() {
        try {
            studentJsonWriter.open();
            studentJsonWriter.write(stu);
            studentJsonWriter.close();
            universityJsonWriter.open();
            universityJsonWriter.write(uni);
            universityJsonWriter.close();
            System.out.println("Saved the course list of " + stu.getName() + " to " + STUDENT_JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + STUDENT_JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads Student information from file
    private void loadStudentCourseList() {
        try {
            stu = studentJsonReader.readStudent(uni);
            System.out.println("Loaded the course list of " + stu.getName() + " from " + STUDENT_JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + STUDENT_JSON_STORE);
        }
    }
}

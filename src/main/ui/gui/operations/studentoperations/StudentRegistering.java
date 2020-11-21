package ui.gui.operations.studentoperations;

import model.Student;
import model.University;

public class StudentRegistering extends StudentConfirmationWindow {


    //EFFECTS: Create a new window as a confirmation window
    //         after students' successful registration
    public StudentRegistering(University uni, Student stu) {
        super(uni,stu);
    }

    //EFFECTS: start a single student-registering window, just for test
    public static void main(String[] args) {
        new StudentRegistering(new University("Test University"),
                new Student("TestStudent"));
    }

    @Override
    public void setTextForTitle() {
        setTitle("Success Registration Confirmation Window");
    }

    @Override
    public void setTextForSuccessfulSentence() {
        successfulSentence.setText("Successfully registered");
    }
}


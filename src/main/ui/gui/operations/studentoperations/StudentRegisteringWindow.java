package ui.gui.operations.studentoperations;

import model.Student;
import model.University;

public class StudentRegisteringWindow extends StudentConfirmationWindow {


    //EFFECTS: Create a new window as a confirmation window
    //         after students' successful registration
    public StudentRegisteringWindow(University uni, Student stu) {
        super(uni,stu);
    }

    //EFFECTS: start a single student-registering window, just for test
    public static void main(String[] args) {
        new StudentRegisteringWindow(new University("Test University"),
                new Student("TestStudent"));
    }

    //EFFECTS: set the title to the String below
    @Override
    public void setTextForTitle() {
        setTitle("Success Registration Confirmation Window");
    }

    //EFFECTS: set the text of the successfulSentence
    @Override
    public void setTextForSuccessfulSentence() {
        successfulSentence.setText("Successfully registered");
    }
}


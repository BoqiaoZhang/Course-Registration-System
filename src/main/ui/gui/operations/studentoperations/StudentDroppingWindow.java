package ui.gui.operations.studentoperations;

import model.Student;
import model.University;

public class StudentDroppingWindow extends StudentConfirmationWindow {


    //EFFECTS: Create a new window as a confirmation window
    //         after students' successful dropping
    public StudentDroppingWindow(University uni, Student stu) {
        super(uni,stu);
    }

    //EFFECTS: set the title to the String below
    @Override
    public void setTextForTitle() {
        setTitle("Success Dropping Confirmation Window");
    }

    //EFFECTS: set the text of the successfulSentence
    @Override
    public void setTextForSuccessfulSentence() {
        successfulSentence.setText("Successfully dropped");
    }

    //EFFECTS: start a single student-dropping window, just for test
    public static void main(String[] args) {
        new StudentDroppingWindow(new University("Test University"),
                new Student("TestStudent"));
    }
}



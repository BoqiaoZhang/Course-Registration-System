package ui.gui.operations.studentoperations;

import model.Student;
import model.University;

public class StudentDropping extends StudentConfirmationWindow {


    //EFFECTS: Create a new window as a confirmation window
    //         after students' successful dropping
    public StudentDropping(University uni, Student stu) {
        super(uni,stu);
    }

    @Override
    public void setTextForTitle() {
        setTitle("Success Dropping Confirmation Window");
    }

    @Override
    public void setTextForSuccessfulSentence() {
        successfulSentence.setText("Successfully dropped");
    }

    //EFFECTS: start a single student-dropping window, just for test
    public static void main(String[] args) {
        new StudentDropping(new University("Test University"),
                new Student("TestStudent"));
    }
}



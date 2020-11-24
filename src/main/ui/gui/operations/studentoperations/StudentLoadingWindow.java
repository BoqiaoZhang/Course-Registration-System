package ui.gui.operations.studentoperations;

import model.Student;
import model.University;

public class StudentLoadingWindow extends StudentConfirmationWindow {

    //EFFECTS: Create a new window as a confirmation window
    //         after successfully loading data
    public StudentLoadingWindow(University uni, Student stu) {
        super(uni,stu);
    }


    //EFFECTS: start a single student-loading window, just for test
    public static void main(String[] args) {
        new StudentLoadingWindow(new University("Test University"),
                new Student("TestStaff"));
    }

    //EFFECTS: set the title to the String below
    @Override
    public void setTextForTitle() {
        setTitle("Successful Loading Confirmation Page");
    }

    //EFFECTS: set the text of the successfulSentence
    @Override
    public void setTextForSuccessfulSentence() {
        successfulSentence.setText("Successfully loaded");
    }
}


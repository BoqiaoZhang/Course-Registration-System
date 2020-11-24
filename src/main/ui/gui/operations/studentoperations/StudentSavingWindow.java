package ui.gui.operations.studentoperations;

import model.Student;
import model.University;

public class StudentSavingWindow extends StudentConfirmationWindow {

    //EFFECTS: Create a new window as a confirmation window
    //         after successfully saving data of a student's course list
    public StudentSavingWindow(University uni, Student stu) {
        super(uni,stu);
    }

    public static void main(String[] args) {
        new StudentSavingWindow(new University("Test University"),
                new Student("TestStaff"));
    }

    //EFFECTS: set the title to the String below
    @Override
    public void setTextForTitle() {
        setTitle("Successful Saving Confirmation Page");
    }

    //EFFECTS: set the text of the successfulSentence
    @Override
    public void setTextForSuccessfulSentence() {
        successfulSentence.setText("Successfully saved");
    }
}


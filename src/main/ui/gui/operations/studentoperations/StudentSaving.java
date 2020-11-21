package ui.gui.operations.studentoperations;

import model.Student;
import model.University;

public class StudentSaving extends StudentConfirmationWindow {

    //EFFECTS: Create a new window as a confirmation window
    //         after successfully saving data of a student's course list
    public StudentSaving(University uni,Student stu) {
        super(uni,stu);
    }

    public static void main(String[] args) {
        new StudentSaving(new University("Test University"),
                new Student("TestStaff"));
    }

    @Override
    public void setTextForTitle() {
        setTitle("Successful Saving Confirmation Page");
    }

    @Override
    public void setTextForSuccessfulSentence() {
        successfulSentence.setText("Successfully saved");
    }
}


package ui.gui.operations.staffoperations;

import model.University;
import model.UniversityStaff;

public class StaffLoadingWindow extends StaffConfirmationWindow {

    //EFFECTS: create a new window for staff-loading operation
    public StaffLoadingWindow(University uni, UniversityStaff staff) {
        super(uni,staff);
    }

    //EFFECTS: create a single staff-loading window just for test
    public static void main(String[] args) {
        new StaffLoadingWindow(new University("Test University"),
                new UniversityStaff("TestStaff",0));
    }

    //EFFECTS: set the title to the String below
    @Override
    public void setTextForTitle() {
        setTitle("Success Loading Confirmation Page");
    }

    //EFFECTS: set the text of the successfulSentence
    @Override
    public void setTextForSuccessSentence() {
        successfulSentence.setText("Successfully Loaded");
    }
}


package ui.gui.operations.staffoperations;

import model.University;
import model.UniversityStaff;

public class StaffLoading extends StaffConfirmationWindow {

    //EFFECTS: create a new window for staff-loading operation
    public StaffLoading(University uni,UniversityStaff staff) {
        super(uni,staff);
    }

    //EFFECTS: create a single staff-loading window just for test
    public static void main(String[] args) {
        new StaffLoading(new University("Test University"),
                new UniversityStaff("TestStaff",0));
    }

    @Override
    public void setTextForTitle() {
        setTitle("Success Loading Confirmation Page");
    }

    @Override
    public void setTextForSuccessSentence() {
        successfulSentence.setText("Successfully Loaded");
    }
}


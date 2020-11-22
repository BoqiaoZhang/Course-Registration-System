package ui.gui.operations.staffoperations;

import model.University;
import model.UniversityStaff;

public class StaffSaving extends StaffConfirmationWindow {

    //EFFECTS: create a new window for staff-saving operation
    public StaffSaving(University uni,UniversityStaff staff) {
        super(uni,staff);
    }


    //EFFECTS: create a single staff-saving window just for test
    public static void main(String[] args) {
        new StaffSaving(new University("Test University"),
                new UniversityStaff("TestStaff",0));
    }


    @Override
    public void setTextForTitle() {
        setTitle("Success Saving Confirmation Page");
    }

    @Override
    public void setTextForSuccessSentence() {
        successfulSentence.setText("Successfully saved");
    }
}

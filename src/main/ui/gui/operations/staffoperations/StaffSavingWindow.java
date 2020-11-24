package ui.gui.operations.staffoperations;

import model.University;
import model.UniversityStaff;

public class StaffSavingWindow extends StaffConfirmationWindow {

    //EFFECTS: create a new window for staff-saving operation
    public StaffSavingWindow(University uni, UniversityStaff staff) {
        super(uni,staff);
    }


    //EFFECTS: create a single staff-saving window just for test
    public static void main(String[] args) {
        new StaffSavingWindow(new University("Test University"),
                new UniversityStaff("TestStaff",0));
    }


    //EFFECTS: set the title to the String below
    @Override
    public void setTextForTitle() {
        setTitle("Success Saving Confirmation Page");
    }

    //EFFECTS: set the text of the successfulSentence
    @Override
    public void setTextForSuccessSentence() {
        successfulSentence.setText("Successfully saved");
    }
}

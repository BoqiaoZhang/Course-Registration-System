package ui.gui.staffoperations;

import model.University;
import model.UniversityStaff;
import ui.gui.DstaffOperationMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffSaving extends JFrame implements ActionListener {
    private UniversityStaff staff;
    private University uni;
    private JLabel savingSuccessfullyIcon;
    private JLabel savingSuccessfullySentence;
    private static String SAVING_ICON_STORE = "./data/saved.png";
    private JButton btnOK;

    //EFFECTS: create a new window for staff-saving operation
    public StaffSaving(University uni,UniversityStaff staff) {
        this.staff = staff;
        this.uni = uni;
        init();
    }

    //EFFECTS: a helper method, initializing all components
    public void init() {
        savingSuccessfullySentence = new JLabel("Successfully saved.");
        ImageIcon image = new ImageIcon(SAVING_ICON_STORE);
        savingSuccessfullyIcon = new JLabel(image); //TODO: Add an icon here later!!!!!!
        btnOK = new JButton("OK");

        btnOK.addActionListener(this);

        this.setLayout(null);

        setBounds();

        addListeners();

        addComponents();

        setBounds(1,1,500,600);
        setTitle("Course Adding Page");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //EFFECTS: a helper method of init
    //         add all components
    public void addComponents() {
        add(savingSuccessfullyIcon);
        add(savingSuccessfullySentence);
        add(btnOK);
    }

    //EFFECTS: create a single staff-saving window just for test
    public static void main(String[] args) {
        new StaffSaving(new University("Test University"),
                new UniversityStaff("TestStaff",0));
    }

    //EFFECTS: default method doing nothing
    @Override
    public void actionPerformed(ActionEvent e) {
        //default
    }

    //EFFECTS: set bounds for all components
    public void setBounds() {
        savingSuccessfullyIcon.setBounds(20,20,400,300);
        savingSuccessfullySentence.setBounds(20,350,200,20);
        btnOK.setBounds(20,400,60,20);
    }

    //EFFECTS: add listeners for all the buttons
    public void addListeners() {
        addListenerForAddButton();
    }

    //EFFECTS: add listener for "OK" button.
    public void addListenerForAddButton() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DstaffOperationMenu(uni,staff);
                setVisible(false);
            }
        });
    }
}

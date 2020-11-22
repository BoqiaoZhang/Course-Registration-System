package ui.gui.operations.staffoperations;

import model.University;
import model.UniversityStaff;
import ui.gui.StaffOperationWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class StaffConfirmationWindow extends JFrame implements ActionListener {
    protected UniversityStaff staff;
    protected University uni;
    protected JLabel successfulIcon;
    protected static String LOADING_ICON_STORE = "./data/saved.png";
    protected JLabel successfulSentence;
    protected JButton btnOK;

    //EFFECTS: create a new window for staff-loading operation
    public StaffConfirmationWindow(University uni,UniversityStaff staff) {
        this.staff = staff;
        this.uni = uni;
        init();
    }

    //EFFECTS: a helper method, initializing all components
    public void init() {
        ImageIcon image = new ImageIcon(LOADING_ICON_STORE);
        successfulIcon = new JLabel(image);
        successfulSentence = new JLabel();
        setTextForSuccessSentence(); //abstract method to set the text of the successSentence

        btnOK = new JButton("OK");

        btnOK.addActionListener(this);

        this.setLayout(null);

        setBounds();

        addListeners();

        addComponents();

        setBounds(1,1,500,600);
        setTextForTitle(); //abstract method to set the text of the title
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //EFFECTS: a helper method of init
    //         add all components
    public void addComponents() {
        add(successfulIcon);
        add(successfulSentence);
        add(btnOK);
    }

    //EFFECTS: default method doing nothing
    @Override
    public void actionPerformed(ActionEvent e) {
        //default
    }

    //EFFECTS: set bounds for all components
    public void setBounds() {
        successfulIcon.setBounds(20,20,400,300);
        successfulSentence.setBounds(20,350,200,20);
        btnOK.setBounds(20,400,60,20);
    }

    //EFFECTS: add listeners for all the buttons
    public void addListeners() {
        addListenerForOKButton();
    }

    //EFFECTS: add listener for "OK" button.
    public void addListenerForOKButton() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StaffOperationWindow(uni,staff);
                setVisible(false);
            }
        });
    }

    public abstract void setTextForTitle();

    public abstract void setTextForSuccessSentence();
}

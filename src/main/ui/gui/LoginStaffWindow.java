package ui.gui;

import model.University;
import model.UniversityStaff;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginStaffWindow extends JFrame implements ActionListener {
    private UniversityStaff staff;
    private University uni;
    private JLabel questionAboutLogin;
    private JTextField txtName;
    private JTextField txtNumber;
    private JLabel lblName;
    private JLabel lblNumber;
    private JButton btnOK;
    private JButton btnExit;

    //EFFECTS:Create a new staff-login window
    public LoginStaffWindow(University uni) {
        this.uni = uni;
        init();
    }

    //EFFECTS: initialize all the fields
    public void init() {
        questionAboutLogin = new JLabel("Please provide your name and student/staff number.");
        questionAboutLogin.setBounds(20,20,400,20);
        lblName = new JLabel("Name:");
        lblName.setBounds(20,60,100,20);
        lblNumber = new JLabel("Number:");
        lblNumber.setBounds(20,80,100,20);

        setLayout(null);

        txtName = new JTextField(15);
        txtName.setBounds(100,60,100,20);
        txtNumber = new JTextField(15);
        txtNumber.setBounds(100,80,100,20);

        btnOK = new JButton("Enter");
        btnExit = new JButton("Exit");
        btnOK.setBounds(60,100,60,20);
        btnExit.setBounds(125,100,60,20);
        btnOK.addActionListener(this);

        addComponents();

        setBounds(1,1,500,600);
        setTitle("Course Registration System Login Page");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //EFFECTS:add all the components to our window
    public void addComponents() {
        add(questionAboutLogin);
        add(txtName);
        add(txtNumber);
        add(lblName);
        add(lblNumber);
        add(btnOK);
        add(btnExit);
    }

    //EFFECTS: start a single staff-login window, just for test
    public static void main(String[] args) {
        new LoginStaffWindow(new University("Test University"));
    }

    //EFFECTS: When clicking OK button, go to the "staff operation menu" window
    @Override
    public void actionPerformed(ActionEvent e) {
        staff = new UniversityStaff(txtName.getText(),Integer.parseInt(txtNumber.getText()));
        setVisible(false);
        new StaffOperationWindow(uni,staff);
    }
}

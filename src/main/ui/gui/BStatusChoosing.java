package ui.gui;

import model.University;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BStatusChoosing extends JFrame implements ActionListener {
    private static University uni;
    private JLabel questionAboutStatus;
    private JRadioButton choosingStaff;
    private JRadioButton choosingStudent;
    private JButton btnOK;
    private JButton btnExit;

    public BStatusChoosing(University uni) {
        init(uni);
    }

    public void init(University uni) {
        this.uni = uni;
        questionAboutStatus = new JLabel("Are you staff or a student?");
        questionAboutStatus.setBounds(20,20,300,20);

        choosingStaff = new JRadioButton("staff");
        choosingStaff.setBounds(20,60,100,20);
        choosingStaff.addActionListener(this);
        choosingStudent = new JRadioButton("student");
        choosingStudent.setBounds(20,80,100,20);
        choosingStudent.addActionListener(this);

        setLayout(null);

        btnOK = new JButton("Enter");
        btnExit = new JButton("Exit");
        btnOK.setBounds(60,120,60,20);
        btnExit.setBounds(125,120,60,20);

        addComponents();

        setBounds(1,1,500,600);
        setTitle("GUIdesign");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addComponents() {
        add(questionAboutStatus);
        add(choosingStaff);
        add(choosingStudent);
        add(btnOK);
        add(btnExit);
    }

    public static void main(String[] args) {
        new BStatusChoosing(new University("Test University"));
    }

    //EFFECTS: just a default method doing noting
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == choosingStaff && e.getSource() != choosingStudent) {
            new CLoginStaff(uni);
        } else if (e.getSource() != choosingStaff && e.getSource() == choosingStudent) {
            new CLoginStudent(uni);
        } else if (e.getSource() != choosingStaff && e.getSource() != choosingStudent) {
            System.out.println("Please select whether you are staff or a student.");
        } else if (e.getSource() == choosingStaff && e.getSource() == choosingStudent) {
            System.out.println("Please selection only one of the given two options");
        }
        setVisible(false);
    }
}

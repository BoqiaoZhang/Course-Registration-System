package ui.gui.studentoperations;

import model.Student;
import model.University;
import ui.gui.DstudentOperationMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentRegistering extends JFrame implements ActionListener {
    private Student stu;
    private University uni;
    private JLabel savingSuccessfullyIcon;
    private JLabel savingSuccessfullySentence;
    private static String SAVING_ICON_STORE = "./data/saved.png";
    private JButton btnOK;

    public StudentRegistering(University uni, Student stu) {
        this.stu = stu;
        this.uni = uni;
        init();
    }

    public void init() {
        savingSuccessfullySentence = new JLabel("Successfully registered.");
        ImageIcon image = new ImageIcon(SAVING_ICON_STORE);
        savingSuccessfullyIcon = new JLabel(image); //TODO: Add an icon here later!!!!!!
        btnOK = new JButton("OK");

        btnOK.addActionListener(this);

        this.setLayout(null);

        setBounds();

        addListeners();

        addComponents();

        setBounds(1,1,500,600);
        setTitle("Successful Registration Confirmation Page");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addComponents() {
        add(savingSuccessfullyIcon);
        add(savingSuccessfullySentence);
        add(btnOK);
    }

    public static void main(String[] args) {
        new StudentRegistering(new University("Test University"),
                new Student("TestStudent"));
    }

    //EFFECTS: default method doing nothing
    @Override
    public void actionPerformed(ActionEvent e) {
        //default
    }

    public void setBounds() {
        savingSuccessfullyIcon.setBounds(20,20,400,300);
        savingSuccessfullySentence.setBounds(20,350,200,20);
        btnOK.setBounds(20,400,60,20);
    }

    public void addListeners() {
        addListenerForOKButton();
    }

    public void addListenerForOKButton() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DstudentOperationMenu(uni,stu);
                setVisible(false);
            }
        });
    }
}


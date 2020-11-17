package ui.gui.studentoperations;

import model.Student;
import model.University;
import model.UniversityStaff;
import ui.gui.DstaffOperationMenu;
import ui.gui.DstudentOperationMenu;
import ui.gui.staffoperations.StaffAdding;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentLoading extends JFrame implements ActionListener {
    private Student stu;
    private University uni;
    private JLabel savingSuccessfullyIcon;
    private static String LOADING_ICON_STORE = "./data/saved.png";
    private JLabel savingSuccessfullySentence;
    private JButton btnOK;

    public StudentLoading(University uni,Student stu) {
        this.stu  = stu;
        this.uni = uni;
        init();
    }

    public void init() {
        ImageIcon image = new ImageIcon(LOADING_ICON_STORE);
        savingSuccessfullyIcon = new JLabel(image);
        savingSuccessfullySentence = new JLabel("Successfully loaded.");
        btnOK = new JButton("OK");

        btnOK.addActionListener(this);

        this.setLayout(null);

        setBounds();

        addListeners();

        addComponents();

        setBounds(1,1,500,600);
        setTitle("Loading Confirmation Page");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addComponents() {
        add(savingSuccessfullyIcon);
        add(savingSuccessfullySentence);
        add(btnOK);
    }

    public static void main(String[] args) {
        new StaffAdding(new University("Test University"),
                new UniversityStaff("TestStaff",0));
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
        addListenerForAddButton();
    }

    public void addListenerForAddButton() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DstudentOperationMenu(uni,stu);
                setVisible(false);
            }
        });
    }
}


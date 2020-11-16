package ui.gui.staffoperations;

import model.University;
import model.UniversityStaff;
import ui.gui.DstaffOperationMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffViewing extends JFrame implements ActionListener {
    private UniversityStaff staff;
    private University uni;
    private JLabel instruction;
    private JLabel courseList;
    private JButton btnOK;

    public StaffViewing(University uni,UniversityStaff staff) {
        this.staff = staff;
        this.uni = uni;
        init();
    }

    public void init() {
        instruction = new JLabel("You are viewing the current university course list");
        courseList = new JLabel(staff.viewAllCourses(uni));
        btnOK = new JButton("OK");

        btnOK.addActionListener(this);

        this.setLayout(null);

        setBounds();

        addListeners();

        addComponents();

        setBounds(1,1,800,600);
        setTitle("Course Adding Page");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addComponents() {
        add(instruction);
        add(courseList);
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
        instruction.setBounds(20,20,500,20);
        courseList.setBounds(20,50,700,20);
        btnOK.setBounds(20,100,60,20);
    }

    public void addListeners() {
        addListenerForAddButton();
    }

    public void addListenerForAddButton() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DstaffOperationMenu(uni,staff);
                setVisible(false);
                staff.viewAllCourses(uni); //TODO: Remember to comment this line out!!!
            }
        });
    }
}



package ui.gui;

import model.University;
import model.UniversityStaff;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.gui.staffoperations.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DstaffOperationMenu extends JFrame {
    private UniversityStaff staff;
    private University uni;
    private JsonWriter universityJsonWriter;
    private JsonReader universityJsonReader;
    private static final String UNIVERSITY_JSON_STORE = "./data/UBC.json";
    //private static final String STUDENT_JSON_STORE = "./data/Student.json";
    private JButton btnAdd;  //bingo
    private JButton btnRemove; //bingo
    private JButton btnView; //bingo
    private JButton btnSave; //bingo
    private JButton btnLoad; //bingo
    private JButton btnBack; //bingo
    private JButton btnExit; //bingo (without new window, just simple shutting down)

    //EFFECTS:Create a new staff-operation-menu window
    public DstaffOperationMenu(University uni, UniversityStaff staff) {
        this.staff = staff;
        this.uni = uni;
        init();
    }

    //EFFECTS: initialize all the fields
    public void init() {
        universityJsonReader = new JsonReader(UNIVERSITY_JSON_STORE);
        universityJsonWriter = new JsonWriter(UNIVERSITY_JSON_STORE);
        btnAdd = new JButton("Add a course");
        btnRemove = new JButton("Remove a course");
        btnView = new JButton("View all the courses");
        btnSave = new JButton("Save current university course list");
        btnLoad = new JButton("Load current university course list");
        btnBack = new JButton("Back to login page");
        btnExit = new JButton("Exit");

        this.setLayout(new GridLayout(3, 3, 10, 10));

        addComponents();
        processAllActions();

        setBounds(1, 1, 500, 600);
        setTitle("GUIdesign");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //EFFECTS:add all the components to our window
    public void addComponents() {
        add(btnAdd);
        add(btnRemove);
        add(btnView);
        add(btnSave);
        add(btnLoad);
        add(btnBack);
        add(btnExit);
    }

    //EFFECTS: start a single staff-operation-menu window, just for test
    public static void main(String[] args) {
        new DstaffOperationMenu(new University("Test University"),
                new UniversityStaff("TestStaff", 0));
    }

    //EFFECTS: add listener for "add" button
    //         when clicking this button, go to staff-adding window
    public void processAdding() {
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new StaffAdding(uni, staff);
            }
        });
    }

    //EFFECTS: add listener for "remove" button
    //         when clicking this button, go to staff-removing window
    public void processRemoving() {
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new StaffRemoving(uni, staff);
            }
        });
    }

    //EFFECTS: add listener for "view" button
    //         when clicking this button, go to staff-viewing window
    public void processViewing() {
        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new StaffViewing(uni, staff);
            }
        });
    }

    //EFFECTS: add listener for "asve" button
    //         when clicking this button, go to staff-saving window
    public void processSaving() {
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    universityJsonWriter.open();
                    universityJsonWriter.write(uni);
                    universityJsonWriter.close();
                    System.out.println("Saved the course list of " + uni.getUniversityName());
                    setVisible(false);
                    new StaffSaving(uni, staff);
                } catch (FileNotFoundException e1) {
                    System.out.println("Unable to write to file: " + UNIVERSITY_JSON_STORE);
                }
            }
        });
    }

    //EFFECTS: add listener for "load" button
    //         when clicking this button, go to staff-loading window
    public void processLoading() {
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    uni = universityJsonReader.readUniversity();
                    System.out.println("Loaded " + uni.getUniversityName() + " from " + UNIVERSITY_JSON_STORE);
                    setVisible(false);
                    new StaffLoading(uni, staff);
                } catch (IOException e1) {
                    System.out.println("Unable to write to file: " + UNIVERSITY_JSON_STORE);
                }
            }
        });
    }

    //EFFECTS: add listener for "add" button
    //         when clicking this button, stop the whole project
    public void processExit() {
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("successfully logout.");
                System.exit(0);
            }
        });
    }

    //EFFECTS: add listener for "back" button
    //         when clicking this button, go back to the status-choosing window
    public void processBack() {
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BStatusChoosing(uni);
                setVisible(false);
            }
        });
    }

    //EFFECTS: add listeners for all the buttons
    public void processAllActions() {
        processAdding();
        processRemoving();
        processViewing();
        processSaving();
        processLoading();
        processExit();
        processBack();
    }
}

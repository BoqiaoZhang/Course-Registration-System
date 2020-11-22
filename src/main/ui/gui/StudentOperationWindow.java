package ui.gui;

import model.Student;
import model.University;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.gui.operations.studentoperations.StudentLoading;
import ui.gui.operations.studentoperations.StudentSaving;
import ui.gui.operations.studentoperations.StudentSearching;
import ui.gui.operations.studentoperations.StudentViewing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StudentOperationWindow extends JFrame {
    private Student stu;
    private University uni;
    private JsonWriter universityJsonWriter;
    private JsonWriter studentJsonWriter;
    private JsonReader studentJsonReader;
    private static final String UNIVERSITY_JSON_STORE = "./data/UBC.json";
    private static final String STUDENT_JSON_STORE = "./data/Student.json";
    private JButton btnSearch;
    private JButton btnView;
    private JButton btnBack;
    private JButton btnExit;
    private JButton btnSave;
    private JButton btnLoad;

    //EFFECTS:Create a new student-operation-menu window
    public StudentOperationWindow(University uni, Student stu) {
        this.stu = stu;
        this.uni = uni;
        init();
    }

    //EFFECTS: initialize all the fields
    public void init() {
        studentJsonReader = new JsonReader(STUDENT_JSON_STORE);
        studentJsonWriter = new JsonWriter(STUDENT_JSON_STORE);
        universityJsonWriter = new JsonWriter(UNIVERSITY_JSON_STORE);
        btnSearch = new JButton("Search/Register courses"); //bingo
        btnView = new JButton("View/Drop courses");  //bingo
        btnExit = new JButton("Exit"); //bingo
        btnBack = new JButton("Back to login page");  //bingo
        btnSave = new JButton("Save current registered course list");
        btnLoad = new JButton("Load current registered course list");

        this.setLayout(new GridLayout(3, 3, 10, 10));

        addComponents();
        processAllActions();

        setBounds(1, 1, 700, 600);
        setTitle("Student Operation Menu");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //EFFECTS:add all the components to our window
    public void addComponents() {
        add(btnSearch);
        add(btnView);
        add(btnExit);
        add(btnBack);
        add(btnSave);
        add(btnLoad);
    }

    //EFFECTS: start a single student-operation-menu window, just for test
    public static void main(String[] args) {
        new StudentOperationWindow(new University("Test University"),
                new Student("TestStaff"));
    }

    //EFFECTS: add listener for "search" button
    //         when clicking this button, go to student-search window
    public void processSearch() {
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentSearching(uni,stu);
                setVisible(false);
            }
        });
    }

    //EFFECTS: add listener for "view" button
    //         when clicking this button, go to student-view window
    public void processView() {
        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentViewing(uni,stu);
                setVisible(false);
            }
        });
    }

    //EFFECTS: add listener for "exit" button
    //         when clicking this button, stop the whole project
    public void processExit() {
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                new StatusChoosingWindow(uni);
                setVisible(false);
            }
        });
    }

    //EFFECTS: add listener for "save" button
    //         when clicking this button, go to student-saving window
    public void processSaving() {
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    studentJsonWriter.open();
                    studentJsonWriter.write(stu);
                    studentJsonWriter.close();
                    universityJsonWriter.open();
                    universityJsonWriter.write(uni);
                    universityJsonWriter.close();
                    new StudentSaving(uni,stu);
                    setVisible(false);
                } catch (FileNotFoundException e1) {
                    System.out.println("Unable to write to file: " + STUDENT_JSON_STORE);
                }
            }
        });
    }

    //EFFECTS: add listener for "load" button
    //         when clicking this button, go to student-loading window
    public void processLoading() {
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    stu = studentJsonReader.readStudent(uni);
                    new StudentLoading(uni,stu);
                    setVisible(false);
                } catch (IOException e3) {
                    System.out.println("Unable to read from file: " + STUDENT_JSON_STORE);
                }
            }
        });
    }

    //EFFECTS: add listeners for all the buttons
    public void processAllActions() {
        processSearch();
        processView();
        processExit();
        processBack();
        processSaving();
        processLoading();
    }
}

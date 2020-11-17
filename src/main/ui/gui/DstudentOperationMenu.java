package ui.gui;

import model.Student;
import model.University;
import model.UniversityStaff;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.gui.staffoperations.StaffSaving;
import ui.gui.studentoperations.StudentLoading;
import ui.gui.studentoperations.StudentSaving;
import ui.gui.studentoperations.StudentSearching;
import ui.gui.studentoperations.StudentViewing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DstudentOperationMenu extends JFrame {
    private Student stu;
    private University uni;
    private JsonWriter universityJsonWriter;
    private JsonReader universityJsonReader;
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

    public DstudentOperationMenu(University uni, Student stu) {
        this.stu = stu;
        this.uni = uni;
        init();
    }

    public void init() {
        studentJsonReader = new JsonReader(STUDENT_JSON_STORE);
        studentJsonWriter = new JsonWriter(STUDENT_JSON_STORE);
        universityJsonReader = new JsonReader(UNIVERSITY_JSON_STORE);
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
        setTitle("GUIdesign");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addComponents() {
        add(btnSearch);
        add(btnView);
        add(btnExit);
        add(btnBack);
        add(btnSave);
        add(btnLoad);
    }

    public static void main(String[] args) {
        new DstudentOperationMenu(new University("Test University"),
                new Student("TestStaff"));
    }

    public void processSearch() {
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentSearching(uni,stu);
                setVisible(false);
            }
        });
    }

    public void processView() {
        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentViewing(uni,stu);
                setVisible(false);
            }
        });
    }

    public void processExit() {
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void processBack() {
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BStatusChoosing(uni);
                setVisible(false);
            }
        });
    }

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

    public void processAllActions() {
        processSearch();
        processView();
        processExit();
        processBack();
        processSaving();
        processLoading();
        //TODO: add processSomething here
    }
}

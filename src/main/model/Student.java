package model;

import java.util.ArrayList;

public class Student {
    private String name;                             //The name of a student
    private int studentNumber;                       //The unique student number of a student
    private String universityName;                   //The name of the university where the student is learning
    private ArrayList<Course> registeredCourses;     //The list of courses that have been registered by a student

    //REQUIRES: The student number(studentNum) must be unique (distinctive from that of all other students)
    //EFFECTS: The String name is set to the field called name;
    //         The studentNum is set to the field called studentNumber;
    //         The filed registeredCourses is initialized as a new empty ArrayList<Course>
    public Student(String name, int studentNum, String un) {
        this.name = name;
        this.studentNumber = studentNum;
        this.universityName = un;
        this.registeredCourses = new ArrayList<Course>();
    }

    public String getName() {
        return this.name;
    }

    public int getStudentNumber() {
        return this.studentNumber;
    }

    public String getUniversityName() {
        return this.universityName;
    }

    public ArrayList<Course> getRegisteredCourses() {
        return this.registeredCourses;
    }

    //REQUIRES: The parameter University u must have the same name as the field universityName
    //EFFECTS:  Return ture if the course c is in the university course list; otherwise return false
    public boolean searchCourse(University u, Course c) {
        return false;    //stub
    }

    //REQUIRES: The parameter University u must have the same name as the field universityName ;
    //          The Course c must be in the universityCourseList of University u.
    //EFFECTS:  Return true if there are available seats; otherwise false
    public boolean checkSeats(University u, Course c) {
        return false;     //stub
    }

    //REQUIRES: The parameter Course c must be searched in advance and return true in searchCourse method
    //MODIFY: this, Course c
    //EFFECTS: add Course c to the registeredCourses;
    //         call havingNewRegistration method to change Course c (more details
    //         in the specification of havingNewRegistration)
    public void registerCourse(Course c) {}


    //REQUIRES: Course c should be in the current registeredCourses list
    //MODIFY: this, Course c
    //EFFECTS: remove Course c to the registeredCourses;
    //         call havingNewDrop method to change Course c (more details
    //         in the specification of havingNewDrop)
    public void dropCourse(Course c) {}

    //EFFECTS: print out a string consisting of all the course names of the
    //         courses in the registeredCourses list
    public String viewAllRegisteredCourses() {
        return "";     //stub
    }
}

package model;

import java.util.ArrayList;

public class University {
    private String name;                                 //The name of the university
    private ArrayList<Course> universityCourseList;      //A list of courses available for students to register,
                                                         //which are set up be university staff

    //EFFECTS: instantiate universityCourses as a new empty ArrayList of Course
    public University(String name) {
        this.name = name;
        universityCourseList = new ArrayList<Course>();
    }


    public ArrayList<Course> getUniversityCourseList() {
        return this.universityCourseList;
    }

    //REQUIRES: Course c should not be in the current university course list of
    //MODIFY: this
    //EFFECTS: add a course to the universityCourseList of
    public void newCourseAdded(Course c) {
        this.universityCourseList.add(c);
    }

    //REQUIRES: the course c (parameter) must be in the current university course list
    //MODIFY: this
    //EFFECTS: remove a course from the current universityCourseList of
    public void courseRemoved(Course c) {
        this.universityCourseList.remove(c);
    }

}

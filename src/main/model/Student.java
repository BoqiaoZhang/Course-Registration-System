package model;

import java.util.ArrayList;

public class Student {
    private String name;                             //The name of a student
    private int studentNumber;                       //The unique student number of a student
    private ArrayList<Course> registeredCourses;     //The list of courses that have been registered by a student

    //REQUIRES: The student number(studentNum) must be unique (distinctive from that of all other students)
    //EFFECTS: The String name is set to the field called name;
    //         The studentNum is set to the field called studentNumber;
    //         The filed registeredCourses is initialized as a new empty ArrayList<Course>
    public Student(String name, int studentNum) {
        this.name = name;
        this.studentNumber = studentNum;
        this.registeredCourses = new ArrayList<Course>();
    }

    public String getName() {
        return this.name;
    }

    public int getStudentNumber() {
        return this.studentNumber;
    }


    public ArrayList<Course> getRegisteredCourses() {
        return this.registeredCourses;
    }

    //REQUIRES: The parameter University u must have the same name as the field universityName
    //EFFECTS:  Return ture if the course c is in the university course list; otherwise return false
    public boolean searchCourse(University uni, String str) {
        ArrayList<Course> courseList = uni.getUniversityCourseList();
        for (Course cou : courseList) {
            String courseCode = cou.getCourseName() + Integer.toString(cou.getCourseNumber());
            if (str.equals(courseCode)) {
                return true;
            }
        }
        return false;
    }

    //REQUIRES: The parameter University u must have the same name as the field universityName ;
    //          The Course c must be in the universityCourseList of University u.
    //EFFECTS:  Return true if there are available seats; otherwise false
    public boolean checkSeats(University uni, String str) {
        ArrayList<Course> courseList = uni.getUniversityCourseList();
        for (Course cou : courseList) {
            String courseCode = cou.getCourseName() + Integer.toString(cou.getCourseNumber());
            if (str.equals(courseCode)) {
                if (!cou.getIsFull()) {
                    return true;
                }
            }
        }
        return false;
    }

    //REQUIRES: The parameter Course c must be searched in advance and return true in searchCourse method
    //MODIFY: this, Course c
    //EFFECTS: add Course c to the registeredCourses;
    //         call havingNewRegistration method to change Course c (more details
    //         in the specification of havingNewRegistration)
    public void registerCourse(University uni, String str) {
        ArrayList<Course> courseList = uni.getUniversityCourseList();
        for (Course cou : courseList) {
            String courseCode = cou.getCourseName() + Integer.toString(cou.getCourseNumber());
            if (str.equals(courseCode)) {
                cou.havingNewRegistration();
                this.registeredCourses.add(cou);
            }
        }
    }


    //REQUIRES: Course c should be in the current registeredCourses list
    //MODIFY: this, Course c
    //EFFECTS: remove Course c to the registeredCourses;
    //         call havingNewDrop method to change Course c (more details
    //         in the specification of havingNewDrop)
    public void dropCourse(University uni, String str) {
        ArrayList<Course> courseList = uni.getUniversityCourseList();
        for (Course cou : courseList) {
            String courseCode = cou.getCourseName() + Integer.toString(cou.getCourseNumber());
            if (str.equals(courseCode)) {
                cou.havingNewDrop();
                this.registeredCourses.remove(cou);
            }
        }
    }

    //EFFECTS: print out a string consisting of all the course names of the
    //         courses in the registeredCourses list
    public String viewAllRegisteredCourses() {
        if (this.registeredCourses.size() == 0) {
            return "No registered course";
        } else if (this.registeredCourses.size() == 1) {
            Course c = this.registeredCourses.get(0);
            String courseCode = c.getCourseName() + c.getCourseNumber();
            return "Your registered courses include: " + courseCode;
        } else {
            String result = "";
            Course c = this.registeredCourses.get(0);
            String courseCode = c.getCourseName() + c.getCourseNumber();
            result = "Your registered courses include: " + courseCode;
            for (int i = 1; i < this.registeredCourses.size(); i++) {
                Course cou = this.registeredCourses.get(i);
                String element = ", " + cou.getCourseName() + cou.getCourseNumber();
                result = result + element;
            }
            return result;
        }
    }
}
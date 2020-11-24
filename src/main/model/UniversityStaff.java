package model;

import java.util.ArrayList;

public class UniversityStaff {
    private String name;              //The name of the staff
    private int staffNumber;          //The unique staff number of the staff

    //EFFECTS: construct a universityStaff with its name and staffNumber
    public UniversityStaff(String name, int staffNum) {
        this.name = name;
        this.staffNumber = staffNum;
    }

    //REQUIRES: Course c should not be in the current university course list of university u;
    //          University u must have the same name as the field universityName
    //MODIFY: University u
    //EFFECTS: add a course to the universityCourseList of university u
    public void addNewCourse(University u, Course c) {
        u.getUniversityCourseList().add(c);
    }

    //REQUIRES: the course c (represented by the name nd the num) must be in the current university
    //          course list of university u;
    //MODIFY: University u
    //EFFECTS: remove a course from the current universityCourseList of university u
    public void removeCourse(University u, String name, String num) {
        ArrayList<Course> courseList = u.getUniversityCourseList();
        courseList.removeIf(c -> (c.getCourseName().equals(name)) && (Integer.parseInt(num) == c.getCourseNumber()));
    }

    //EFFECTS: return a string showing all the courses in the university course list
    public String viewAllCourses(University uni) {
        ArrayList<Course> courseList = uni.getUniversityCourseList();
        if (courseList.size() == 0) {
            return "No course found in the current university course list.";
        } else if (courseList.size() == 1) {
            Course c = courseList.get(0);
            String courseCode = c.getCourseName() + Integer.toString(c.getCourseNumber());
            return "The courses in the current university course list include: " + courseCode;
        } else {
            String result;
            Course c = courseList.get(0);
            String courseCode = c.getCourseName() + c.getCourseNumber();
            result = "The courses in the current university course list include: " + courseCode;
            for (int i = 1; i < courseList.size(); i++) {
                Course cou = courseList.get(i);
                String element = ", " + cou.getCourseName() + Integer.toString(cou.getCourseNumber());
                result = result + element;
            }
            return result;
        }
    }
}

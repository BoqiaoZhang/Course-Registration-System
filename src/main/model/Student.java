package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class Student implements Writable {
    private String name;                             //The name of a student
    private ArrayList<Course> registeredCourses;     //The list of courses that have been registered by a student


    //REQUIRES: The student number(studentNum) must be unique (distinctive from that of all other students)
    //EFFECTS: The String name is set to the field called name;
    //         The studentNum is set to the field called studentNumber;
    //         The filed registeredCourses is initialized as a new empty ArrayList<Course>
    public Student(String name) {
        this.name = name;
        this.registeredCourses = new ArrayList<Course>();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Course> getRegisteredCourses() {
        return this.registeredCourses;
    }

    //EFFECTS:  Return ture if a course (represented as str) is in the university course list; otherwise return false
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

    //REQUIRES: The Course (represented as str) must be in the universityCourseList of University uni.
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

    //REQUIRES: The Course(represented as str) must be searched in advance and return true in searchCourse method;
    //          The course(represented as str) must be checked seats first and return true in checkSeats method.
    //MODIFY: this, Course (represented as str)
    //EFFECTS: add Course(represented as str)to the registeredCourses;
    //         change Course information (see more details
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

    //REQUIRES: The Course(represented as str) must be searched in advance and return true in searchCourse method;
    //          The course(represented as str) must be checked seats first and return true in checkSeats method.
    //MODIFY: this
    //EFFECTS: add Course(represented as str)to the registeredCourses;
    //         do NOT change course information.
    public void registerCourseForLoading(University uni, String str) {
        ArrayList<Course> courseList = uni.getUniversityCourseList();
        for (Course cou : courseList) {
            String courseCode = cou.getCourseName() + Integer.toString(cou.getCourseNumber());
            if (str.equals(courseCode)) {
                this.registeredCourses.add(cou);
            }
        }
    }


    //REQUIRES: Course c should be in the current registeredCourses list
    //MODIFY: this, Course(represented as str)
    //EFFECTS: remove Course(represented as str) to the registeredCourses;
    //         change Course information (more details
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
            String courseCode = c.getCourseName() + Integer.toString(c.getCourseNumber());
            return "Your registered courses include: " + courseCode;
        } else {
            String result;
            Course c = this.registeredCourses.get(0);
            String courseCode = c.getCourseName() + c.getCourseNumber();
            result = "Your registered courses include: " + courseCode;
            for (int i = 1; i < this.registeredCourses.size(); i++) {
                Course cou = this.registeredCourses.get(i);
                String element = ", " + cou.getCourseName() + Integer.toString(cou.getCourseNumber());
                result = result + element;
            }
            return result;
        }
    }

    //EFFECTS:returns a student as a jason
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("registeredCourses", coursesToJson());
        return json;
    }

    // EFFECTS: returns registeredCourseList of a student as a JSON array
    private JSONArray coursesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course c : this.registeredCourses) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}

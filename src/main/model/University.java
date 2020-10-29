package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class University implements Writable {
    private String name;                                 //The name of the university
    private ArrayList<Course> universityCourseList;      //A list of courses available for students to register,
    //which are set up be university staff

    //EFFECTS: instantiate universityCourses as a new empty ArrayList of Course
    public University(String name) {
        this.name = name;
        universityCourseList = new ArrayList<Course>();
    }

    public String getUniversityName() {
        return this.name;
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

    //EFFECTS"returns a University as a jasonObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("universityCourseList", coursesToJson());
        return json;
    }

    // EFFECTS: returns UniversityCourseList of a university as a JSON array
    private JSONArray coursesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course c : this.universityCourseList) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}

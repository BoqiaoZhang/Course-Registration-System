package model;

import org.json.JSONObject;
import persistence.Writable;

public class Course implements Writable {
    private String courseName;                //the name of this course
    private int courseNumber;                 //the special course number of a course
    private int seatsRemaining;               //the number of total seats remaining
    private int seatsRegistered;              //the number of total seats registered
    private boolean isFull;                   //course status: true if the course is full(seatsRemaining=0),
                                              //               false otherwise

    //REQUIRES: courseName(cn) must have non-zero length;
    //          num must be greater than zero
    //          the new course should be unique (either courseName or
    //          courseNumber is distinctive from these two of any other courses)
    //EFFECTS: construct a course based on the given information and initialize seatsRegistered to 0, isFull to false.
    public Course(String cn, int num, int sr) {
        this.courseName = cn;
        this.courseNumber = num;
        this.seatsRemaining = sr;
        this.seatsRegistered = 0;
        this.isFull = false;
    }

    public Course(String cn, int num, int seatsRemaining,int seatsRegistered,boolean isFull) {
        this.courseName = cn;
        this.courseNumber = num;
        this.seatsRemaining = seatsRemaining;
        this.seatsRegistered = seatsRegistered;
        this.isFull = isFull;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public int getCourseNumber() {
        return this.courseNumber;
    }

    public int getSeatsRemaining() {
        return this.seatsRemaining;
    }

    public boolean getIsFull() {
        return this.isFull;
    }

    public int getSeatsRegistered() {
        return this.seatsRegistered;
    }



    //REQUIRES: the seatsRemaining must be greater than 0.
    //MODIFY: this
    //EFFECTS: reducing 1 to seatsRemaining;
    //         adding 1 to seatsRegistered.
    public void havingNewRegistration() {
        this.seatsRegistered = seatsRegistered + 1;
        this.seatsRemaining = seatsRemaining - 1;
        if (seatsRemaining == 0) {
            isFull = true;
        }
    }

    //REQUIRES: the seatsRegistered must be greater than 0.
    //MODIFY: this
    //EFFECTS: adding 1 to seatsRemaining;
    //         reducing 1 to seatsRegistered.
    public void havingNewDrop() {
        boolean fullOrNot = this.isFull;
        this.seatsRemaining = seatsRemaining + 1;
        this.seatsRegistered = seatsRegistered - 1;
        if (fullOrNot) {
            this.isFull = false;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", courseName);
        json.put("number", Integer.toString(courseNumber));
        json.put("seatsRemaining", Integer.toString(seatsRemaining));
        json.put("seatsRegistered", Integer.toString(seatsRegistered));
        json.put("isFull", Boolean.toString(isFull));
        return json;
    }
}

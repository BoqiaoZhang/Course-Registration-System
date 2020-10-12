package model;

public class Course {
    private String courseName;                //the name of this course
    private int courseNumber;                 //the special course number of a course
    private int seatsRemaining;               //the number of total seats remaining
    private int generalSeatsRemaining;        //the number of general seats remaining
    private int restrictedSeatsRemaining;     //the number of restricted seats remaining
    private String restrictions;              //a description about students who could use restricted seats
    private int seatsRegistered;              //the number of total seats registered
    private boolean isFull;                   //course status: true if the course is full(seatsRemaining=0),
                                              //               false otherwise

    //REQUIRES: courseName(cn) must have non-zero length;
    //          sr must be equal to gsr+rsr;
    //          the new course should be unique (either courseName or
    //          courseNumber is distinctive from these two of any other courses)
    //EFFECTS: construct a course based on the given information and initialize seatsRegistered to 0, isFull to false.
    public Course(String cn, int num, int sr, int gsr, int rsr, String res) {
        this.courseName = cn;
        this.courseNumber = num;
        this.seatsRemaining = sr;
        this.generalSeatsRemaining = gsr;
        this.restrictedSeatsRemaining = rsr;
        this.restrictions = res;
        this.seatsRegistered = 0;
        this.isFull = false;
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

    public int getGeneralSeatsRemaining() {
        return this.generalSeatsRemaining;
    }

    public int getRestrictedSeatsRemaining() {
        return this.restrictedSeatsRemaining;
    }

    public String getRestrictions() {
        return this.restrictions;
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
    //         reducing 1 to either generalSeatsRemaining or restrictedSeatsRemaining;
    //         adding 1 to seatsRegistered.
    public void havingNewRegistration() {}

    //REQUIRES: the seatsRegistered must be greater than 0.
    //MODIFY: this
    //EFFECTS: adding 1 to seatsRemaining;
    //         adding 1 to either generalSeatsRemaining or restrictedSeatsRemaining;
    //         reducing 1 to seatsRegistered.
    public void havingNewDrop() {}


}

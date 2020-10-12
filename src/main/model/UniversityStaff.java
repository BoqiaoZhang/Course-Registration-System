package model;

public class UniversityStaff {
    private String name;              //The name of the staff
    private int staffNumber;          //The unique staff number of the staff
    private String universityName;    //The name of the university where the staff work

    public UniversityStaff(String name, int staffNum, String un) {
        this.name = name;
        this.staffNumber = staffNum;
        this.universityName = un;
    }

    //REQUIRES: Course c should not be in the current university course list of university u;
    //          University u must have the same name as the field universityName
    //MODIFY: University u
    //EFFECTS: add a course to the universityCourseList of university u
    public void addNewCourse(University u, Course c) {}

    //REQUIRES: the course c (parameter) must be in the current university course list of university u;
    //          University u must have the same name as the field universityName
    //MODIFY: University u
    //EFFECTS: remove a course from the current universityCourseList of university u
    public void removeCourse(University u, Course c) {}
}

package persistence;

import model.Course;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCourse(String name, int num, int rem, int reg, boolean full, Course course) {
        assertEquals(name, course.getCourseName());
        assertEquals(num, course.getCourseNumber());
        assertEquals(rem, course.getSeatsRemaining());
        assertEquals(reg, course.getSeatsRegistered());
        assertEquals(full, course.getIsFull());
    }
    /*private String courseName;                //the name of this course
    private int courseNumber;                 //the special course number of a course
    private int seatsRemaining;               //the number of total seats remaining
    private int seatsRegistered;              //the number of total seats registered
    private boolean isFull;*/
}


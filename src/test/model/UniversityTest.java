package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UniversityTest {
    private University u1;
    private Course c1;
    private Course c2;

    @BeforeEach
    void setup() {
        u1 = new University("UBC");
        c1 = new Course("MATH",101,130);
        c2 = new Course("SCIE",113,50);
    }

    @Test
    void testAddNewCourseOneCourse() {
        assertEquals(0,u1.getUniversityCourseList().size());
        u1.newCourseAdded(c1);
        assertEquals(1,u1.getUniversityCourseList().size());
        assertTrue(u1.getUniversityCourseList().contains(c1));
    }

    @Test
    void testAddNewCourseMultipleCourses() {
        assertEquals(0,u1.getUniversityCourseList().size());
        u1.newCourseAdded(c1);
        u1.newCourseAdded(c2);
        assertEquals(2,u1.getUniversityCourseList().size());
        assertTrue(u1.getUniversityCourseList().contains(c1));
        assertTrue(u1.getUniversityCourseList().contains(c2));
    }

    @Test
    void testRemoveCourse() {
        assertEquals(0,u1.getUniversityCourseList().size());
        u1.newCourseAdded(c1);
        u1.newCourseAdded(c2);
        assertEquals(2,u1.getUniversityCourseList().size());
        u1.courseRemoved(c1);
        assertEquals(1,u1.getUniversityCourseList().size());
        u1.courseRemoved(c2);
        assertEquals(0,u1.getUniversityCourseList().size());
    }


}

package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UniversityTest {
    private University u1;
    private University u2;
    private Course c1;
    private Course c2;

    @BeforeEach
    void setup() {
        u1 = new University("UBC");
        u2 = new University("UT");
        c1 = new Course("MATH",101,130);
        c2 = new Course("SCIE",113,50);
    }

    @Test
    void testAddNewCourseOneCourse() {
        assertEquals(0,u1.getUniversityCourseList().size());
        u1.addNewCourse(c1);
        assertEquals(1,u1.getUniversityCourseList().size());
        assertTrue(u1.getUniversityCourseList().contains(u1));
    }

    @Test
    void testAddNewCourseMultipleCourses() {
        assertEquals(0,u2.getUniversityCourseList().size());
        u2.addNewCourse(c1);
        u2.addNewCourse(c2);
        assertEquals(2,u2.getUniversityCourseList().size());
        assertTrue(u1.getUniversityCourseList().contains(u1));
        assertTrue(u1.getUniversityCourseList().contains(u2));
    }

    @Test
    void testRemoveCourse() {
        assertEquals(0,u2.getUniversityCourseList().size());
        u2.addNewCourse(c1);
        u2.addNewCourse(c2);
        assertEquals(2,u2.getUniversityCourseList().size());
        u2.removeCourse(c1);
        assertEquals(1,u2.getUniversityCourseList().size());
        u2.removeCourse(c2);
        assertEquals(0,u2.getUniversityCourseList().size());
    }

    @Test
    void testSearchingTrue() {
        assertEquals(0,u2.getUniversityCourseList().size());
        u2.addNewCourse(c1);
        u2.addNewCourse(c2);
        assertTrue(u2.searching(c1));
        assertTrue(u2.searching(c2));
    }

    @Test
    void testSearchingFalse() {
        u2.addNewCourse(c1);
        assertFalse(u2.searching(c2));
        assertFalse(u1.searching(c1));
    }
}

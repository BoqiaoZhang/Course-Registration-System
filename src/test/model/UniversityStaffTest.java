package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UniversityStaffTest {
    private UniversityStaff staff1;
    private UniversityStaff staff2;
    private University u1;
    private Course c1;
    private Course c2;

    @BeforeEach
    void setup() {
        staff1 = new UniversityStaff("Sujatha",12345);
        staff2 = new UniversityStaff("Billy",678910);
        u1 = new University("UBC");
        c1 = new Course("MATH",101,130);
        c2 = new Course("SCIE",113,50);
    }

    @Test
    void testAddNewCourseOneCourse() {
        assertEquals(0,u1.getUniversityCourseList().size());
        staff1.addNewCourse(u1,c1);
        assertEquals(1,u1.getUniversityCourseList().size());
        assertTrue(u1.getUniversityCourseList().contains(c1));
    }

    @Test
    void testAddNewCourseMultipleCourses() {
        assertEquals(0,u1.getUniversityCourseList().size());

        staff2.addNewCourse(u1,c1);
        assertEquals(1,u1.getUniversityCourseList().size());
        assertTrue(u1.getUniversityCourseList().contains(c1));

        staff2.addNewCourse(u1,c2);
        assertEquals(2,u1.getUniversityCourseList().size());
        assertTrue(u1.getUniversityCourseList().contains(c1));
        assertTrue(u1.getUniversityCourseList().contains(c2));
    }

    @Test
    void testRemoveCourse() {
        assertEquals(0,u1.getUniversityCourseList().size());

        staff2.addNewCourse(u1,c1);
        staff2.addNewCourse(u1,c2);

        staff2.removeCourse(u1,c1);
        assertEquals(1,u1.getUniversityCourseList().size());
        assertFalse(u1.getUniversityCourseList().contains(c1));
        assertTrue(u1.getUniversityCourseList().contains(c2));

        staff2.removeCourse(u1,c2);
        assertEquals(0,u1.getUniversityCourseList().size());
        assertFalse(u1.getUniversityCourseList().contains(c1));
        assertFalse(u1.getUniversityCourseList().contains(c2));
    }
}

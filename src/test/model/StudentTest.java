package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    private Student s1;
    private Student s2;
    private Student s3;
    private University u1;
    private University u2;
    private Course c1;
    private Course c2;
    private Course c3;
    private UniversityStaff staff1;
    private UniversityStaff staff2;

    @BeforeEach
    void setup() {
        s1 = new Student("Bill",12345678,"UBC");
        s2 = new Student("Steven",11335577,"UBC");
        s3 = new Student("Matt",246810,"UCL");
        u1 = new University("UBC");
        u2 = new University("UCL");
        c1 = new Course("CPSC",121,150);
        c2 = new Course("MATH",100,200);
        c3 = new Course("EOSC",103,50);
        staff1 = new UniversityStaff("Billy",12345,"UBC");
        staff1.addNewCourse(u1,c1);
        staff2 = new UniversityStaff("Micheal",5678,"UCL");
        staff2.addNewCourse(u2,c2);
        staff2.addNewCourse(u2,c3);
    }

    @Test
    void testSearchCourseTrue() {
        assertTrue(s1.searchCourse(u1,c1));
        assertTrue(s3.searchCourse(u2,c2));
    }

    @Test
    void testSearchCourseFalse() {
        assertFalse(s2.searchCourse(u1,c2));
        assertFalse(s3.searchCourse(u2,c1));
    }

    @Test
    void testCheckSeatsTrue() {
        assertTrue(s1.checkSeats(u1,c1));
        assertTrue(s3.checkSeats(u2,c2));
    }

    @Test
    void testCheckSeatsFalse() {
        assertTrue(s1.checkSeats(u1,c1));
        for(int i = 0; i< 150; i++) {
            c1.havingNewRegistration();
        }
        assertFalse(s1.checkSeats(u1,c1));
    }

    @Test
    void testRegisterCourse() {
        assertEquals(0,s1.getRegisteredCourses().size());
        s1.registerCourse(c1);
        assertEquals(1,s1.getRegisteredCourses().size());
        assertTrue(s1.getRegisteredCourses().contains(c1));
    }

    @Test
    void testDropCourse() {
        assertEquals(0,s3.getRegisteredCourses().size());
        s3.registerCourse(c2);
        assertEquals(1,s3.getRegisteredCourses().size());
        assertTrue(s3.getRegisteredCourses().contains(c2));

        s3.dropCourse(c2);
        assertEquals(0,s3.getRegisteredCourses().size());
    }

    @Test
    void TestViewAllRegisteredCoursesNoCourse() {
        assertEquals("No registered course",s1.viewAllRegisteredCourses());
    }

    @Test
    void TestViewAllRegisteredCoursesOneCourse() {
        s3.registerCourse(c2);
        assertEquals("Your registered courses include: MATH100",s3.viewAllRegisteredCourses());
    }

    @Test
    void TestViewAllRegisteredCoursesMultipleCourses() {
        s3.registerCourse(c2);
        assertEquals("Your registered courses include: MATH100",s3.viewAllRegisteredCourses());
        s3.registerCourse(c3);
        assertEquals("Your registered courses include: MATH100, EOSC103",s3.viewAllRegisteredCourses());
    }
}

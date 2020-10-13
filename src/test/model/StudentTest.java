package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    private Student s1;
    private Student s2;
    private Student s3;
    private Course c1;
    private Course c2;
    private Course c3;
    private UniversityStaff staff1;
    private UniversityStaff staff2;
    private University u;

    @BeforeEach
    void setup() {
        s1 = new Student("Bill",12345678);
        s2 = new Student("Steven",11335577);
        s3 = new Student("Matt",246810);
        c1 = new Course("CPSC",121,150);
        c2 = new Course("MATH",100,200);
        c3 = new Course("EOSC",103,50);
        staff1 = new UniversityStaff("Billy",12345);
        staff2 = new UniversityStaff("Micheal",5678);
        u = new University("UBC");
    }

    @Test
    void testSearchCourseTrue() {
        staff1.addNewCourse(u,c1);
        staff2.addNewCourse(u,c2);
        assertTrue(s1.searchCourse(u,"CPSC121"));
        assertTrue(s3.searchCourse(u,"MATH100"));
    }

    @Test
    void testSearchCourseFalse() {
        assertFalse(s2.searchCourse(u,"MATH100"));
        assertFalse(s3.searchCourse(u,"CPSC121"));
    }

    @Test
    void testCheckSeatsTrue() {
        staff1.addNewCourse(u,c1);
        staff2.addNewCourse(u,c2);
        assertTrue(s1.checkSeats(u,"CPSC121"));
        assertTrue(s3.checkSeats(u,"MATH100"));
    }

    @Test
    void testCheckSeatsFalse() {
        staff1.addNewCourse(u,c1);
        assertTrue(s1.checkSeats(u,"CPSC121"));
        for(int i = 0; i< 150; i++) {
            c1.havingNewRegistration();
        }
        assertFalse(s1.checkSeats(u,"CPSC121"));
    }

    @Test
    void testRegisterCourse() {
        assertEquals(0,s1.getRegisteredCourses().size());
        staff1.addNewCourse(u,c1);
        s1.registerCourse(u,"CPSC121");
        assertEquals(1,s1.getRegisteredCourses().size());
        assertTrue(s1.getRegisteredCourses().contains(c1));
    }

    @Test
    void testDropCourse() {
        assertEquals(0,s3.getRegisteredCourses().size());
        staff1.addNewCourse(u,c2);
        s3.registerCourse(u,"MATH100");
        assertEquals(1,s3.getRegisteredCourses().size());
        assertTrue(s3.getRegisteredCourses().contains(c2));

        s3.dropCourse(u,"MATH100");
        assertEquals(0,s3.getRegisteredCourses().size());
    }

    @Test
    void TestViewAllRegisteredCoursesNoCourse() {
        assertEquals("No registered course",s1.viewAllRegisteredCourses());
    }

    @Test
    void TestViewAllRegisteredCoursesOneCourse() {
        staff2.addNewCourse(u,c2);
        s3.registerCourse(u,"MATH100");
        assertEquals("Your registered courses include: MATH100",s3.viewAllRegisteredCourses());
    }

    @Test
    void TestViewAllRegisteredCoursesMultipleCourses() {
        staff1.addNewCourse(u,c2);
        s3.registerCourse(u,"MATH100");
        assertEquals("Your registered courses include: MATH100",s3.viewAllRegisteredCourses());

        staff2.addNewCourse(u,c3);
        s3.registerCourse(u,"EOSC103");
        assertEquals("Your registered courses include: MATH100, EOSC103",s3.viewAllRegisteredCourses());
    }
}

package persistence;

import model.Course;
import model.Student;
import model.University;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testUniversityReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.University.json");
        try {
            University u = reader.readUniversity();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testStudentReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.Student.json");
        try {
            Student s = reader.readStudent(new University("UBC"));
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyUniversityCourseList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCourseList.json");
        try {
            University u = reader.readUniversity();
            assertEquals("McGill", u.getUniversityName());
            assertEquals(0, u.getUniversityCourseList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyStudentCourseList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyStudentCourseList.json");
        try {
            Student s = reader.readStudent(new University("UBC"));
            assertEquals("Billy", s.getName());
            assertEquals(0, s.getRegisteredCourses().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralUniversityCourseList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCourseList.json");
        try {
            University u = reader.readUniversity();
            assertEquals("UBC", u.getUniversityName());
            List<Course> courses = u.getUniversityCourseList();
            assertEquals(2, courses.size());
            checkCourse("CPSC", 110,100,0,false , courses.get(0));
            checkCourse("WRDS", 150,0,20,true, courses.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralStudentCourseList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralStudentCourseList.json");
        University u = new University("UCL");
        Course c1 = new Course("CPSC",110,100,0,false);
        Course c2 = new Course("WRDS",150,1,19,false);
        u.newCourseAdded(c1);
        u.newCourseAdded(c2);
        try {
            Student s = reader.readStudent(u);
            assertEquals("Billy", s.getName());
            List<Course> registeredCourses = s.getRegisteredCourses();
            assertEquals(2, registeredCourses.size());
            checkCourse("CPSC", 110,99,1,false , registeredCourses.get(0));
            checkCourse("WRDS", 150,0,20,true, registeredCourses.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

package persistence;

import model.Course;
import model.University;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.Student.json");
        try {
            University u = reader.readUniversity();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
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
    void testReaderGeneralWorkRoom() {
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
}

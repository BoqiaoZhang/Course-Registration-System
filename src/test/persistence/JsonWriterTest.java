package persistence;

import model.Course;
import model.University;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            University u = new University("My University");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCourseList() {
        try {
            University u = new University("My University");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCourseList.json");
            writer.open();
            writer.write(u);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCourseList.json");
            u = reader.read();
            assertEquals("My University", u.getUniversityName());
            assertEquals(0, u.getUniversityCourseList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralCourseList() {
        try {
            University u = new University("UBC");
            u.newCourseAdded(new Course("CPSC", 210, 100,0,false));
            u.newCourseAdded(new Course("MATH",100,150,50,false));
            u.newCourseAdded(new Course("EOSC",101,0,50,true));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCourseList.json");
            writer.open();
            writer.write(u);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCourseList.json");
            u = reader.read();
            assertEquals("UBC", u.getUniversityName());
            List<Course> courses = u.getUniversityCourseList();
            assertEquals(3, courses.size());
            checkCourse("CPSC", 210, 100,0,false, courses.get(0));
            checkCourse("MATH", 100, 150,50,false, courses.get(1));
            checkCourse("EOSC", 101, 0,50,true, courses.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

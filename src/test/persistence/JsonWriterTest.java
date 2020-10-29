package persistence;

import model.Course;
import model.Student;
import model.University;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterUniversityInvalidFile() {
        try {
            University u = new University("My University");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.Student.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterStudentInvalidFile() {
        try {
            Student s = new Student("Bill");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.Student.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyUniversityCourseList() {
        try {
            University u = new University("My University");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCourseList.json");
            writer.open();
            writer.write(u);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCourseList.json");
            u = reader.readUniversity();
            assertEquals("My University", u.getUniversityName());
            assertEquals(0, u.getUniversityCourseList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyStudentCourseList() {
        try {
            Student s = new Student("Bill");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyStudentCourseList.json");
            writer.open();
            writer.write(s);
            writer.close();
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralStudentCourseList() {
        try {
            Student s = new Student("Billy");
            University university = new University("myUniversity");
            Course c = new Course("MATH",100,50);
            university.newCourseAdded(c);
            s.registerCourse(university,"MATH100");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralStudentCourseList.json");
            writer.open();
            writer.write(s);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralStudentCourseList.json");
            Student s1 = reader.readStudent(university);
            assertEquals("Billy", s.getName());
            List<Course> courses = s.getRegisteredCourses();
            assertEquals(1, courses.size());
            checkCourse("MATH", 100, 49,1,false, courses.get(0));
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
            u = reader.readUniversity();
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

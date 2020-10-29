package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Course;
import model.University;
import org.json.*;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public University read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUniversity(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private University parseUniversity(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        University u = new University(name);
        addCourses(u, jsonObject);
        return u;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addCourses(University u, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("universityCourseList");
        for (Object json : jsonArray) {
            JSONObject nextCourse = (JSONObject) json;
            addCourse(u, nextCourse);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addCourse(University u, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String number = jsonObject.getString("number");
        String remaining = jsonObject.getString("seatsRemaining");
        String registered = jsonObject.getString("seatsRegistered");
        String full = jsonObject.getString("isFull");

        int courseNumber = Integer.parseInt(number);
        int seatsRemaining = Integer.parseInt(remaining);
        int seatsRegistered = Integer.parseInt(registered);
        boolean isFull = Boolean.parseBoolean(full);
        Course course = new Course(name, courseNumber, seatsRemaining, seatsRegistered, isFull);
        u.newCourseAdded(course);
    }
}

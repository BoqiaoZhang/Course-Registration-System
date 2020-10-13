package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    private Course c1;
    private Course c2;
    private Course c3;
    private Course c4;
    private Course c5;

    @BeforeEach
    void setup() {
        c1 = new Course("CPSC",110,310);
        c2 = new Course("CPSC",121,150);
        c3 = new Course("CPSC",210,400);
        c4 = new Course("MATH",100,200);
        c5 = new Course("MATH",101,100);
    }

    @Test
    void testHavingNewRegistration() {
        assertEquals(0,c1.getSeatsRegistered());
        assertEquals(310,c1.getSeatsRemaining());

        c1.havingNewRegistration();
        assertEquals(1,c1.getSeatsRegistered());
        assertEquals(309,c1.getSeatsRemaining());

        c1.havingNewRegistration();
        assertEquals(2,c1.getSeatsRegistered());
        assertEquals(308,c1.getSeatsRemaining());
    }

    @Test
    void testHavingNewDrop() {
        assertEquals(0,c2.getSeatsRegistered());
        assertEquals(150,c2.getSeatsRemaining());

        for(int i = 0; i < 100; i++) {
            c2.havingNewRegistration();
        }
        assertEquals(100,c2.getSeatsRegistered());
        assertEquals(50,c2.getSeatsRemaining());

        c2.havingNewDrop();
        assertEquals(99,c2.getSeatsRegistered());
        assertEquals(51,c2.getSeatsRemaining());

        c2.havingNewDrop();
        assertEquals(98,c2.getSeatsRegistered());
        assertEquals(52,c2.getSeatsRemaining());
    }
}
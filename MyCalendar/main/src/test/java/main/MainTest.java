package main;

import org.example.Event;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.chrono.ChronoLocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    public void test01_descriptionRDV_PERSONNEL(){
        Event ev = new Event("RDV_PERSONNEL",
                "rendez vous, vous êtes cernés !",
                "patron",
                LocalDateTime.of(2027, Month.APRIL, 21, 17, 30),
                17, "local à vélo", "", 0);

        assertEquals("", ev.description());
    }

    @Test
    public void test02_descriptionREUNION(){
        Event ev = new Event("REUNION",
                "réunion",
                "patron",
                LocalDateTime.of(2027, Month.APRIL, 21, 17, 30),
                10, "la Réunion", "fred, jamy Gourmaud, Jamie Paige", 0);

        assertEquals("", ev.description());
    }

    @Test
    public void test03_descriptionPERIODIQUE(){
        Event ev = new Event("PERIODIQUE",
                "au tableau !",
                "mendeleiev",
                LocalDateTime.of(2027, Month.APRIL, 21, 17, 30),
                7, "IUT Charlemagne", "", 360);

        assertEquals("", ev.description());
    }

}

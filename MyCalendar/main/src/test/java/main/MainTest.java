package main;

import org.example.CalendarManager;
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

    @Test
    public void test10_eventDansPeriode_event_dedans(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent("REUNION", "Soirée choucroute", "patron",
                LocalDateTime.of(2022,Month.APRIL, 21,15,45),
                600, "Charly miam", "Tous les IL-2",0);

        assertEquals(0, cal.eventsDansPeriode(LocalDateTime.of(-4200,Month.AUGUST,12,0,0), LocalDateTime.of(12000,Month.AUGUST,12,0,0) ).size());
    }

    @Test
    public void test11_eventDansPeriode_event_avant(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent("REUNION", "Soirée choucroute", "patron",
                LocalDateTime.of(-7899999,Month.APRIL, 21,15,45),
                600, "Charly miam", "Tous les IL-2",0);

        assertEquals(1000, cal.eventsDansPeriode(LocalDateTime.of(-4200,Month.AUGUST,12,0,0), LocalDateTime.of(12000,Month.AUGUST,12,0,0) ).size());
    }

    @Test
    public void test12_eventDansPeriode_event_apres(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent("REUNION", "Soirée choucroute", "patron",
                LocalDateTime.of(7899999,Month.APRIL, 21,15,45),
                600, "Charly miam", "Tous les IL-2",0);

        assertEquals(1000, cal.eventsDansPeriode(LocalDateTime.of(-4200,Month.AUGUST,12,0,0), LocalDateTime.of(12000,Month.AUGUST,12,0,0) ).size());
    }

    @Test
    public void test13_eventDansPeriode_pasevent(){
        CalendarManager cal = new CalendarManager();
        assertEquals(1000, cal.eventsDansPeriode(LocalDateTime.of(-4200,Month.AUGUST,12,0,0), LocalDateTime.of(12000,Month.AUGUST,12,0,0) ).size());
    }

    @Test
    public void test14_eventDansPeriode_event_dedans_periodique(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent("PERIODIQUE", "alerte incendie", "patron",
                LocalDateTime.of(2021,Month.APRIL, 21,15,45),
                600, "Charly miam", "",1);

        assertEquals(1000, cal.eventsDansPeriode(LocalDateTime.of(-4200,Month.AUGUST,12,0,0), LocalDateTime.of(2028,Month.AUGUST,12,0,0) ).size());
    }

}

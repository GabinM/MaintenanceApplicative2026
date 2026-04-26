import org.example.CalendarManager;
import org.example.event.*;
import org.example.types.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    static EventTitle generic_title = new EventTitle("title");
    static Individual generic_individual = new Individual("individual");
    static EventParty generic_party;
    static EventDate generic_date = new EventDate(LocalDateTime.of(2027, Month.APRIL, 21, 17, 30));
    static EventDuration generic_event_duration = new EventDuration(10);
    static EventPlace generic_place = new EventPlace("place");
    static EventFrequency generic_frequency = new EventFrequency(2);

    public void prepare_test(){
        Individual jamie = new Individual("jamie Paige");
        Individual fred = new Individual("fred");
        Individual jamy = new Individual("Jamy Gourmaud");
        List<Individual> list = new ArrayList<>();
        list.add(jamie);
        list.add(fred);
        list.add(jamy);
        generic_party = new EventParty(list);
    }

    @Test
    public void test01_descriptionRDV_PERSONNEL(){
        Event ev = new PersonnalEvent(
                generic_title,
                generic_individual,
                generic_date,
                generic_event_duration);

        assertEquals("RDV : title à 2027-04-21T17:30", ev.description());
    }

    @Test
    public void test02_descriptionREUNION(){
        prepare_test();
        MeetingEvent ev = new MeetingEvent(
                generic_title,
                generic_individual,
                generic_date,
                generic_event_duration, generic_place, generic_party);

        assertEquals("Réunion : title à place avec jamie Paige, fred, Jamy Gourmaud", ev.description());
    }

    @Test
    public void test03_descriptionPERIODIQUE(){
        PeriodicEvent ev = new PeriodicEvent(
                generic_title,
                generic_individual,
                generic_date,
                generic_event_duration, generic_frequency);

        assertEquals("Événement périodique : title tous les 2 jours", ev.description());
    }

    @Test
    public void test04_descriptionAutre(){
        Event ev = new Event(
                generic_title,
                generic_individual,
                generic_date,
                generic_event_duration);

        assertEquals("", ev.description());
    }

    @Test
    public void test10_eventDansPeriode_event_dedans(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent(EventType.REUNION, generic_title, generic_individual,
                new EventDate(LocalDateTime.of(2022,Month.APRIL, 21,15,45)),
                new EventDuration(600), generic_place, generic_party,generic_frequency);

        assertEquals(1, cal.eventsDansPeriode(LocalDateTime.of(-4200,Month.AUGUST,12,0,0), LocalDateTime.of(12000,Month.AUGUST,12,0,0) ).size());
    }

    @Test
    public void test11_eventDansPeriode_event_avant(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent(EventType.REUNION, generic_title, generic_individual,
                new EventDate(LocalDateTime.of(-7899999,Month.APRIL, 21,15,45)),
                new EventDuration(600), generic_place, generic_party,generic_frequency);

        assertEquals(0, cal.eventsDansPeriode(LocalDateTime.of(-4200,Month.AUGUST,12,0,0), LocalDateTime.of(12000,Month.AUGUST,12,0,0) ).size());
    }

    @Test
    public void test12_eventDansPeriode_event_apres(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent(EventType.REUNION, generic_title, generic_individual,
                new EventDate(LocalDateTime.of(7899999,Month.APRIL, 21,15,45)),
                new EventDuration(600), generic_place, generic_party, generic_frequency);

        assertEquals(0, cal.eventsDansPeriode(LocalDateTime.of(-4200,Month.AUGUST,12,0,0), LocalDateTime.of(12000,Month.AUGUST,12,0,0) ).size());
    }

    @Test
    public void test13_eventDansPeriode_pasevent(){
        CalendarManager cal = new CalendarManager();
        assertEquals(0, cal.eventsDansPeriode(LocalDateTime.of(-4200,Month.AUGUST,12,0,0), LocalDateTime.of(12000,Month.AUGUST,12,0,0) ).size());
    }

    @Test
    public void test14_eventDansPeriode_event_dedans_periodique(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent(EventType.PERIODIQUE, generic_title, generic_individual,
                new EventDate(LocalDateTime.of(2021,Month.APRIL, 21,15,45)),
                generic_event_duration, generic_place, generic_party,generic_frequency);

        assertEquals(1, cal.eventsDansPeriode(LocalDateTime.of(-4200,Month.AUGUST,12,0,0), LocalDateTime.of(2028,Month.AUGUST,12,0,0) ).size());
    }

    @Test
    public void test15_eventDansPeriode_event_avant_periodique(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent(EventType.PERIODIQUE, generic_title, generic_individual,
                new EventDate(LocalDateTime.of(-4201,Month.APRIL, 21,15,45)),
                generic_event_duration, generic_place, generic_party,generic_frequency);

        assertEquals(1, cal.eventsDansPeriode(LocalDateTime.of(-4200,Month.AUGUST,12,0,0), LocalDateTime.of(2028,Month.AUGUST,12,0,0) ).size());
    }

    @Test
    public void test16_eventDansPeriode_event_apres_periodique(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent(EventType.PERIODIQUE, generic_title, generic_individual,
                new EventDate(LocalDateTime.of(2088,Month.APRIL, 21,15,45)),
                generic_event_duration, generic_place, generic_party,generic_frequency);

        assertEquals(0, cal.eventsDansPeriode(LocalDateTime.of(-4200,Month.AUGUST,12,0,0), LocalDateTime.of(2028,Month.AUGUST,12,0,0) ).size());
    }


    @Test
    public void test20_conflit_pasConflit_pasPeriodique(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent(EventType.REUNION, generic_title, generic_individual,
                new EventDate(LocalDateTime.of(2021,Month.APRIL, 21,15,45)),
                generic_event_duration, generic_place, generic_party,generic_frequency);

        cal.ajouterEvent(EventType.RDV_PERSONNEL, generic_title, generic_individual,
                new EventDate(LocalDateTime.of(2048,Month.APRIL, 21,15,45)),
                generic_event_duration, generic_place, generic_party,generic_frequency);

        assertEquals(false, cal.conflit(cal.events.get(0), cal.events.get(1)));
    }

    @Test
    public void test21_conflit_pasConflit_periodique(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent(EventType.PERIODIQUE, generic_title, generic_individual,
                new EventDate(LocalDateTime.of(2021,Month.APRIL, 21,15,45)),
                generic_event_duration, generic_place, generic_party,generic_frequency);

        cal.ajouterEvent(EventType.RDV_PERSONNEL, generic_title, generic_individual,
                new EventDate(LocalDateTime.of(2048,Month.APRIL, 21,15,45)),
                generic_event_duration, generic_place, generic_party,generic_frequency);

        assertEquals(false, cal.conflit(cal.events.get(0), cal.events.get(1)));
    }

    @Test
    public void test22_conflit_pasConflitOfficiel_periodique(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent(EventType.PERIODIQUE, generic_title, generic_individual,
                new EventDate(LocalDateTime.of(2021,Month.APRIL, 21,15,45)),
                generic_event_duration, generic_place, generic_party,generic_frequency);

        cal.ajouterEvent(EventType.PERIODIQUE, generic_title, generic_individual,
                new EventDate(LocalDateTime.of(2021,Month.APRIL, 21,15,45)),
                generic_event_duration, generic_place, generic_party,generic_frequency);

        assertEquals(false, cal.conflit(cal.events.get(0), cal.events.get(1)));
    }

    @Test
    public void test23_conflit_conflit_pasPeriodique(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent(EventType.REUNION, generic_title, generic_individual,
                new EventDate(LocalDateTime.of(2021,Month.APRIL, 21,15,45)),
                generic_event_duration, generic_place, generic_party,generic_frequency);

        cal.ajouterEvent(EventType.RDV_PERSONNEL, generic_title, generic_individual,
                new EventDate(LocalDateTime.of(2021,Month.APRIL, 21,15,45)),
                generic_event_duration, generic_place, generic_party,generic_frequency);

        assertEquals(true, cal.conflit(cal.events.get(0), cal.events.get(1)));
    }

    @Test
    public void test24_conflit_pasconflit_deuxiemePeriodique(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent(EventType.REUNION, generic_title, generic_individual,
                new EventDate(LocalDateTime.of(2021,Month.APRIL, 21,15,45)),
                generic_event_duration, generic_place, generic_party,generic_frequency);

        cal.ajouterEvent(EventType.PERIODIQUE, generic_title, generic_individual,
                new EventDate(LocalDateTime.of(2021,Month.APRIL, 21,15,45)),
                generic_event_duration, generic_place, generic_party,generic_frequency);

        assertEquals(false, cal.conflit(cal.events.get(0), cal.events.get(1)));
    }

    @Test
    public void test25_conflit_conflit_debutE1ApresFinE2(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent(EventType.REUNION, generic_title, generic_individual,
                new EventDate(LocalDateTime.of(2022,Month.APRIL, 21,15,45)),
                generic_event_duration, generic_place, generic_party,generic_frequency);

        cal.ajouterEvent(EventType.REUNION, generic_title, generic_individual,
                new EventDate(LocalDateTime.of(2021,Month.APRIL, 21,15,45)),
                generic_event_duration, generic_place, generic_party,generic_frequency);

        assertEquals(false, cal.conflit(cal.events.get(0), cal.events.get(1)));
    }

    @Test
    public void test30_afficherEvenements(){

        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent(EventType.REUNION, generic_title, generic_individual,
                new EventDate(LocalDateTime.of(2021,Month.APRIL, 21,15,45)),
                generic_event_duration, generic_place, generic_party,generic_frequency);

        cal.ajouterEvent(EventType.PERIODIQUE, generic_title, generic_individual,
                new EventDate(LocalDateTime.of(2021,Month.APRIL, 21,15,45)),
                generic_event_duration, generic_place, generic_party,generic_frequency);
        cal.afficherEvenements();
    }

}

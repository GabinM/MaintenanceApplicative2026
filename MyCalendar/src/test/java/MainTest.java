import org.example.CalendarManager;
import org.example.event.Event;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    public void test01_descriptionRDV_PERSONNEL(){
        Event ev = new Event("RDV_PERSONNEL",
                "rendez vous, vous êtes cernés !",
                "patron",
                LocalDateTime.of(2027, Month.APRIL, 21, 17, 30),
                17, "local à vélo", "", 0);

        assertEquals("RDV : rendez vous, vous êtes cernés ! à 2027-04-21T17:30", ev.description());
    }

    @Test
    public void test02_descriptionREUNION(){
        Event ev = new Event("REUNION",
                "réunion",
                "patron",
                LocalDateTime.of(2027, Month.APRIL, 21, 17, 30),
                10, "la Réunion", "fred, jamy Gourmaud, Jamie Paige", 0);

        assertEquals("Réunion : réunion à la Réunion avec fred, jamy Gourmaud, Jamie Paige", ev.description());
    }

    @Test
    public void test03_descriptionPERIODIQUE(){
        Event ev = new Event("PERIODIQUE",
                "au tableau !",
                "mendeleiev",
                LocalDateTime.of(2027, Month.APRIL, 21, 17, 30),
                7, "IUT Charlemagne", "", 360);

        assertEquals("Événement périodique : au tableau ! tous les 360 jours", ev.description());
    }

    @Test
    public void test04_descriptionAutre(){
        Event ev = new Event("BAGARRE",
                "1v1 sur Krumble",
                "patron",
                LocalDateTime.of(2026, Month.APRIL, 21, 17, 30),
                7, "local à vélo", "patron, salarié", 0);

        assertEquals("", ev.description());
    }

    @Test
    public void test10_eventDansPeriode_event_dedans(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent("REUNION", "Soirée choucroute", "patron",
                LocalDateTime.of(2022,Month.APRIL, 21,15,45),
                600, "Charly miam", "Tous les IL-2",0);

        assertEquals(1, cal.eventsDansPeriode(LocalDateTime.of(-4200,Month.AUGUST,12,0,0), LocalDateTime.of(12000,Month.AUGUST,12,0,0) ).size());
    }

    @Test
    public void test11_eventDansPeriode_event_avant(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent("REUNION", "Soirée choucroute", "patron",
                LocalDateTime.of(-7899999,Month.APRIL, 21,15,45),
                600, "Charly miam", "Tous les IL-2",0);

        assertEquals(0, cal.eventsDansPeriode(LocalDateTime.of(-4200,Month.AUGUST,12,0,0), LocalDateTime.of(12000,Month.AUGUST,12,0,0) ).size());
    }

    @Test
    public void test12_eventDansPeriode_event_apres(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent("REUNION", "Soirée choucroute", "patron",
                LocalDateTime.of(7899999,Month.APRIL, 21,15,45),
                600, "Charly miam", "Tous les IL-2",0);

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
        cal.ajouterEvent("PERIODIQUE", "alerte incendie", "patron",
                LocalDateTime.of(2021,Month.APRIL, 21,15,45),
                600, "Charly miam", "",1);

        assertEquals(1, cal.eventsDansPeriode(LocalDateTime.of(-4200,Month.AUGUST,12,0,0), LocalDateTime.of(2028,Month.AUGUST,12,0,0) ).size());
    }

    @Test
    public void test15_eventDansPeriode_event_avant_periodique(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent("PERIODIQUE", "alerte incendie", "patron",
                LocalDateTime.of(-4201,Month.APRIL, 21,15,45),
                600, "Charly miam", "",1);

        assertEquals(1, cal.eventsDansPeriode(LocalDateTime.of(-4200,Month.AUGUST,12,0,0), LocalDateTime.of(2028,Month.AUGUST,12,0,0) ).size());
    }

    @Test
    public void test16_eventDansPeriode_event_apres_periodique(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent("PERIODIQUE", "alerte incendie", "patron",
                LocalDateTime.of(2088,Month.APRIL, 21,15,45),
                600, "Charly miam", "",1);

        assertEquals(0, cal.eventsDansPeriode(LocalDateTime.of(-4200,Month.AUGUST,12,0,0), LocalDateTime.of(2028,Month.AUGUST,12,0,0) ).size());
    }


    @Test
    public void test20_conflit_pasConflit_pasPeriodique(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent("REUNION", "alerte incendie", "patron",
                LocalDateTime.of(2021,Month.APRIL, 21,15,45),
                600, "Charly miam", "pierre, paul, jacques",0);

        cal.ajouterEvent("RDV_PERSONNEL", "faire les courses", "patron",
                LocalDateTime.of(2048,Month.APRIL, 21,15,45),
                600, "king jouet", "",0);

        assertEquals(false, cal.conflit(cal.events.get(0), cal.events.get(1)));
    }

    @Test
    public void test21_conflit_pasConflit_periodique(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent("PERIODIQUE", "les minijusticiers sur Tfou", "patron",
                LocalDateTime.of(2021,Month.APRIL, 21,15,45),
                40, "salon", "",2);

        cal.ajouterEvent("RDV_PERSONNEL", "faire les courses", "patron",
                LocalDateTime.of(2048,Month.APRIL, 21,15,45),
                600, "king jouet", "",0);

        assertEquals(false, cal.conflit(cal.events.get(0), cal.events.get(1)));
    }

    @Test
    public void test22_conflit_pasConflitOfficiel_periodique(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent("PERIODIQUE", "les minijusticiers sur Tfou", "patron",
                LocalDateTime.of(2021,Month.APRIL, 21,15,45),
                40, "salon", "",2);

        cal.ajouterEvent("PERIODIQUE", "titeuf sur gulli", "patron",
                LocalDateTime.of(2021,Month.APRIL, 21,15,45),
                30, "salon", "",2);

        assertEquals(false, cal.conflit(cal.events.get(0), cal.events.get(1)));
    }

    @Test
    public void test23_conflit_conflit_pasPeriodique(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent("REUNION", "club dorothée", "patron",
                LocalDateTime.of(2021,Month.APRIL, 21,15,45),
                40, "salon", "moi, tous les zamis",0);

        cal.ajouterEvent("RDV_PERSONNEL", "la spéciale d'inspecteur gadget", "patron",
                LocalDateTime.of(2021,Month.APRIL, 21,15,45),
                30, "salon", "",0);

        assertEquals(true, cal.conflit(cal.events.get(0), cal.events.get(1)));
    }

    @Test
    public void test24_conflit_pasconflit_deuxiemePeriodique(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent("REUNION", "club dorothée", "patron",
                LocalDateTime.of(2021,Month.APRIL, 21,15,45),
                40, "salon", "moi, tous les zamis",0);

        cal.ajouterEvent("PERIODIQUE", "la spéciale d'inspecteur gadget", "patron",
                LocalDateTime.of(2021,Month.APRIL, 21,15,45),
                30, "salon", "",8);

        assertEquals(false, cal.conflit(cal.events.get(0), cal.events.get(1)));
    }

    @Test
    public void test25_conflit_conflit_debutE1ApresFinE2(){
        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent("REUNION", "club dorothée", "patron",
                LocalDateTime.of(2022,Month.APRIL, 21,15,45),
                40, "salon", "moi, tous les zamis",0);

        cal.ajouterEvent("REUNION", "la spéciale d'inspecteur gadget", "patron",
                LocalDateTime.of(2021,Month.APRIL, 21,15,45),
                30, "salon", "des gens",0);

        assertEquals(false, cal.conflit(cal.events.get(0), cal.events.get(1)));
    }

    @Test
    public void test30_afficherEvenements(){

        CalendarManager cal = new CalendarManager();
        cal.ajouterEvent("REUNION", "club dorothée", "patron",
                LocalDateTime.of(2021,Month.APRIL, 21,15,45),
                40, "salon", "moi, tous les zamis",0);

        cal.ajouterEvent("PERIODIQUE", "la spéciale d'inspecteur gadget", "patron",
                LocalDateTime.of(2021,Month.APRIL, 21,15,45),
                30, "salon", "",8);
        cal.afficherEvenements();
    }

}

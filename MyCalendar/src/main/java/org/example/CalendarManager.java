package org.example;

import org.example.event.Event;
import org.example.event.Event;
import org.example.event.EventFactory;
import org.example.event.PersonnalEvent;
import org.example.types.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalendarManager {
    public List<Event> events;

    public CalendarManager() {
        this.events = new ArrayList<>();
    }

    public void ajouterEvent(EventType type, EventTitle title, Individual proprietaire, EventDate dateDebut, EventDuration dureeMinutes,
                             EventPlace lieu, EventParty participants, EventFrequency frequenceJours) {
        //Event e = new Event(type, title, proprietaire, dateDebut, dureeMinutes, lieu, participants, frequenceJours);
        //Event e = null;
        EventFactory factory = EventFactory.getInstance();
        Class<?> event = factory.getCorrespondingEvent(type);
        try{

            Method constructor = Arrays.stream(event.getDeclaredMethods()).filter(m -> m.getName().equals("createEvent")).toList().getFirst();
            events.add((Event)constructor.invoke(event, new Object[] {title, proprietaire, dateDebut, dureeMinutes, lieu, participants, frequenceJours}));
        } catch (InvocationTargetException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Event> eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {


        return this.events.stream().filter(event -> event.dansPeriode(new EventDate(debut), new EventDate(fin))).toList();
    }

    public boolean conflit(Event e1, Event e2) {
        LocalDateTime fin1 = e1.getDateDebut().date().plusMinutes(e1.getDureeMinutes().durationMinute());
        LocalDateTime fin2 = e2.getDateDebut().date().plusMinutes(e2.getDureeMinutes().durationMinute());

        return !(e1.getType().equals(EventType.PERIODIQUE) || e2.getType().equals(EventType.PERIODIQUE))
                && (e1.getDateDebut().date().isBefore(fin2) && fin1.isAfter(e2.getDateDebut().date()));
    }

    public void afficherEvenements() {
        for (Event e : events) {
            System.out.println(e.description());
        }
    }

    public void supprimerEvenement(EventId id){

    }
}
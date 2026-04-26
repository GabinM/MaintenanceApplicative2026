package org.example.event;

import com.sun.java.accessibility.util.EventID;
import org.example.types.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Event {

    private final EventType TYPE = EventType.AUTRE;

    protected EventId id;
    protected EventTitle title;
    protected Individual proprietaire;
    protected EventDate dateDebut;
    protected EventDuration dureeMinutes;

    public Event(EventTitle title, Individual proprietaire, EventDate dateDebut, EventDuration dureeMinutes) {
        this.id = EventId.create();
        this.title = title;
        this.dateDebut = dateDebut;
        this.dureeMinutes = dureeMinutes;
        this.proprietaire = proprietaire;
    }

    public static Event createEvent(EventTitle title, Individual proprietaire, EventDate dateDebut, EventDuration dureeMinutes,
                                            EventPlace lieu, EventParty participants, EventFrequency frequenceJours){
        return new Event(title, proprietaire, dateDebut, dureeMinutes);
    }

    public EventDate getDateDebut() {
        return dateDebut;
    }

    public EventDuration getDureeMinutes() {
        return dureeMinutes;
    }

    public String description(){
        return "";
    }

    public boolean dansPeriode(EventDate dateDebut, EventDate dateFin){
        return dateDebut.date().isBefore(this.dateDebut.date())  && dateFin.date().isAfter(this.dateDebut.date());
    }

    public EventType getType() {
        return this.TYPE;
    }

    public EventId getId() {
        return this.id;
    }

}

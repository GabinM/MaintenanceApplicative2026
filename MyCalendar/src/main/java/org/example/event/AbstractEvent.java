package org.example.event;

import org.example.types.EventDate;
import org.example.types.EventDuration;
import org.example.types.EventTitle;
import org.example.types.Individual;

import java.time.LocalDateTime;

public abstract class AbstractEvent {

    protected EventTitle title;
    protected Individual proprietaire;
    protected EventDate dateDebut;
    protected EventDuration dureeMinutes;

    public AbstractEvent(EventTitle title, EventDate dateDebut, EventDuration dureeMinutes, Individual proprietaire) {
        this.title = title;
        this.dateDebut = dateDebut;
        this.dureeMinutes = dureeMinutes;
        this.proprietaire = proprietaire;
    }

    public String description(){
        return "";
    }

}

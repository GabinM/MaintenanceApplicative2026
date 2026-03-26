package org.example.event;

import org.example.types.*;

public class PeriodicEvent extends AbstractEvent{

    protected EventFrequency frequency;

    public PeriodicEvent(EventTitle title, EventDate dateDebut, EventDuration dureeMinutes, Individual proprietaire, EventFrequency frequency) {
        super(title, dateDebut, dureeMinutes, proprietaire);
        this.frequency = frequency;
    }

    @Override
    public String description() {
        return "Événement périodique : " + title + " tous les " + frequency + " jours";
    }
}

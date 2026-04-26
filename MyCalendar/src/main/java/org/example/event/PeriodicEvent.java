package org.example.event;

import org.example.types.*;

public class PeriodicEvent extends Event{

    public final static EventType TYPE = EventType.PERIODIQUE;

    protected EventFrequency frequency;

    public PeriodicEvent(EventTitle title, Individual proprietaire, EventDate dateDebut, EventDuration dureeMinutes, EventFrequency frequency) {
        super(title, proprietaire, dateDebut, dureeMinutes);
        this.frequency = frequency;
    }

    public static PeriodicEvent createEvent(EventTitle title, Individual proprietaire, EventDate dateDebut, EventDuration dureeMinutes,
                                            EventPlace lieu, EventParty participants, EventFrequency frequenceJours){
        return new PeriodicEvent(title, proprietaire, dateDebut, dureeMinutes, frequenceJours);
    }

    @Override
    public String description() {
        return "Événement périodique : " + title.title() + " tous les " + frequency.dayFrequency() + " jours";
    }

    @Override
    public boolean dansPeriode(EventDate dateDebut, EventDate dateFin) {
        EventDate temp = this.dateDebut;
        while (temp.date().isBefore(dateFin.date())) {
            if (!temp.date().isBefore(dateDebut.date())) {
                return true;
            }
            temp = new EventDate(temp.date().plusDays(this.frequency.dayFrequency()));
        }
        return false;
    }

    @Override
    public EventType getType(){
        return EventType.PERIODIQUE;
    }
}

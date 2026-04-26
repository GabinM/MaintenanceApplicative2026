package org.example.event;

import org.example.types.*;

public class PersonnalEvent extends Event {

    public final static EventType TYPE = EventType.RDV_PERSONNEL;

    public PersonnalEvent(EventTitle title, Individual proprietaire, EventDate dateDebut,EventDuration dureeMinutes ) {
        super(title, proprietaire, dateDebut, dureeMinutes);
    }

    public static PersonnalEvent createEvent(EventTitle title, Individual proprietaire, EventDate dateDebut, EventDuration dureeMinutes,
                                            EventPlace lieu, EventParty participants, EventFrequency frequenceJours){
        return new PersonnalEvent(title, proprietaire, dateDebut,dureeMinutes);
    }

    @Override
    public String description() {
        return "RDV : " + title.title() + " à " + dateDebut.date().toString();
    }

    @Override
    public EventType getType(){
        return EventType.RDV_PERSONNEL;
    }
}

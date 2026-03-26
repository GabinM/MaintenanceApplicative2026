package org.example.event;

import org.example.types.*;

public class MeetingEvent extends AbstractEvent {

    protected EventPlace eventPlace;
    protected EventParty eventParty;

    public MeetingEvent(EventTitle title, EventDate dateDebut, EventDuration dureeMinutes, Individual proprietaire, EventPlace eventPlace, EventParty eventParty) {
        super(title, dateDebut, dureeMinutes, proprietaire);
        this.eventPlace = eventPlace;
        this.eventParty = eventParty;
    }

    @Override
    public String description() {
        return "Réunion : " + title + " à " + eventPlace + " avec " + eventParty;
    }
}

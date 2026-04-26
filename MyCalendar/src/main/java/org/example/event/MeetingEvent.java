package org.example.event;

import org.example.types.*;

public class MeetingEvent extends Event {

    public final static EventType TYPE = EventType.REUNION;

    protected EventPlace eventPlace;
    protected EventParty eventParty;

    public MeetingEvent(EventTitle title, Individual proprietaire, EventDate dateDebut, EventDuration dureeMinutes, EventPlace eventPlace, EventParty eventParty) {
        super(title, proprietaire, dateDebut, dureeMinutes);
        this.eventPlace = eventPlace;
        this.eventParty = eventParty;
    }

    public static MeetingEvent createEvent(EventTitle title, Individual proprietaire, EventDate dateDebut, EventDuration dureeMinutes,
                                           EventPlace lieu, EventParty participants, EventFrequency frequenceJours){
        return new MeetingEvent(title, proprietaire, dateDebut, dureeMinutes, lieu, participants);
    }

    @Override
    public String description() {
        StringBuilder party_cipants = new StringBuilder(eventParty.party().getFirst().name());
        for(int i = 1 ; i < eventParty.party().size() ; i++){
            party_cipants.append(", ").append(eventParty.party().get(i).name());
        }
        return "Réunion : " + title.title() + " à " + eventPlace.place() + " avec " + party_cipants;
    }

    @Override
    public EventType getType(){
        return EventType.REUNION;
    }
}

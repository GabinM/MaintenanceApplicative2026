package org.example.event;

import org.example.types.EventDate;
import org.example.types.EventDuration;
import org.example.types.EventTitle;
import org.example.types.Individual;

public class PersonnalEvent extends AbstractEvent {

    public PersonnalEvent(EventTitle title, EventDate dateDebut, EventDuration dureeMinutes, Individual proprietaire) {
        super(title, dateDebut, dureeMinutes, proprietaire);
    }

    @Override
    public String description() {
        return "RDV : " + title + " à " + dateDebut.toString();
    }
}

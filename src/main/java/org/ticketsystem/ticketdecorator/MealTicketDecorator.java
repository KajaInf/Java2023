package org.ticketsystem.ticketdecorator;

import org.ticketsystem.Ticket;

public class MealTicketDecorator extends TicketDecorator {

    public MealTicketDecorator(Ticket basicTicket) {
        super(basicTicket);
    }

    public String getDescription() {
        return super.getDescription() + ", with Meal";
    }

    public double getPrice() {
        return super.getPrice() + 20.0;
    }
}

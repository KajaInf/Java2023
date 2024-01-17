package org.ticketsystem.ticketdecorator;

import org.ticketsystem.Ticket;

public class VIPSeatDecorator extends TicketDecorator {

    public VIPSeatDecorator(Ticket basicTicket) {
        super(basicTicket);
    }

    public String getDescription() {
        return super.getDescription() + ", VIP Seat";
    }

    public double getPrice() {
        return super.getPrice() + 50.0;
    }
}

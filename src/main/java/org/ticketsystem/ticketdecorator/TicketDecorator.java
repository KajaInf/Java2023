package org.ticketsystem.ticketdecorator;

import org.ticketsystem.Ticket;

public abstract class TicketDecorator implements Ticket {

    private final Ticket decoratedTicket;

    public TicketDecorator(Ticket decoratedTicket) {
        this.decoratedTicket = decoratedTicket;
    }

    public String getDescription() {
        return decoratedTicket.getDescription();
    }

    public double getPrice() {
        return decoratedTicket.getPrice();
    }
}

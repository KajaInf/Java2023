package org.ticketsystem;

import org.ticketsystem.ticketdecorator.MealTicketDecorator;
import org.ticketsystem.ticketdecorator.VIPSeatDecorator;

public class TicketReservationSystem {
    public static void main(String[] args) {

        Ticket basicTicket = new BasicTicket(100.0);
        System.out.println("Basic Ticket: " + basicTicket.getDescription() + "; Price: " + basicTicket.getPrice());

        Ticket mealTicket = new MealTicketDecorator(basicTicket);
        System.out.println("Meal Ticket: " + mealTicket.getDescription() + "; Price: " + mealTicket.getPrice());

        Ticket vipTicket = new VIPSeatDecorator(mealTicket);
        System.out.println("VIP Ticket: " + vipTicket.getDescription() + "; Price: " + vipTicket.getPrice());
    }
}

package org.ticketsystem;

public class BasicTicket implements Ticket {
    private final double price;

    public BasicTicket(double price) {
        this.price = price;
    }

    public String getDescription() {
        return "Basic Ticket";
    }

    public double getPrice() {
        return this.price;
    }
}

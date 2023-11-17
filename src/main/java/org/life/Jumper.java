package org.life;

import java.util.Random;

public class Jumper extends Organism {

    private int energy;
    private Position position;
    private Random random = new Random();
    public Board board;

    public Jumper(int energy,Board board) {
        super(energy, board);
        this.energy = energy;
        this.board = board;
    }


    public void move() {
        int newX = position.getX();
        int newY = position.getY();

        // Decide whether to move vertically or horizontally
        boolean moveVertically = random.nextBoolean();

        if (moveVertically) {
            // Move up or down by 1
            newY += random.nextBoolean() ? 2 : -2;
        } else {
            // Move left or right by 1
            newX += random.nextBoolean() ? 2 : -2;
        }
        board.moveOrganism(this,newX,newY);
    }
    public int getEnergy(){return energy;}

    public void eatEnergy(int eaten) {
        this.energy += eaten;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}

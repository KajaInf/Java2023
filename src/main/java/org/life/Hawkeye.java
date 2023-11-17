package org.life;

import java.util.Random;

public class Hawkeye extends Organism{
    private int energy;
    private Position position;
    private Random random = new Random(); //generowanie pseudolosowych liczb
    private Board board;
    private int radius;

    public Hawkeye(int energy,Board board, int radius) {
        super(energy, board);
        this.energy = energy;
        this.board = board;
        this.radius = radius;
    }

    public void setBoard(Board board){
        this.board = board;
    }

    public boolean otherOrganismInRadius(int radius){
        int newX = position.getX();
        int newY = position.getY();

        for (int i = newX - radius; i <= newX + radius; i++){
            for (int j = newY - radius; j <= newY + radius; j++) {
                if(board.isEmpty(i,j)){
                    System.out.println("widze organizm");
                    return true;
                }

            }
        }
        return false;
    }

    public void move() {
        int newX = position.getX();
        int newY = position.getY();

        // Decide whether to move vertically or horizontally
        boolean moveVertically = random.nextBoolean();

        if (moveVertically) {
            // Move up or down by 1
            newY += random.nextBoolean() ? 1 : -1;
        } else {
            // Move left or right by 1
            newX += random.nextBoolean() ? 1 : -1;
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

    public int getRadius() {
        return radius;
    }
}

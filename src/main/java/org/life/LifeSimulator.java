package org.life;

public class LifeSimulator {

  public static void main(String[] args) {
    Board board = new Board(10, 10);
    Organism organism = new Organism(100,board);
    board.addOrganism(organism, 5, 5);
    organism.move();
  }
}
package org.life;

public class LifeSimulator {

  public static void main(String[] args) {
    Board board = new Board(25, 125);
    Organism organism1 = new Organism(100,board);
    Jumper organism2 = new Jumper(20, board);
    board.addOrganism(organism1, 5, 5);
    board.addOrganism(organism2, 3,4);
    for (int i = 0; i < 20; i++){
      organism1.move();

      System.out.println("Org1 : ");
      organism2.move();

      System.out.println("Org2 : " );
    }

  }
}
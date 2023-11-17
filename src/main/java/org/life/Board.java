package org.life;

public class Board {

  private int width;
  private int height;
  private Organism[][] organisms;

  public Board(int width, int height) {
    this.width = width;
    this.height = height;
    this.organisms = new Organism[width][height];
  }

  public void addOrganism(Organism organism, int x, int y) {
    if (organisms[x][y] == null) {
      organisms[x][y] = organism;
      organism.setPosition(new Position(x, y));
    } else {
      System.out.println("Position already occupied!");
    }
  }

  public void moveOrganism(Organism organism, int newX, int newY) {
    if (newX >= 0 && newX < width && newY >= 0 && newY < height) {
      if(organisms[newX][newY] == null){
      organisms[organism.getPosition().getX()][organism.getPosition().getY()] = null;
      organisms[newX][newY] = organism;
      organism.setPosition(new Position(newX, newY));
      }else{
        int eatenEnergy = organisms[newX][newY].getEnergy();
        organisms[newX][newY].setEnergy(0);
        organism.eatEnergy(eatenEnergy);

        organisms[organism.getPosition().getX()][organism.getPosition().getY()] = null;
        organisms[newX][newY] = organism;
        organism.setPosition(new Position(newX, newY));
      }
    } else {
      System.out.println("Invalid move!");
    }
  }

  public boolean isEmpty(int x, int y){
    return organisms[x][y] == null;
  }



}

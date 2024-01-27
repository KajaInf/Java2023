package org.starmap.model;

public class Star {
    private String name;
    private double xPosition;
    private double yPosition;
    private double brightness;

    public void setName(String name) {
        this.name = name;
    }

    public void setXPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public void setYPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    public void setBrightness(double brightness) {
        this.brightness = brightness;
    }


    public Star(String name, double xPosition, double yPosition, double brightness) {
        this.name = name;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.brightness = brightness;
    }

    public String getName() {
        return name;
    }

    public double getXPosition() {
        return xPosition;
    }

    public double getYPosition() {
        return yPosition;
    }

    public double getBrightness() {
        return brightness;
    }

    public boolean ifPresent(Star star){
        if(star != null){
            return true;
        }else {
            return false;
        }
    }

    public void setPosition(double xPosition, double yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }


}

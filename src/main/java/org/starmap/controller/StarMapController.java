package org.starmap.controller;

import org.starmap.model.Constellation;
import org.starmap.model.Star;
import org.starmap.utils.DataLoader;

import java.util.List;

public class StarMapController {
    private List<Star> stars;
    private List<Constellation> constellations;
    private Star starMovement = null;

    public StarMapController(String dataFilePath) {
        this.stars = DataLoader.loadStars(dataFilePath);
        this.constellations = DataLoader.loadConstellations(dataFilePath, stars);
    }

    public List<Star> getStars() {
        return stars;
    }

    public void setStars(List<Star> stars) {
        this.stars = stars;
    }

    public List<Constellation> getConstellations() {
        return constellations;
    }

    public void setConstellations(List<Constellation> constellations) {
        this.constellations = constellations;
    }

    public Star getStarByName(String name) {
        for (Star star : stars) {
            if (star.getName().equalsIgnoreCase(name)) {
                return star;
            }
        }
        return null;
    }

    public Constellation getConstellationByName(String name) {
        for (Constellation constellation : constellations) {
            if (constellation.getName().equalsIgnoreCase(name)) {
                return constellation;
            }
        }
        return null;
    }

    public void addStar(Star star) {
        stars.add(star);
    }

    public void removeStar(String name) {
        stars.removeIf(star -> star.getName().equalsIgnoreCase(name));
    }

    public void addConstellation(Constellation constellation) {
        constellations.add(constellation);
    }

    public void removeConstellation(String name) {
        constellations.removeIf(constellation -> constellation.getName().equalsIgnoreCase(name));
    }

    public void addStarConfiguration(String name, double x, double y) {
        Star star = new Star(name, x, y, 0.87);
        addStar(star);
    }

    public void beginStarMovement(Star star) {
        starMovement = star;
    }

    public void moveStar(double newX, double newY) {
        if (starMovement != null) {
            starMovement.setPosition(newX, newY);
        }
    }

    public void stopMovingStar() {
        starMovement = null;
    }
}

package org.starmap.model;

import java.util.List;

public class Constellation {
    final private String name;
    final private List<Star> stars;

    public Constellation(String name, List<Star> stars) {
        this.name = name;
        this.stars = stars;
    }

    public void addStar(Star star) {
        stars.add(star);
    }

    public void removeStar(Star star) {
        stars.remove(star);
    }

    public String getName() {
        return name;
    }

    public List<Star> getStars() {
        return stars;
    }
}

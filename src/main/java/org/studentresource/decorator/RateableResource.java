package org.studentresource.decorator;

import org.studentresource.StudentResource;

public class RateableResource extends ResourceDecorator {
    public RateableResource(StudentResource decoratedResource) {
        super(decoratedResource);
    }

    private static final double MIN_RATING = 0.0;
    private static final double MAX_RATING = 20.0;

    private double rating;

    public void setRating(double rating) {
        if (rating < MIN_RATING || rating > MAX_RATING) {
            throw new IllegalArgumentException("Rating must be between " + MIN_RATING + " and " + MAX_RATING);
        }
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public StudentResource getDecoratedResource() {
        return super.getDecoratedResource();
    }
}

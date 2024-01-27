package org.studentresource.decorator;

import org.studentresource.StudentResource;

public class CommentableResource extends ResourceDecorator {
    private String comment;

    public CommentableResource(StudentResource decoratedResource) {

        super(decoratedResource);
    }
    public void addComment(String comment) {

        this.comment = comment;
    }

    public String getComment() {

        return comment;
    }

    @Override
    public StudentResource getDecoratedResource() {

        return super.getDecoratedResource();
    }



}

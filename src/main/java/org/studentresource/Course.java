package org.studentresource;

public class Course implements StudentResource {
    private String id;
    private String name;

    // Constructor, getters, setters

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Implement all necessary methods from StudentResource
}
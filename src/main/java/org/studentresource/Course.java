package org.studentresource;

public class Course implements StudentResource {
    private String id;
    private String name;

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

//    @Override
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    @Override
//    public void setName(String name) {
//        this.name = name;
//    }
}

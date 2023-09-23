package com.example.getbetter.model;

public class Habit {

    private String id;
    private String name;
    private Boolean type;

    public Habit() {
    }

    public Habit(String id, String name, Boolean type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Habit(String name, Boolean type) {
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }
}

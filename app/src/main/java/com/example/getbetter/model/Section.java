package com.example.getbetter.model;

public class Section {

    private String id;
    private String habit_id;
    private String name;
    private String bio;

    public Section() {
    }

    public Section(String id, String habit_id, String name, String bio) {
        this.id = id;
        this.habit_id = habit_id;
        this.name = name;
        this.bio = bio;
    }

    public Section(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }

    public String getId() {
        return id;
    }

    public void setId(String uuid) {
        this.id = uuid;
    }

    public String getHabit_id() {
        return habit_id;
    }

    public void setHabit_id(String habit_id) {
        this.habit_id = habit_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}

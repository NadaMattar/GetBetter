package com.example.getbetter.model;

public class HabitOption {

    private String id;
    private String text;
    private String id_section;

    public HabitOption() {
    }

    public HabitOption(String text) {
        this.text = text;
    }

    public HabitOption(String id, String text, String id_section) {
        this.id = id;
        this.text = text;
        this.id_section = id_section;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_section() {
        return id_section;
    }

    public void setId_section(String id_section) {
        this.id_section = id_section;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

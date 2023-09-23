package com.example.getbetter.model;

public class UserHabit {

    private String id;
    private String id_user;
    private String id_habit;
    private String name_habit;
    private String timestamp;
    private String timestamp_end;

    public UserHabit() {
    }

    public UserHabit(String name_habit, String timestamp) {
        this.name_habit = name_habit;
        this.timestamp = timestamp;
    }

    public UserHabit(String name_habit, String timestamp, String timestamp_end) {
        this.name_habit = name_habit;
        this.timestamp = timestamp;
        this.timestamp_end = timestamp_end;
    }

    public UserHabit(String id, String id_user, String id_habit, String name_habit, String timestamp, String timestamp_end) {
        this.id = id;
        this.id_user = id_user;
        this.id_habit = id_habit;
        this.name_habit = name_habit;
        this.timestamp = timestamp;
        this.timestamp_end = timestamp_end;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId_habit() {
        return id_habit;
    }

    public void setId_habit(String id_habit) {
        this.id_habit = id_habit;
    }

    public String getName_habit() {
        return name_habit;
    }

    public void setName_habit(String name_habit) {
        this.name_habit = name_habit;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp_end() {
        return timestamp_end;
    }

    public void setTimestamp_end(String timestamp_end) {
        this.timestamp_end = timestamp_end;
    }
}

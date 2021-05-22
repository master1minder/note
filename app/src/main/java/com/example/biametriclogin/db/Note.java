package com.example.biametriclogin.db;

public class Note {

    private long id;
    private String title;
    private String desc;

    Note(long id, String title, String desc){
        this.id = id;
        this.title = title;
        this.desc = desc;
    }
    public long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }


    public String getDesc() {
        return desc;
    }


//метод вывода в понятном для человека виде
    @Override
    public String toString() {
        return "Title: "+this.title + "\nDescriptions: " + this.desc;
    }
}
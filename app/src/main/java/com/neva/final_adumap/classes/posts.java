package com.neva.final_adumap.classes;

public class posts {

    private String name, content, date;

    public posts(String name, String content, String date){
        this.name = name;
        this.content = content;
        this.date = date;

    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
    public String getDate() {
        return date;
    }
}

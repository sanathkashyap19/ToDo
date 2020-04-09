package com.sanath.todo;

/**
 * Created by Sanath on 31-10-2016.
 */
public class ModelInfo {

    String title, body;
    int ID;

    public ModelInfo(int ID, String title, String body) {
        this.title = title;
        this.body = body;
        this.ID = ID;
    }

    public ModelInfo(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {

        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setID(int ID) {

        this.ID = ID;
    }
}

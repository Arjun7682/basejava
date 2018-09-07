package ru.javawebinar.basejava.model;

import java.io.Serializable;

public class Link implements Serializable {
    private static final long serialVersionUID = 1L;

    private String text;
    private String url;

    public Link(String text, String url) {
        this.text = text;
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

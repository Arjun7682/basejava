package ru.javawebinar.basejava.model;

public class TextSection extends Section {
    private static final long serialVersionUID = 1L;

    private String content;

    public TextSection() {
    }

    public TextSection(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

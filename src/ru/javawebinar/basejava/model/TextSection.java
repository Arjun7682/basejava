package ru.javawebinar.basejava.model;

class TextSection extends Section {
    private String content;

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

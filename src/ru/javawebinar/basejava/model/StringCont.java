package ru.javawebinar.basejava.model;

class StringCont extends SectionCont {
    private String content;

    public StringCont(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

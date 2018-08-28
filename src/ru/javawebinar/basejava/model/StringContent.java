package ru.javawebinar.basejava.model;

class StringContent extends SectionContent {
    private String content;

    public StringContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    protected void print() {
        System.out.println(content);
    }
}

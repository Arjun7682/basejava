package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

class ListSection extends Section {
    private List<String> content = new ArrayList<>();

    public void addTextBlock(String text) {
        content.add(text);
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}

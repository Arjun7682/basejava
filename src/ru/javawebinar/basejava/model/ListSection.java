package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends Section {
    private List<String> content = new ArrayList<>();
    private static final long serialVersionUID = 1L;

    public ListSection() {
    }

    public ListSection(List<String> content) {
        this.content = content;
    }

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

package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class ListContent extends SectionContent {
    private List<String> content = new ArrayList<>();

    public void addTextBlock(String text) {
        content.add(text);
    }
}

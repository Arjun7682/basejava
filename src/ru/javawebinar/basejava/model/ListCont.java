package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

class ListCont extends SectionCont {
    private List<String> content = new ArrayList<>();

    public void addTextBlock(String text) {
        content.add(text);
    }
}

package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class ComboContent extends SectionContent {
    private List<Org> content = new ArrayList<>();

    public void addOrganization(Org org) {
        content.add(org);
    }

    public List<Org> getContent() {
        return content;
    }

    public void setContent(List<Org> content) {
        this.content = content;
    }
}

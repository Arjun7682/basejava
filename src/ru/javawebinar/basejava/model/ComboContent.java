package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class ComboContent extends SectionContent {
    private List<Org> content = new ArrayList<>();

    public void addOrganization(Org org) {
        content.add(org);
    }
}

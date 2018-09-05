package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class OrganizationSection extends Section {
    private List<Organization> content = new ArrayList<>();

    public void addOrganization(Organization org) {
        content.add(org);
    }
}

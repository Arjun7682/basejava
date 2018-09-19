package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class OrganizationSection extends Section {
    private static final long serialVersionUID = 1L;

    private List<Organization> content = new ArrayList<>();

    public OrganizationSection() {
    }

    public OrganizationSection(List<Organization> content) {
        this.content = content;
    }

    public void addOrganization(Organization org) {
        content.add(org);
    }

    public List<Organization> getContent() {
        return content;
    }

    public void setContent(List<Organization> content) {
        this.content = content;
    }
}

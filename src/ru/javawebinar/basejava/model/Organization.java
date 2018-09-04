package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Organization {
    private List<OrgEntry> orgEntries = new ArrayList<>();
    private String company;

    public Organization(Date begin, Date end, String company, String position, String description) {
        orgEntries.add(new OrgEntry(begin, end, position, description));
        this.company = company;
    }

    //public void addPositin

    public class OrgEntry {
        private Date begin;
        private Date end;
        private String position;
        private String description;

        public OrgEntry(Date begin, Date end, String position, String description) {
            this.begin = begin;
            this.end = end;
            this.position = position;
            this.description = description;
        }
    }
}

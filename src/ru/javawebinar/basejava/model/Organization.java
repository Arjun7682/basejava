package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Organization {

    private List<Position> orgEntries;
    private Link company;

    public Organization(Link company, Position... positions) {
        this.orgEntries = new ArrayList<>(Arrays.asList(positions));
        this.company = company;
    }

    public List<Position> getOrgEntries() {
        return orgEntries;
    }

    public void setOrgEntries(List<Position> orgEntries) {
        this.orgEntries = orgEntries;
    }

    public Link getCompany() {
        return company;
    }

    public void setCompany(Link company) {
        this.company = company;
    }

    public void addPosition(LocalDate begin, LocalDate end, String position, String description) {
        if (getOrgEntryIndex(position) == -1) {
            orgEntries.add(new Position(begin, end, position, description));
        }
    }

    private int getOrgEntryIndex(String position) {
        for (int i = 0; i < orgEntries.size(); i++) {
            if (orgEntries.get(i).getTitle().equals(position)) {
                return i;
            }
        }
        return -1;
    }

    public void removePosition(String position) {
        int orgEntryIndex = getOrgEntryIndex(position);
        if (orgEntryIndex != -1) {
            orgEntries.remove(orgEntryIndex);
        }
    }

    public void updatePosition(LocalDate begin, LocalDate end, String position, String description) {
        int orgEntryIndex = getOrgEntryIndex(position);
        if (orgEntryIndex != -1) {
            Position entry = orgEntries.get(orgEntryIndex);
            entry.setBegin(begin);
            entry.setEnd(end);
            entry.setDescription(description);
        }
    }

    public static class Position {
        private LocalDate begin;
        private LocalDate end;
        private String title;
        private String description;

        public Position(LocalDate begin, LocalDate end, String title, String description) {
            this.begin = begin;
            this.end = end;
            this.title = title;
            this.description = description;
        }

        public LocalDate getBegin() {
            return begin;
        }

        public void setBegin(LocalDate begin) {
            this.begin = begin;
        }

        public LocalDate getEnd() {
            return end;
        }

        public void setEnd(LocalDate end) {
            this.end = end;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }


}

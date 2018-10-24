package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Position> orgEntries;
    private Link company;

    public Organization() {
    }

    public Organization(Link company, Position... positions) {
        this.orgEntries = new ArrayList<>(Arrays.asList(positions));
        this.company = company;
    }

    public Organization(Link homePage, List<Position> positions) {
        this.company = homePage;
        this.orgEntries = positions;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(orgEntries, that.orgEntries) &&
                Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orgEntries, company);
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

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Position implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate begin;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate end;
        private String title;
        private String description;

        public Position() {
        }

        public Position(LocalDate begin, LocalDate end, String title, String description) {
            begin = begin.withDayOfMonth(1);
            this.begin = begin;
            if (end == null) {
                end = LocalDate.now();
            }
            end = end.withDayOfMonth(1);
            this.end = end;
            this.title = title;
            this.description = description == null ? "" : description;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return Objects.equals(begin, position.begin) &&
                    Objects.equals(end, position.end) &&
                    Objects.equals(title, position.title) &&
                    Objects.equals(description, position.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(begin, end, title, description);
        }
    }
}

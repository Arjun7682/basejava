package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {

    private List<OrgEntry> orgEntries = new ArrayList<>();
    private Link company;

    public Organization(Date begin, Date end, String companyName, String companyURL, String position, String description) {
        Objects.requireNonNull(companyName, "Company name must not be null");
        company = new Link(companyName, companyURL);
        orgEntries.add(new OrgEntry(begin, end, position, description));
    }

    public List<OrgEntry> getOrgEntries() {
        return orgEntries;
    }

    public void setOrgEntries(List<OrgEntry> orgEntries) {
        this.orgEntries = orgEntries;
    }

    public Link getCompany() {
        return company;
    }

    public void setCompany(Link company) {
        this.company = company;
    }

    public void addPosition(Date begin, Date end, String position, String description) {
        if (getOrgEntryIndex(position) == -1) {
            orgEntries.add(new OrgEntry(begin, end, position, description));
        }
    }

    private int getOrgEntryIndex(String position) {
        for (int i = 0; i < orgEntries.size(); i++) {
            if (orgEntries.get(i).getPosition().equals(position)) {
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

    public void updatePosition(Date begin, Date end, String position, String description) {
        int orgEntryIndex = getOrgEntryIndex(position);
        if (orgEntryIndex != -1) {
            OrgEntry entry = orgEntries.get(orgEntryIndex);
            entry.setBegin(begin);
            entry.setEnd(end);
            entry.setDescription(description);
        }
    }

    public class OrgEntry implements Serializable {
        private Date begin;
        private Date end;
        private String position;
        private String description;

        public OrgEntry(Date begin, Date end, String position, String description) {
            Objects.requireNonNull(begin, "Date begin must not be null");
            Objects.requireNonNull(position, "Position must not be null");
            this.begin = begin;
            this.end = end;
            this.position = position;
            this.description = description;
        }

        public Date getBegin() {
            return begin;
        }

        public void setBegin(Date begin) {
            this.begin = begin;
        }

        public Date getEnd() {
            return end;
        }

        public void setEnd(Date end) {
            this.end = end;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }


}

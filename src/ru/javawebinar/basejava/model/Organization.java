package ru.javawebinar.basejava.model;

import java.util.Date;

public class Organization {
    private Date begin;
    private Date end;
    private Link company;
    private String position;
    private String functions;

    public Organization(Date begin, Date end, String companyName, String companyURL, String position, String functions) {
        this.begin = begin;
        this.end = end;
        company = new Link(companyName, companyURL);
        this.position = position;
        this.functions = functions;
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

    public Link getCompany() {
        return company;
    }

    public void setCompany(Link company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFunctions() {
        return functions;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }
}

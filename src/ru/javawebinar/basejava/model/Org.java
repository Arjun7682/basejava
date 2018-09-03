package ru.javawebinar.basejava.model;

import java.util.Date;

class Org {
    private Date begin;
    private Date end;
    private String company;
    private String position;
    private String functions;

    public Org(Date begin, Date end, String company, String position, String functions) {
        this.begin = begin;
        this.end = end;
        this.company = company;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
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

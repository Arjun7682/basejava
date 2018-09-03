package ru.javawebinar.basejava.model;

enum ContactType {
    PHONE("Тел.:"),
    SKYPE("Skype:"),
    EMAIL("Почта:"),
    LINKEDIN("Linked In:"),
    GITHUB("GitHub:"),
    STACKOVERFLOW("Stackoverflow:"),
    HOMEPAGE("Home page:");

    private String contacts;

    ContactType(String title) {
        this.contacts = title;
    }

    public String getContacts() {
        return contacts;
    }
}

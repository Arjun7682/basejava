package ru.javawebinar.basejava.model;

enum ContactType {
    PHONE("Тел.:"),
    SKYPE("Skype:"),
    EMAIL("Почта:"),
    LINKEDIN("Linked In:"),
    GITHUB("GitHub:"),
    STACKOVERFLOW("Stackoverflow:"),
    HOMEPAGE("Home page:");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

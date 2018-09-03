package ru.javawebinar.basejava.model;

enum SectionType {
    OBJECTIVE("Позиция"),
    PERSONAL("Личные качества"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    private String content;

    SectionType(String title) {
        this.content = title;
    }

    public String getContent() {
        return content;
    }
}

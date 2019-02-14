package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.util.Map;

public class EmptyResume {
    public static Resume initEmptyResume() {
        Resume resume = new Resume();

        Map<SectionType, Section> sections = resume.getSections();

        sections.put(SectionType.OBJECTIVE, new TextSection(""));
        sections.put(SectionType.PERSONAL, new TextSection(""));

        ListSection achievement = new ListSection();
        achievement.addTextBlock("");
        sections.put(SectionType.ACHIEVEMENT, achievement);

        ListSection qualifications = new ListSection();
        qualifications.addTextBlock("");
        sections.put(SectionType.QUALIFICATIONS, qualifications);

        OrganizationSection work = new OrganizationSection();
        work.addOrganization(new Organization(new Link("", ""), new Organization.Position()));
        sections.put(SectionType.EXPERIENCE, work);

        OrganizationSection edu = new OrganizationSection();
        edu.addOrganization(new Organization(new Link("", ""), new Organization.Position()));
        sections.put(SectionType.EDUCATION, edu);

        return resume;
    }
}

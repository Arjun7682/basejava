package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.Map;

public class TestResume {
    public static Resume initTestResume(String uuid) {
        Resume resume = new Resume(uuid, "Григорий Кислин");
        Map<ContactType, String> contacts = resume.getContacts();

        contacts.put(ContactType.PHONE, "+7(921) 855-0482");
        contacts.put(ContactType.SKYPE, "skype:grigory.kislin");
        contacts.put(ContactType.MAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        contacts.put(ContactType.GITHUB, "https://github.com/gkislin");
        contacts.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        contacts.put(ContactType.HOMEPAGE, "http://gkislin.ru/");

        Map<SectionType, Section> sections = resume.getSections();

        TextSection objective = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        sections.put(SectionType.OBJECTIVE, objective);

        TextSection personal = new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        sections.put(SectionType.PERSONAL, personal);

        ListSection achievement = new ListSection();
        achievement.addTextBlock("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", ");
        achievement.addTextBlock("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. ");
        sections.put(SectionType.ACHIEVEMENT, achievement);

        ListSection qualifications = new ListSection();
        qualifications.addTextBlock("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.addTextBlock("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        sections.put(SectionType.QUALIFICATIONS, qualifications);

        OrganizationSection work = new OrganizationSection();
        work.addOrganization(new Organization(
                new Link("Wrike", "https://www.wrike.com/"),
                new Organization.Position(LocalDate.now(),
                        "Старший разработчик (backend)",
                        "Проектирование и разработка онлайн платформы управления проектами Wrike")));
        work.addOrganization(new Organization(
                new Link("RIT Center", null),
                new Organization.Position(LocalDate.now(), LocalDate.now(),
                        "Java архитектор",
                        "Организация процесса разработки системы ERP")));
        sections.put(SectionType.EXPERIENCE, work);

        OrganizationSection edu = new OrganizationSection();

        edu.addOrganization(new Organization(
                new Link("Coursera", "https://www.coursera.org/course/progfun"),
                new Organization.Position(LocalDate.now(),
                        "Functional Programming Principles in Scala\" by Martin Odersky",
                        null)));
        edu.addOrganization(new Organization(
                new Link("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366"),
                new Organization.Position(LocalDate.now(), LocalDate.now(),
                        "Курс Объектно-ориентированный анализ",
                        null),
                new Organization.Position(LocalDate.now(), LocalDate.now(),
                        "Аспирантура (программист С, С++)",
                        null)));
        sections.put(SectionType.EDUCATION, edu);

        return resume;
    }
}

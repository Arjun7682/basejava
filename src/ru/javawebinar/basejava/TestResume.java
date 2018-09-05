package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.util.Date;
import java.util.Map;

public class TestResume {
    public static Resume initTestResume(String uuid) {
        Resume resume = new Resume(uuid, "Григорий Кислин");
        Map<ContactType, Link> contacts = resume.getContacts();

        contacts.put(ContactType.PHONE, new Link("+7(921) 855-0482", null));
        contacts.put(ContactType.SKYPE, new Link("grigory.kislin", "skype:grigory.kislin"));
        contacts.put(ContactType.EMAIL, new Link("gkislin@yandex.ru", "gkislin@yandex.ru"));
        contacts.put(ContactType.LINKEDIN, new Link("Профиль LinkedIn", "https://www.linkedin.com/in/gkislin"));
        contacts.put(ContactType.GITHUB, new Link("Профиль GitHub", "https://github.com/gkislin"));
        contacts.put(ContactType.STACKOVERFLOW, new Link("Профиль Stackoverflow", "https://stackoverflow.com/users/548473"));
        contacts.put(ContactType.HOMEPAGE, new Link("Домашняя страница", "http://gkislin.ru/"));

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
        work.addOrganization(new Organization(new Date(), null,
                "Wrike",
                "https://www.wrike.com/",
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike"));
        work.addOrganization(new Organization(new Date(), new Date(),
                "RIT Center",
                null,
                "Java архитектор",
                "Организация процесса разработки системы ERP"));
        sections.put(SectionType.EXPERIENCE, work);

        OrganizationSection edu = new OrganizationSection();
        edu.addOrganization(new Organization(new Date(), null,
                "Coursera",
                "https://www.coursera.org/course/progfun",
                null,
                "Functional Programming Principles in Scala\" by Martin Odersky"));
        edu.addOrganization(new Organization(new Date(), new Date(),
                "Luxoft",
                "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                null,
                "Курс \"Объектно-ориентированный анализ"));
        sections.put(SectionType.EDUCATION, edu);

        return resume;
    }
}

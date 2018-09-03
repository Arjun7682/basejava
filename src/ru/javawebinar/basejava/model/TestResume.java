package ru.javawebinar.basejava.model;

import java.util.Date;
import java.util.Map;

public class TestResume {
    public static Resume initTestResume(String uuid) {
        Resume resume = new Resume(uuid, "Григорий Кислин");
        Map<ContactType, String> contacts = resume.getContacts();

        contacts.put(ContactType.PHONE, "+7(921) 855-0482");
        contacts.put(ContactType.SKYPE, "grigory.kislin");
        contacts.put(ContactType.EMAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN, "Профиль LinkedIn");
        contacts.put(ContactType.GITHUB, "Профиль GitHub");
        contacts.put(ContactType.STACKOVERFLOW, "Профиль Stackoverflow");
        contacts.put(ContactType.HOMEPAGE, "Домашняя страница");

        Map<SectionType, SectionCont> sections = resume.getSections();

        StringCont objective = new StringCont("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        sections.put(SectionType.OBJECTIVE, objective);

        StringCont personal = new StringCont("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        sections.put(SectionType.PERSONAL, personal);

        ListCont achievement = new ListCont();
        achievement.addTextBlock("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", ");
        achievement.addTextBlock("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. ");
        sections.put(SectionType.ACHIEVEMENT, achievement);

        ListCont qualifications = new ListCont();
        qualifications.addTextBlock("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.addTextBlock("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        sections.put(SectionType.QUALIFICATIONS, qualifications);

        ComboCont work = new ComboCont();
        work.addOrganization(new Organization(new Date(), null,
                "Wrike",
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike"));
        work.addOrganization(new Organization(new Date(), new Date(),
                "RIT Center",
                "Java архитектор",
                "Организация процесса разработки системы ERP"));
        sections.put(SectionType.EXPERIENCE, work);

        ComboCont edu = new ComboCont();
        edu.addOrganization(new Organization(new Date(), null,
                "Coursera",
                null,
                "Functional Programming Principles in Scala\" by Martin Odersky"));
        edu.addOrganization(new Organization(new Date(), new Date(),
                "Luxoft",
                null,
                "Курс \"Объектно-ориентированный анализ"));
        sections.put(SectionType.EDUCATION, edu);

        return resume;
    }
}

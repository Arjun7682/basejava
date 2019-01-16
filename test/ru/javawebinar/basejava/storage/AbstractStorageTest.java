package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    public static final Resume R1 = new Resume(UUID_1, "name1");

    private static final String UUID_2 = "uuid2";
    public static final Resume R2 = new Resume(UUID_2, "name2");

    private static final String UUID_3 = "uuid3";
    public static final Resume R3 = new Resume(UUID_3, "name3");

    public static final Resume R4 = new Resume("uuid4", "Григорий Кислин");

    static {
        R1.setContacts(ContactType.SKYPE, "12345");
        R1.setContacts(ContactType.PHONE, "67890");
        R2.setContacts(ContactType.SKYPE, "12345");
        R2.setContacts(ContactType.PHONE, "67890");
        R3.setContacts(ContactType.SKYPE, "12345");
        R3.setContacts(ContactType.PHONE, "67890");

        Map<ContactType, String> contacts = R4.getContacts();

        contacts.put(ContactType.PHONE, "+7(921) 855-0482");
        contacts.put(ContactType.SKYPE, "skype:grigory.kislin");
        contacts.put(ContactType.EMAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        contacts.put(ContactType.GITHUB, "https://github.com/gkislin");
        contacts.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        contacts.put(ContactType.HOMEPAGE, "http://gkislin.ru/");

        Map<SectionType, Section> sections = R4.getSections();

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
    }

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
        storage.save(R4);
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(4, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(storage.size(), 0);
    }

    @Test
    public void save() throws Exception {
        final Resume newResume = new Resume("uuid5", "name");
        newResume.setContacts(ContactType.SKYPE, "12345");
        newResume.setContacts(ContactType.PHONE, "67890");
        final int sizeBeforeSave = storage.size();
        storage.save(newResume);
        Assert.assertEquals(storage.get("uuid5"), newResume);
        Assert.assertEquals(storage.size(), sizeBeforeSave + 1);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(R1);
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(storage.get("uuid4"), R4);

    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();
        Assert.assertEquals(4, list.size());
        Assert.assertEquals(Arrays.asList(R1, R2, R3, R4), list);
    }

    @Test
    public void update() throws Exception {
        Resume resume = new Resume(UUID_3, "update");
        resume.setContacts(ContactType.SKYPE, "12345");
        resume.setContacts(ContactType.PHONE, "67890");
        storage.update(resume);
        Assert.assertEquals(resume, storage.get(UUID_3));

    }

    @Test(expected = NotExistStorageException.class)
    public void updateExceptionTest() throws Exception {
        Resume resume = new Resume("not exist", "name");
        storage.update(resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        final int size = storage.size();
        storage.delete(UUID_2);
        Assert.assertEquals(storage.size(), size - 1);
        storage.get(UUID_2);
    }

    @Test (expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("none");
    }
}

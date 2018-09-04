package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.TestResume;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractStorageTest {
    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    public static final Resume R1 = new Resume(UUID_1, "name1");

    private static final String UUID_2 = "uuid2";
    public static final Resume R2 = new Resume(UUID_2, "name2");

    private static final String UUID_3 = "uuid3";
    public static final Resume R3 = new Resume(UUID_3, "name3");

    public static final Resume R4 = TestResume.initTestResume("uuid4");

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(storage.size(), 0);
    }

    @Test
    public void save() throws Exception {
        final Resume newResume = new Resume("uuid4", "name");
        final int sizeBeforeSave = storage.size();
        storage.save(newResume);
        Assert.assertEquals(storage.get("uuid4"), newResume);
        Assert.assertEquals(storage.size(), sizeBeforeSave + 1);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(R1);
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(storage.get(UUID_1), R1);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> resumes = Arrays.asList(R1, R2, R3);
        Collections.sort(resumes);
        List<Resume> sortedResumes = storage.getAllSorted();
        Assert.assertEquals(sortedResumes, resumes);
    }

    @Test
    public void update() throws Exception {
        Resume resume = new Resume(UUID_3, "update");
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
}

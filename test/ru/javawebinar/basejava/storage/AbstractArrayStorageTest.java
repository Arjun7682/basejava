package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {

    private Storage storage;

    private static final String UUID_1 = "uuid1";
    public static final Resume R1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    public static final Resume R2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    public static final Resume R3 = new Resume(UUID_3);

    public AbstractArrayStorageTest(Storage storage) {
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
    public void save() throws Exception {
        final String test = "test";
        final int size = storage.size();
        storage.save(new Resume(test));
        Assert.assertEquals(storage.get(test).getUuid(), test);
        Assert.assertEquals(storage.size(), size + 1);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(R1);
    }

    @Test(expected = StorageException.class)
    public void overflow() throws Exception {
        try {
            for (int i = storage.size(); i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(storage.get(UUID_1).getUuid(), UUID_1);
    }

    @Test
    public void getAll() throws Exception {
        Resume[] resumes = storage.getAll();
        Assert.assertEquals(resumes.length, storage.size());
        Assert.assertEquals(resumes[0], R1);
        Assert.assertEquals(resumes[1], R2);
        Assert.assertEquals(resumes[2], R3);
    }

    @Test
    public void update() throws Exception {
        storage.update(R2);
        Assert.assertEquals(R2, storage.get(R2.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        final int size = storage.size();
        storage.delete(UUID_2);
        Assert.assertEquals(storage.size(), size - 1);
        storage.get(UUID_2);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(storage.size(), 0);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }
}
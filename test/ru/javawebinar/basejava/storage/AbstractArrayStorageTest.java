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
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
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
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = StorageException.class)
    public void overflow() throws Exception {
        while (true) {
            try {
                storage.save(new Resume());
            } catch (StorageException e) {
                if (storage.size() < AbstractArrayStorage.STORAGE_LIMIT) {
                    Assert.fail();
                } else {
                    throw e;
                }
            }
        }
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(storage.get(UUID_1).getUuid(), UUID_1);
    }

    @Test
    public void getAll() throws Exception {
        Assert.assertEquals(storage.getAll().length, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume r = new Resume("update");
        storage.save(r);
        storage.update(r);
        Assert.assertEquals(r, storage.get(r.getUuid()));
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
package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractStorageTest {
    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    public static final Resume R1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    public static final Resume R2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    public static final Resume R3 = new Resume(UUID_3);

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

    @Test
    public void get() throws Exception {
        Assert.assertEquals(storage.get(UUID_1).getUuid(), UUID_1);
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> toCompare = new ArrayList<>();
        toCompare.add(R1);
        toCompare.add(R2);
        toCompare.add(R3);
        Collections.sort(toCompare);
        List<Resume> list = storage.getAllSorted();
        Assert.assertEquals(list, toCompare);
    }

    @Test
    public void update() throws Exception {
        storage.update(R3);
        Assert.assertEquals(R3, storage.get(R3.getUuid()));
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

package ru.javawebinar.basejava.storage;

import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {
    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Override
    @Test
    public void size() throws Exception {
        super.size();
    }

    @Override
    @Test
    public void save() throws Exception {
        super.save();
    }

    @Override
    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        super.saveExist();
    }

    @Override
    @Test(expected = StorageException.class)
    public void overflow() throws Exception {
        super.overflow();
    }

    @Override
    @Test
    public void get() throws Exception {
        super.get();
    }

    @Override
    @Test
    public void getAll() throws Exception {
        super.getAll();
    }

    @Override
    @Test
    public void update() throws Exception {
        super.update();
    }

    @Override
    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        super.delete();
    }

    @Override
    @Test
    public void clear() throws Exception {
        super.clear();
    }

    @Override
    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        super.getNotExist();
    }
}
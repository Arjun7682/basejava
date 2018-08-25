package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListStorage extends AbstractStorage {

    List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void insertElement(Resume r) {
        storage.add(r);
    }

    @Override
    protected Resume getElement(String uuid) {
        for (Resume r : storage) {
            if (r.getUuid().equals(uuid)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    protected boolean isExist(Resume r) {
        return storage.contains(r);
    }
}

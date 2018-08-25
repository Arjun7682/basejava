package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
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
    public int size() {
        return storage.size();
    }

    @Override
    protected void saveChanges(Resume r) {
        delResume(r.getUuid());
        insertElement(r);
    }

    @Override
    protected void delResume(String uuid) {
        storage.remove(getElement(uuid));
    }

    @Override
    protected boolean isExist(Resume r) {
        return storage.contains(r);
    }
}

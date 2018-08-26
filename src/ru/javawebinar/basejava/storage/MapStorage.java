package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage<Resume> {

    Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void insertElement(Resume t, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume getElement(Resume r) {
        return r;
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[storage.size()]);
    }

    @Override
    protected void saveChanges(Resume t, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void delResume(Resume r) {
        storage.remove(r.getUuid());
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Resume r) {
        return storage.containsValue(r);
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }
}

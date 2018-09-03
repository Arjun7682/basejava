package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {
    Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void doSave(String uuid, Resume resume) {
        storage.put(uuid, resume);
    }

    @Override
    protected Resume getElement(String uuid) {
        return storage.get(uuid);
    }

    @Override
    public List<Resume> CopyAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected void saveChanges(String uuid, Resume resume) {
        storage.put(uuid, resume);
    }

    @Override
    protected void delResume(String uuid) {
        storage.remove(uuid);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExist(String uuid) {
        return storage.containsKey(uuid);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }
}

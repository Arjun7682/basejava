package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume> {

    Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void doSave(Resume key, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getElement(Resume resume) {
        return resume;
    }

    @Override
    public List<Resume> getData() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected void saveChanges(Resume key, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void delResume(Resume resume) {
        storage.remove(resume.getUuid());
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Resume resume) {
        return storage.containsValue(resume);
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }
}

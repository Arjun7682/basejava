package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void doSave(Integer index, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected Resume getElement(Integer index) {
        return storage.get(index);
    }

    @Override
    public List<Resume> getData() {
        return storage;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void saveChanges(Integer index, Resume resume) {
        storage.set(index, resume);
    }

    @Override
    protected void delResume(Integer index) {
        storage.remove(index.intValue());
    }

    @Override
    protected boolean isExist(Integer index) {
        return index != null;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }
}

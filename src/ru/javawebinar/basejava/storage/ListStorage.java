package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void doSave(Integer index, Resume r) {
        storage.add(r);
    }

    @Override
    protected Resume getElement(Integer index) {
        return storage.get(index);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>(storage);
        Collections.sort(list);
        return list;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void saveChanges(Integer index, Resume r) {
        storage.set(index, r);
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

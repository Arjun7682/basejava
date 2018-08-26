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
    protected void insertElement(Integer index, Resume r) {
        storage.add(r);
    }

    @Override
    protected Resume getElement(Integer index) {
        return storage.get(index);
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
    protected void saveChanges(Integer index, Resume r) {
        storage.remove(index.intValue());
        insertElement(index, r);
    }

    @Override
    protected void delResume(Integer index) {
        storage.remove(storage.get(index));
    }

    @Override
    protected boolean isExist(Integer index) {
        return index != null;
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        int index = getIndex(uuid);
        return index == -1 ? null : index;
    }
}

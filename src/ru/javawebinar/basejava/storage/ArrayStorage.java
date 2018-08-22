package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    protected void add(Resume r, int index) {
        storage[size] = r;
        size++;
    }

    protected void remove(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

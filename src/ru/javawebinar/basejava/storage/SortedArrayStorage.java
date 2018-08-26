package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    protected void insertElement(Integer index, Resume r) {
        int saveIndex = -index - 1;
        System.arraycopy(storage, saveIndex, storage, saveIndex + 1, size - saveIndex);
        storage[saveIndex] = r;
        size++;
    }

    protected void fillDeletedElement(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}

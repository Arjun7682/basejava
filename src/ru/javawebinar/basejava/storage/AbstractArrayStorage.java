package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    protected void checkSaveExceptions(Integer index, Resume r) throws StorageException {
        super.checkSaveExceptions(index, r);
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
    }

    @Override
    protected Resume getElement(Integer index) {
        return storage[index];
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected void saveChanges(Integer index, Resume r) {
        storage[index] = r;
    }

    @Override
    protected void delResume(Integer index) {
        fillDeletedElement(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected boolean isExist(Integer index) {
        if (index < 0 || index >= size) {
            return false;
        }
        return storage[index] != null;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        return getIndex(uuid);
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract int getIndex(String uuid);
}

package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    protected void insertElement(Resume r) {
        int index = getIndex(r.getUuid());
        insertElement(r, index);
        size++;
    }

    protected abstract void insertElement(Resume r, int index);

    protected abstract int getIndex(String uuid);

    @Override
    protected void checkSaveExceptions(Resume r) throws StorageException {
        super.checkSaveExceptions(r);
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
    }

    @Override
    protected Resume getElement(String uuid) {
        return storage[getIndex(uuid)];
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected void saveChanges(Resume r) {
        storage[getIndex(r.getUuid())] = r;
    }

    @Override
    protected void delResume(String uuid) {
        fillDeletedElement(getIndex(uuid));
        storage[size - 1] = null;
        size--;
    }

    protected abstract void fillDeletedElement(int index);

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected boolean isExist(Resume r) {
        return getIndex(r.getUuid()) > -1;
    }

    @Override
    public int size() {
        return size;
    }
}

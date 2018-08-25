package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume r) {
        checkSaveExceptions(r);
        insertElement(r);
    }

    protected void checkSaveExceptions(Resume r) throws StorageException {
        if (isExist(r)) {
            throw new ExistStorageException(r.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        if (!isExist(new Resume(uuid))) {
            throw new NotExistStorageException(uuid);
        }
        return getElement(uuid);
    }

    protected abstract void insertElement(Resume r);
    protected abstract boolean isExist(Resume r);
    protected abstract Resume getElement(String uuid);
}

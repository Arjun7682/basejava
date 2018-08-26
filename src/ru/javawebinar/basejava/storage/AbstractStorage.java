package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage<SK> implements Storage {

    @Override
    public void save(Resume r) {
        SK searchKey = getSearchKey(r.getUuid());
        checkSaveExceptions(searchKey, r);
        insertElement(searchKey, r);
    }

    protected void checkSaveExceptions(SK searchKey, Resume r) throws StorageException {
        if (isExist(searchKey)) {
            throw new ExistStorageException(r.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return getElement(searchKey);
    }

    @Override
    public void update(Resume r) {
        SK searchKey = getSearchKey(r.getUuid());
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            Resume newResume = new Resume(r.getUuid());
            saveChanges(searchKey, newResume);
        }
    }

    @Override
    public void delete(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            delResume(searchKey);
        }
    }

    protected abstract void delResume(SK searchKey);

    protected abstract void saveChanges(SK searchKey, Resume r);

    protected abstract void insertElement(SK searchKey, Resume r);

    protected abstract boolean isExist(SK searchKey);

    protected abstract Resume getElement(SK searchKey);

    protected abstract SK getSearchKey(String uuid);
}

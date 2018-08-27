package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage<SK> implements Storage {

    @Override
    public void save(Resume r) {
        SK searchKey = getSearchKey(r.getUuid());
        checkExistException(searchKey, r.getUuid());
        doSave(searchKey, r);
    }

    @Override
    public Resume get(String uuid) {
        SK searchKey = getSearchKey(uuid);
        checkNotExistException(searchKey, uuid);
        return getElement(searchKey);
    }

    @Override
    public void update(Resume r) {
        SK searchKey = getSearchKey(r.getUuid());
        checkNotExistException(searchKey, r.getUuid());
        saveChanges(searchKey, r);
    }

    @Override
    public void delete(String uuid) {
        SK searchKey = getSearchKey(uuid);
        checkNotExistException(searchKey, uuid);
        delResume(searchKey);
    }

    private void checkExistException(SK searchKey, String uuid) {
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
    }

    private void checkNotExistException(SK searchKey, String uuid) {
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract void delResume(SK searchKey);

    protected abstract void saveChanges(SK searchKey, Resume r);

    protected abstract boolean isExist(SK searchKey);

    protected abstract Resume getElement(SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract void doSave(SK searchKey, Resume r);
}

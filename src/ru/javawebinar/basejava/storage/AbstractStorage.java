package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {

    @Override
    public void save(Resume resume) {
        SK searchKey = checkExistException(resume.getUuid());
        doSave(searchKey, resume);
    }

    @Override
    public Resume get(String uuid) {
        SK searchKey = checkNotExistException(uuid);
        return getElement(searchKey);
    }

    @Override
    public void update(Resume resume) {
        SK searchKey = checkNotExistException(resume.getUuid());
        saveChanges(searchKey, resume);
    }

    @Override
    public void delete(String uuid) {
        SK searchKey = checkNotExistException(uuid);
        delResume(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = CopyAll();
        Collections.sort(list);
        return list;
    }

    private SK checkExistException(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK checkNotExistException(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract void delResume(SK searchKey);

    protected abstract void saveChanges(SK searchKey, Resume resume);

    protected abstract boolean isExist(SK searchKey);

    protected abstract Resume getElement(SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract void doSave(SK searchKey, Resume resume);

    protected abstract List<Resume> CopyAll();
}

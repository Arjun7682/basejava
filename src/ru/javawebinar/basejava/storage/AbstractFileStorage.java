package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable|writable");
        }
        this.directory = directory;
    }

    @Override
    protected void saveChanges(File searchKey, Resume r) {

    }

    @Override
    protected void doSave(File file, Resume resume) {
        try {
            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO Error", resume.getUuid(), e);
        }
    }

    @Override
    protected Resume getElement(File searchKey) {
        return null;
    }

    @Override
    public List<Resume> getAllSorted() {
        return null;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void delResume(File searchKey) {

    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {

    }

    protected abstract void doWrite(Resume resume, File file) throws IOException;
}


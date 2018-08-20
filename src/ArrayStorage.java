import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        if (isExist(resume.uuid)) {
            resume.uuid = "updated";
        }
        else {
            System.out.println(resume.uuid + " отсутствует в хранилище.");
        }
    }

    public void save(Resume resume) {
        if (size == storage.length) {
            System.out.println("Нет места для хранения нового резюме.");
            return;
        }
        if (isExist(resume.uuid)) {
            System.out.println(resume.uuid + " уже есть в хранилище.");
            return;
        }
        storage[size] = resume;
        size++;
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println(uuid + " отсутствует в хранилище.");
        return null;
    }

    public void delete(String uuid) {
        if (!isExist(uuid)) {
            System.out.println(uuid + " отсутствует в хранилище.");
            return;
        }
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, size - i - 1);
                size--;
                return;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    public boolean isExist(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return true;
            }
        }
        return false;
    }
}

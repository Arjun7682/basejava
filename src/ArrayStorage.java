import java.util.Arrays;

public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size == storage.length) {
            System.out.println("Нет места для хранения нового резюме.");
            return;
        }
        if (getIndex(resume.uuid) != -1) {
            System.out.println(resume.uuid + " уже есть в хранилище.");
            return;
        }
        storage[size] = resume;
        size++;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println(uuid + " отсутствует в хранилище.");
            return null;
        }
        return storage[index];
    }

    public void update(Resume resume) {
        int index = getIndex(resume.uuid);
        if (index == -1) {
            System.out.println(resume.uuid + " отсутствует в хранилище.");
            return;
        }
        storage[index] = resume;
        resume.uuid = resume.uuid + " updated";
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println(uuid + " отсутствует в хранилище.");
            return;
        }
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    public int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

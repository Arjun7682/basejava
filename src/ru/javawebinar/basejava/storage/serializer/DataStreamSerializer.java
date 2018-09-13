package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Link;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.util.Map;

public class DataStreamSerializer implements Serializer {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, Link> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, Link> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue().getText());
                dos.writeUTF(entry.getValue().getUrl());
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                String text = dis.readUTF();
                String url = dis.readUTF();
                resume.getContacts().put(ContactType.valueOf(dis.readUTF()), new Link(text, url));
            }

            return resume;
        }

    }
}

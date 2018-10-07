package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ru.javawebinar.basejava.util.LambdaExceptionUtil.rethrowConsumer;

public class DataStreamSerializer implements Serializer {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType sectionType = entry.getKey();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(((TextSection) entry.getValue()).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeList(dos, entry.getValue());
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeOrg(dos, entry.getValue());
                        break;
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            int contactsSize = dis.readInt();
            for (int i = 0; i < contactsSize; i++) {
                ContactType contact = ContactType.valueOf(dis.readUTF());
                resume.getContacts().put(contact, dis.readUTF());
            }

            int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        resume.getSections().put(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.getSections().put(sectionType, readList(dis));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        resume.getSections().put(sectionType, readOrg(dis));
                        break;
                }
            }

            return resume;
        }
    }

    private void writeList(DataOutputStream dos, Section section) throws IOException {
        List<String> sectionContent = ((ListSection) section).getContent();
        dos.writeInt(sectionContent.size());
        sectionContent.forEach(rethrowConsumer(dos::writeUTF));
    }

    private ListSection readList(DataInputStream dis) throws IOException {
        List<String> sectionContent = new ArrayList<>();
        int i1 = dis.readInt();
        for (int i = 0; i < i1; i++) {
            String e = dis.readUTF();
            sectionContent.add(e);
        }
        return new ListSection(sectionContent);
    }

    private void writeOrg(DataOutputStream dos, Section section) throws IOException {
        List<Organization> organizations = ((OrganizationSection) section).getContent();
        dos.writeInt(organizations.size());
        for (Organization organization : organizations) {
            dos.writeUTF(organization.getCompany().getText());
            dos.writeUTF(organization.getCompany().getUrl());
            List<Organization.Position> positions = organization.getOrgEntries();
            dos.writeInt(positions.size());
            for (Organization.Position position : positions) {
                writeLocalDate(dos, position.getBegin());
                writeLocalDate(dos, position.getEnd());
                dos.writeUTF(position.getTitle());
                dos.writeUTF(position.getDescription());
            }
        }
    }

    private OrganizationSection readOrg(DataInputStream dis) throws IOException {
        List<Organization> organizations = new ArrayList<>();
        int orgSize = dis.readInt();
        for (int i = 0; i < orgSize; i++) {
            Organization organization = new Organization();
            organization.setCompany(new Link(dis.readUTF(), dis.readUTF()));
            organization.setOrgEntries(new ArrayList<>());
            int positionSize = dis.readInt();
            for (int j = 0; j < positionSize; j++) {
                Organization.Position position = new Organization.Position();
                position.setBegin(readLocalDate(dis));
                position.setEnd(readLocalDate(dis));
                position.setTitle(dis.readUTF());
                position.setDescription(dis.readUTF());
                organization.getOrgEntries().add(position);
            }
        }
        return new OrganizationSection(organizations);
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate localDate) throws IOException {
        dos.writeInt(localDate.getYear());
        dos.writeInt(localDate.getMonth().getValue());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }
}

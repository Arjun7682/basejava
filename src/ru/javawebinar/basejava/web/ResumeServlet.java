package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.TestResume;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ResumeServlet extends HttpServlet {
    private Storage storage = Config.get().getStorage();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        storage.clear();
        storage.save(TestResume.initTestResume("test"));
        if (storage.size() == 0) {
            return;
        }
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = response.getWriter();
        String uuid = request.getParameter("uuid");
        writer.write("" +
                "<html>\n" +
                "<head>\n" +
                "<title>Резюме</title>\n" +
                "</head>\n" +
                "<body>");

        if (uuid == null) {
            writer.write("<b>Список всех резюме:</b>");
            for (Resume resume : storage.getAllSorted()) {
                writeResume(writer, resume);
            }
        } else {
            Resume resume = storage.get(uuid);
            if (resume == null) {
                writer.write("Резюме " + uuid + " отсутствует в базе");
            } else {
                writer.write("Резюме " + uuid + ':');
                writeResume(writer, resume);
            }
        }

        writer.write("" +
                "</body>\n" +
                "</html>");
    }

    private void writeResume(PrintWriter writer, Resume resume) {
        writer.write("<table border=\"1\">");
        writeCell(writer, "Имя", resume.getFullName());
        for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
            writeCell(writer, entry.getKey().name(), entry.getValue());
        }
        for (Map.Entry<SectionType, Section> entry : resume.getSections().entrySet()) {
            SectionType sectionType = entry.getKey();
            Section section = entry.getValue();
            switch (sectionType) {
                case OBJECTIVE:
                case PERSONAL:
                    writeCell(writer, sectionType.getTitle(), ((TextSection) section).getContent());
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    List<String> content = ((ListSection) section).getContent();
                    writer.write("" +
                            "<tr>\n" +
                            "   <td rowspan = \"" + content.size() + "\">" + sectionType.getTitle() + "</td>\n" +
                            "   <td>" + content.get(0) + "</td>\n" +
                            "</tr>");
                    for (int i = 1; i < content.size(); i++) {
                        writer.write("<tr><td>" + content.get(i) + "</td></tr>");
                    }
                    break;
            }
        }
        writer.write("</table><br>");
    }

    private void writeCell(PrintWriter writer, String value1, String value2) {
        writer.write("" +
                "<tr>\n" +
                "   <td>" + value1 + "</td>\n" +
                "   <td>" + value2 + "</td>\n" +
                "</tr>");
    }
}

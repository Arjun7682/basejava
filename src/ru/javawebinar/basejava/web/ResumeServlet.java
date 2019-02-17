package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.EmptyResume;
import ru.javawebinar.basejava.TestResume;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;
import ru.javawebinar.basejava.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private Storage storage = Config.get().getStorage();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume r;

        if (uuid == null || uuid.trim().length() == 0) {
            r = new Resume(fullName);
        } else {
            r = storage.get(uuid);
            r.setFullName(fullName);
        }

        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                r.setContacts(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }

        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        r.setSections(type, new TextSection(value));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        r.setSections(type, new ListSection(Arrays.asList(value.split("\n"))));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        String[] companies = request.getParameterValues(type.name());
                        String[] urls = request.getParameterValues(type.name());
                        List<Organization> organizations = new ArrayList<>();
                        for (int i = 0; i < companies.length; i++) {
                            Link link = new Link(companies[i], urls[i]);
                            String[] startDates = request.getParameterValues(type.name() + i + "startDate");
                            String[] endDates = request.getParameterValues(type.name() + i + "endDate");
                            String[] titles = request.getParameterValues(type.name() + i + "title");
                            String[] descriptions = request.getParameterValues(type.name() + i + "description");
                            List<Organization.Position> positions = new ArrayList<>();
                            for (int j = 0; j < titles.length; j++) {
                                LocalDate startDate = DateUtil.parse(startDates[j]);
                                LocalDate endDate = DateUtil.parse(endDates[j]);
                                positions.add(new Organization.Position(startDate, endDate, titles[j], descriptions[j]));
                            }
                            organizations.add(new Organization(link, positions));
                        }
                        r.setSections(type, new OrganizationSection(organizations));
                }
            } else {
                r.getSections().remove(type);
            }
        }
        if (uuid == null || uuid.trim().length() == 0) {
            storage.save(r);
        } else {
            storage.update(r);
        }
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (storage.size() == 0) {
            storage.save(TestResume.initTestResume("test"));
        }

        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");

        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }

        Resume r;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
            case "edit":
                r = storage.get(uuid);
                break;
            case "add":
                r = EmptyResume.initEmptyResume();
                break;
            default:
                throw new IllegalStateException("action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
                .forward(request, response);

    }
}

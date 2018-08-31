package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Resume resume = new Resume("name");
        Method method = resume.getClass().getDeclaredMethod("toString");
        System.out.println(method.invoke(resume));
    }
}

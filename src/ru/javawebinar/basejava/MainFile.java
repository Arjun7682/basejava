package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        File dir = new File("C:\\Users\\pasha\\YandexDisk\\Git\\basejava\\src\\ru\\javawebinar\\basejava");

        try {
            System.out.println(dir.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

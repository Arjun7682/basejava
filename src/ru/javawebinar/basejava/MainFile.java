package ru.javawebinar.basejava;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        File dir = new File("C:\\Users\\pasha\\YandexDisk\\Git\\basejava");
        getFileList(dir, "");
    }

    public static void getFileList(File dir, String offset) {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                System.out.println("Dir: " + offset + file.getName());
                getFileList(file, "  ");
            } else {
                System.out.println(offset + file.getName());
            }
        }
    }
}

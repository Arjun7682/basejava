package ru.javawebinar.basejava;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainFile {
    public static void main(String[] args) {
        File dir = new File("C:\\Users\\pasha\\YandexDisk\\Git\\basejava\\src\\ru\\javawebinar\\basejava");

        for (File file : getFileList(dir)) {
            System.out.println(file.getName());
        }
    }

    public static List<File> getFileList(File dir) {
        List<File> files = new ArrayList<>();

        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                files.addAll(getFileList(file));
            } else {
                files.add(file);
            }
        }
        return files;
    }
}

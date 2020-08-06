package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.ExceptionHandler;

import javax.xml.crypto.Data;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class FileBucket {
    private Path path;
    // Инициализировать path временным файлом. Файл должен быть размещен в директории для временных файлов и иметь случайное имя.

    public FileBucket() {
        try {
            path = Files.createTempFile(null, null);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
        path.toFile().deleteOnExit();
    }
    //он должен возвращать размер файла на который указывает path.
    public long getFileSize(){
        try {
            return Files.size(path);// если файла нет то вернет 0
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    //должен сериализовывать переданный entry в файл. Учти, каждый entry может содержать еще один entry.
    public void putEntry(Entry entry) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(path))) {
            objectOutputStream.writeObject(entry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //  должен забирать entry из файла. Если файл имеет нулевой размер, вернуть null.
    public Entry getEntry()  {
        Entry entry = null;

        if (getFileSize() <= 0)
            return entry;

        try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path))) {
            entry = (Entry) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  entry;
    }
    // удалять файл на который указывает path.
    public void remove(){
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;
import com.javarush.task.task33.task3310.tests.FunctionalTest;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Solution {


    public static void main(String[] args) {


        testStrategy(new HashMapStorageStrategy(), 1000);
        testStrategy(new OurHashMapStorageStrategy(), 1000);
        testStrategy(new FileStorageStrategy(), 10);
        testStrategy(new OurHashBiMapStorageStrategy(), 100);
        testStrategy(new HashBiMapStorageStrategy(), 10);
        testStrategy(new DualHashBidiMapStorageStrategy(), 1);
        FunctionalTest tst = new FunctionalTest();



    } // END of MAIN

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        /*
         * Этот метод должен для переданного множества строк возвращать множество идентификаторов.
         * Идентификатор для каждой отдельной строки нужно получить, используя shortener.
         * */
        Set<Long> ids = new HashSet<>();

        for (String s : strings) {
            ids.add(shortener.getId(s));
        }
        return ids;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        /*
         * Метод будет возвращать множество строк, которое соответствует переданному множеству идентификаторов.
         * */
        Set<String> setOfStrings = new HashSet<>();

        for (Long l : keys) {
            setOfStrings.add(shortener.getString(l));
        }
        return setOfStrings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        // Выводить имя класса стратегии. Имя не должно включать имя пакета
        String fullClassName = strategy.getClass().toString();
        String className = fullClassName.substring(fullClassName.lastIndexOf('.') +1, fullClassName.length());
        System.out.println(className);

        // Генерировать тестовое множество строк, используя Helper и заданное количество элементов elementsNumber
        Set<String> testSetOfStrings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            testSetOfStrings.add(Helper.generateRandomString());
        }

        // Создавать объект типа Shortener, используя переданную стратегию.
        Shortener shortener = new Shortener(strategy);

        /*
         * Замерять и выводить время необходимое для отработки метода getIds для заданной
         * стратегии и заданного множества элементов. Время вывести в миллисекундах.
         * */
        Set<Long> setOfIDs = new HashSet<>();
        Date startTime = new Date();
        setOfIDs = getIds(shortener, testSetOfStrings);
        Date endTime = new Date();
        long runningTime = endTime.getTime() - startTime.getTime();
        Helper.printMessage(Long.toString(runningTime));

        /*
         * Замерять и выводить время необходимое для отработки метода getStrings для заданной
         * стратегии и полученного в предыдущем пункте множества идентификаторов.
         * */
        Set<String> setOfStrings = new HashSet<>();
        Date startTime1 = new Date();
        setOfStrings = getStrings(shortener, setOfIDs);
        Date endTime1 = new Date();
        long runningTime1 = endTime1.getTime() - startTime1.getTime();
        Helper.printMessage(Long.toString(runningTime1));

        /*
         * Сравнивать одинаковое ли содержимое множества строк, которое было
         * сгенерировано и множества, которое было возвращено методом getStrings.
         * Если множества одинаковы, то выведи "Тест пройден.", иначе "Тест не пройден.".
         * */
        if (testSetOfStrings.equals(setOfStrings)) {
            Helper.printMessage("Тест пройден.");
        }

        if (!testSetOfStrings.equals(setOfStrings)) {
            Helper.printMessage("Тест не пройден.");
        }






    }

}
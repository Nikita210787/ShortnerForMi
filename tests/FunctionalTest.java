package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;


public class FunctionalTest {

    public void testStorage(Shortener shortener) {
        // Создавать три строки. Текст 1 и 3 строк должен быть одинаковым.
        String lineOne = Helper.generateRandomString();
        String lineTwo = Helper.generateRandomString();
        String lineThree = lineOne;

        Long idOfLineOne = shortener.getId(lineOne);
        Long idOfLineTwo = shortener.getId(lineTwo);
        Long idOfLineThree = shortener.getId(lineThree);

        /*
        * Проверять, что идентификатор для 2 строки не равен идентификатору для 1 и 3 строк.
          Подсказка: метод Assert.assertNotEquals.
        * */

        Assert.assertNotEquals(idOfLineOne, idOfLineTwo);
        Assert.assertNotEquals(idOfLineTwo, idOfLineThree);
        Assert.assertEquals(idOfLineOne, idOfLineThree);

        String newStr1 = shortener.getString(idOfLineOne);
        String newStr2 = shortener.getString(idOfLineTwo);
        String newStr3 = shortener.getString(idOfLineThree);

        Assert.assertEquals(lineOne, newStr1);
        Assert.assertEquals(lineTwo, newStr2);
        Assert.assertEquals(lineThree, newStr3);

    }

    @Test
    public void testHashMapStorageStrategy(){
        Shortener shortener = new Shortener(new HashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy(){
        Shortener shortener = new Shortener(new OurHashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy(){
        Shortener shortener = new Shortener(new FileStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy(){
        Shortener shortener = new Shortener(new HashBiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy(){
        Shortener shortener = new Shortener(new DualHashBidiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy(){
        Shortener shortener = new Shortener(new OurHashBiMapStorageStrategy());
        testStorage(shortener);
    }
}

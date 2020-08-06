package com.javarush.task.task33.task3310.strategy;

import java.io.Serializable;
import java.util.Objects;

public class Entry implements Serializable {


    Long key;
    String value;
    Entry next;
    int hash;

    // Конструктор
    public Entry(int hash, Long key, String value, Entry next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    /*
     * Добавь и реализуй методы: Long getKey(), String getValue(), int hashCode(), boolean equals() и String toString(). Р
     * */

    public Long getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry)) return false;
        Entry entry = (Entry) o;
        return Objects.equals(key, entry.key) &&
                Objects.equals(value, entry.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }

    // Метод toString должен возвращать строку формата key + " = " + value.
}

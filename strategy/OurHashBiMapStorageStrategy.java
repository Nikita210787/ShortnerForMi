package com.javarush.task.task33.task3310.strategy;

import java.io.Serializable;
import java.util.HashMap;

public class OurHashBiMapStorageStrategy implements StorageStrategy {
    private  HashMap<Long, String> k2v=new HashMap<Long, String>() ;
    private HashMap<String, Long> v2k=new HashMap<String, Long>();

    @Override
    public boolean containsKey(Long key) {
        return k2v.containsKey(key)?true:false;
    }

    @Override
    public boolean containsValue(String value) {
        return v2k.containsKey(value)? true:false;
    }

    @Override
    public void put(Long key, String value) {
        k2v.put(key, value);
        v2k.put(value,key);
    }

    @Override
    public Long getKey(String value) {
        return v2k.get(value);

    }

    @Override
    public String getValue(Long key) {
        return k2v.get(key);
    }
}

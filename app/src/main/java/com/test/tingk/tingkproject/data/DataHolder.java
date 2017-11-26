package com.test.tingk.tingkproject.data;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tingk on 2017/11/26.
 */

// Singleton Pattern
// 關鍵字：Singleton Pattern、WeakReference
public class DataHolder<T> {
    private static DataHolder dataHolder;

    public static DataHolder getInstance() {
        if (dataHolder == null) {
            dataHolder = new DataHolder();
        }
        return dataHolder;
    }

    Map<String, WeakReference<List<T>>> data = new HashMap<String, WeakReference<List<T>>>();

    public void setData(String key, List<T> object) {
        data.put(key, new WeakReference<List<T>>(object));
    }

    public List<T> getData(String key) {
        WeakReference<List<T>> objectWeakReference = data.get(key);
        return objectWeakReference.get();
    }
}

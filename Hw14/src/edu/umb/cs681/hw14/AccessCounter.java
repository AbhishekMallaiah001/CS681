package edu.umb.cs681.hw14;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccessCounter {

    Map<Path, Integer> filePathMap = new ConcurrentHashMap<>();
    private static AccessCounter instance = null;

    private AccessCounter() {
    }

    public static AccessCounter getInstance() {
        if (instance == null) {
            instance = new AccessCounter();
        }
        return instance;

    }

    public void increment(Path path) {
        if (filePathMap.containsKey(path)) {
            filePathMap.replace(path, filePathMap.get(path), filePathMap.get(path) + 1);
        } else {
            filePathMap.put(path, 1);
        }
    }

    public int getCount(Path path) {
        return filePathMap.getOrDefault(path, 0);
    }
}

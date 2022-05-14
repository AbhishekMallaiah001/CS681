package edu.umb.cs681.hw11;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {

    Map<Path, Integer> filePathMap = new HashMap<>();
    private ReentrantLock lock = new ReentrantLock();
    private static ReentrantLock staticLock = new ReentrantLock();
    private static AccessCounter instance = null;

    private AccessCounter() {}

    public static AccessCounter getInstance() {
        staticLock.lock();
        try {
            if (instance == null) {
                instance = new AccessCounter();
            }
            return instance;

        } finally {
            staticLock.unlock();
        }
    }

    public void increment(Path path) {
        lock.lock();
        try {
            if (filePathMap.containsKey(path)) {
                filePathMap.replace(path, filePathMap.get(path), filePathMap.get(path) + 1);
            } else {
                filePathMap.put(path, 1);
            }
        } finally {
            lock.unlock();
        }
    }

    public int getCount(Path path) {

        lock.lock();
        try {
            if (filePathMap.containsKey(path)) {
                return filePathMap.get(path);
            } else {
                return 0;
            }
        } finally {
            lock.unlock();
        }
    }
}

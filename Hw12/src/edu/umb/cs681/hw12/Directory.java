package edu.umb.cs681.hw12;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement {

    public Directory(Directory parent, String name, int size, LocalDateTime creationtime) {
        super(parent, name, size, creationtime);
    }

    LinkedList<FSElement> children = new LinkedList<FSElement>();

    public LinkedList<FSElement> getChildren() {
        lock.lock();
        try {
            return this.children;
        } finally {
            lock.unlock();
        }
    }

    public void appendChild(FSElement child) {
        lock.lock();
        try {
            this.children.add(child);
        } finally {
            lock.unlock();
        }
    }

    public int countChildren() {
        lock.lock();
        try {
            return children.size();
        } finally {
            lock.unlock();
        }
    }


    public LinkedList<Directory> getSubDirectories() {
        lock.lock();
        try {
            LinkedList<Directory> subDirectories = new LinkedList<>();
            for (FSElement element : children) {
                if (!element.isFile())
                    subDirectories.add((Directory) element);
            }
            return subDirectories;
        } finally {
            lock.unlock();
        }
    }


    public LinkedList<File> getFiles() {
        lock.lock();
        try {
            LinkedList<File> files = new LinkedList<>();
            for (FSElement element : children) {
                if (element.isFile())
                    files.add((File) element);
            }
            return files;
        } finally {
            lock.unlock();
        }
    }

    public int getTotalsize() {
        FSElement dir = this;
        Directory d = (Directory) dir;
        int i;
        int total_size = 0;
        lock.lock();
        try {
            for (i = 0; i < d.getChildren().size(); i++) {
                total_size = total_size + d.getChildren().get(i).getSize();
            }
            return total_size;
        } finally {
            lock.unlock();
        }
    }
}

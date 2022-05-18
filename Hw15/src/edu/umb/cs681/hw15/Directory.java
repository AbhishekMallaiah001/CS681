package edu.umb.cs681.hw15;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Directory extends FSElement {

    public Directory(Directory parent, String name, int size, LocalDateTime creationtime) {
        super(parent, name, size, creationtime);
    }

    ConcurrentLinkedQueue<FSElement> children = new ConcurrentLinkedQueue<>();

    public ConcurrentLinkedQueue<FSElement> getChildren() {
        return this.children;

    }

    public void appendChild(FSElement child) {
        this.children.add(child);
    }

    public int countChildren() {
        return children.size();
    }


    public LinkedList<Directory> getSubDirectories() {
        LinkedList<Directory> subDirectories = new LinkedList<>();
        for (FSElement element : children) {
            if (!element.isFile())
                subDirectories.add((Directory) element);
        }
        return subDirectories;
    }


    public LinkedList<File> getFiles() {
        LinkedList<File> files = new LinkedList<>();
        for (FSElement element : children) {
            if (element.isFile())
                files.add((File) element);
        }
        return files;
    }

    public int getTotalSize() {
        FSElement dir = this;
        Directory d = (Directory) dir;
        int i;
        int total_size = 0;
        for (i = 0; i < d.getChildren().size(); i++) {
            total_size = total_size + d.getChildren().element().getSize();
        }
        return total_size;
    }
}

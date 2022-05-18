package edu.umb.cs681.hw15;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FSElement {
    private String name;
    private int size;
    private static LocalDateTime creationist;
    private edu.umb.cs681.hw15.Directory parent;
    LocalDateTime now = LocalDateTime.now();


    FSElement(Directory parent, String name, int size, LocalDateTime creationist) {
        this.parent = parent;
        this.name = name;
        this.size = size;
        FSElement.creationist = creationist;

        if (parent != null) {
            parent.appendChild(this);
        }
    }

    public Directory getParent() {
        return this.parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:MM");
        String time = creationist.format(formatter);
        return time;
    }

    public int getSize() {
        FSElement dir = this;

        if (dir.isFile() == true) {
            return size;
        } else {
            Directory d = (Directory) dir;
            int i;
            int z = 0;
            z = d.getFiles().size();
            for (i = 0; i < d.getSubDirectories().size(); i++) {
                z = z + d.getSubDirectories().get(i).getFiles().size();
            }
            this.size = z;
            return size;
        }
    }

    public boolean isFile() {
        if (this instanceof File)
            return true;
        else return false;
    }
}

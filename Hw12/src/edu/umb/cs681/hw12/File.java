package edu.umb.cs681.hw12;

import java.time.LocalDateTime;

public class File  extends FSElement {

    public File(Directory parent, String name, int size, LocalDateTime creationtime) {
        super(parent, name, size, creationtime);
    }
}


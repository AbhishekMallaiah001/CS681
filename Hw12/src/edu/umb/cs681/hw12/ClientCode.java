package edu.umb.cs681.hw12;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientCode {
    public static void main(String[] args) {
        LocalDateTime creationist = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:MM");
        String time = creationist.format(formatter);

        LocalDateTime creationist1 = LocalDateTime.now();
        Directory root = new Directory(null, "root", 0, creationist1);

        LocalDateTime creationist2 = LocalDateTime.now();
        Directory home = new Directory(root, "home", 0, creationist1);
        Directory alfredProject = new Directory(home, "Alfred_Project", 0, creationist1);
        Directory pviPythonProject = new Directory(home, "PVI_Python_Project", 0, creationist1);
        File file1 = new File(home, "file:f1", 1, creationist2);
        File file2 = new File(alfredProject, "file:f2", 1, creationist2);
        File file3 = new File(alfredProject, "file:f3", 3, creationist2);
        File file4 = new File(alfredProject, "file:f4", 2, creationist2);
        File file5 = new File(pviPythonProject, "file:f5", 4, creationist2);
        File file6 = new File(pviPythonProject, "file:f6", 2, creationist2);
        File file7 = new File(alfredProject, "file:f7", 6, creationist2);
        File file8 = new File(alfredProject, "file:f8", 2, creationist2);
        File file9 = new File(pviPythonProject, "file:f9", 1, creationist2);
        File file10 = new File(pviPythonProject, "file:f10", 3, creationist2);


        System.out.println(" Size of " + home.getName() + " directory which is created on " + home.getTime() + " is " + home.getSize());
        System.out.println(" Size of PVI_Python_Project subdirectory which is created on " + pviPythonProject.getTime() + " is  " + pviPythonProject.getSize());
        System.out.println(" Size of Alfred_Project subdirectory which is created on " + alfredProject.getTime() + " is  " + alfredProject.getSize());
        System.out.println(" Total size of the root directory is  " + root.getTotalsize());

    }
}

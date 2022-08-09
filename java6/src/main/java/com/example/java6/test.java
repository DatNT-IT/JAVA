package com.example.java6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class test {



    public static void main(String[] args) {
        Student student = new Student(1,"a");
        Student student1 = new Student(1,"a");
        Student student2 = new Student(2,"c");
        Set<Student> list = new HashSet<>();
        list.add(student);
        list.add(student1);
        list.add(student2);

        for (Student s : list) {
            System.out.println(s.hashCode());
        }
    }
}

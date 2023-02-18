package com.muammarahlnn.simplelist;

import java.util.ArrayList;

/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file DataSource, 18/02/2023 21.09 by Muammar Ahlan Abimanyu
 */
public class DataSource {

    public static ArrayList<Student> students = generateDummyStudents();

    private static ArrayList<Student> generateDummyStudents() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Monkey D. Luffy", "SHP01"));
        students.add(new Student("Roronoa Zoro", "SHP02"));
        students.add(new Student("Nami", "SHP03"));
        students.add(new Student("Usopp", "SHP04"));
        students.add(new Student("Sanji", "SHP05"));
        students.add(new Student("Tony Tony Chopper", "SHP06"));
        students.add(new Student("Nico Robin", "SHP07"));
        students.add(new Student("Franky", "SHP08"));
        students.add(new Student("Brook", "SHP09"));
        students.add(new Student("Jimbei", "SHP010"));
        return students;
    }
}

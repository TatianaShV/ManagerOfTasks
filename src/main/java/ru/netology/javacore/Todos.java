package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
   private static  List<String> tasks = new ArrayList<>();
    private final static int SIZE = 7;
   String task;
    String type;

    public static List<String> getTasks() {
        return tasks;
    }

    public void addTask(String task) {
        if(tasks.size() < SIZE){
       tasks.add(task);
        }
    }

    public void removeTask(String task) {
       tasks.remove(task);
    }

    public String getAllTasks() {

        String task = tasks.stream()
                .sorted()
                .collect(Collectors.joining(" "));

        return  task;


    }

    @Override
    public String toString() {
        return type + " " + task;
    }
}

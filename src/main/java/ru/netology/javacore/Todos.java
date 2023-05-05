package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    private List<String> tasks = new ArrayList<>();
    private final static int SIZE = 7;

    public List<String> getTasks() {
        return tasks;
    }

    public void addTask(String task) {
        if (tasks.size() < SIZE) {
            tasks.add(task);
        }
    }

    public void removeTask(String task) {
        if (tasks.contains(task)) {
            tasks.remove(task);
        } else {
            System.out.println("Такого задания нет в списке");
        }
        System.out.println(tasks);
    }

    public String getAllTasks() {

        String task = tasks.stream()
                .sorted()
                .collect(Collectors.joining(" "));

        return task;


    }
}

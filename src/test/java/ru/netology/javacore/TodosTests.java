package ru.netology.javacore;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TodosTests {
    Todos todos;
    Operation operation;

    @BeforeEach
    public void beforeEach() {
        operation = new Operation();
        todos = new Todos();
    }

    @Test
    public void addTask() {

        operation.task = "решить задачу";
        List<String> expected = new ArrayList<>();
        expected.add("решить задачу");
        todos.addTask(operation.task);
        Assertions.assertEquals(expected, todos.getTasks());
        operation.task = "помыть посуду";
        todos.addTask(operation.task);
        Assertions.assertNotEquals(expected, todos.getTasks());
    }

    @Test
    public void removeTask() {

        operation.task = "решить задачу";
        todos.addTask(operation.task);
        operation.task = "помыть посуду";
        todos.addTask(operation.task);
        operation.task = "купить хлеб";
        todos.addTask(operation.task);
        todos.removeTask("решить задачу");
        List<String> expected1 = new ArrayList<>();
        expected1.add("помыть посуду");
        expected1.add("купить хлеб");
        Assertions.assertEquals(expected1, todos.getTasks());
    }

    @Test
    public void getAllTasks() {
        Todos todos = new Todos();
        operation.task = "Решить задачу";
        todos.addTask(operation.task);
        operation.task = "Помыть посуду";
        todos.addTask(operation.task);
        operation.task = "Купить хлеб";
        todos.addTask(operation.task);
        String expected2 = "Купить хлеб Помыть посуду Решить задачу";
        Assertions.assertEquals(expected2, todos.getAllTasks());
    }

    @Test
    public void addTaskSize() {

        todos.addTask("выучить");
        todos.addTask("спеть");
        todos.addTask("приготовить");
        todos.addTask("купить");
        todos.addTask("сварить");
        todos.addTask("прочитать");
        todos.addTask("найти");
        todos.addTask("сложить");
        System.out.println(todos.getAllTasks());
        Assertions.assertTrue(todos.getTasks().size() == 7);
    }

}
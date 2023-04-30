package ru.netology.javacore;

import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TodosTests {
    Todos todos;

    @BeforeEach
    public void beforeEach() {
        todos = new Todos();
    }

    @Test
    public void addTask() {

        todos.task = "решить задачу";
        List<String> expected = new ArrayList<>();
        expected.add("решить задачу");
        todos.addTask(todos.task);
        Assertions.assertEquals(expected, todos.getTasks());
        todos.task = "помыть посуду";
        todos.addTask(todos.task);
        Assertions.assertNotEquals(expected, todos.getTasks());
    }

    @Test
    public void removeTask() {

        todos.task = "решить задачу";
        todos.addTask(todos.task);
        todos.task = "помыть посуду";
        todos.addTask(todos.task);
        todos.task = "купить хлеб";
        todos.addTask(todos.task);
        todos.removeTask("решить задачу");
        List<String> expected1 = new ArrayList<>();
        expected1.add("помыть посуду");
        expected1.add("купить хлеб");
        Assertions.assertEquals(expected1, todos.getTasks());
    }

    @Test
    public void getAllTasks() {
        Todos todos = new Todos();
        todos.task = "Решить задачу";
        todos.addTask(todos.task);
        todos.task = "Помыть посуду";
        todos.addTask(todos.task);
        todos.task = "Купить хлеб";
        todos.addTask(todos.task);
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
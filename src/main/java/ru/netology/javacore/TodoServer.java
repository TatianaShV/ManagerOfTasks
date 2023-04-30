package ru.netology.javacore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TodoServer {
    private int port;
    Todos todos;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);) {

                    System.out.println("New connection accepted");
                    System.out.println("Подключен клиент " + socket.getPort());
                    String request = in.readLine();
                    System.out.println(readAnswer(request));

                    switch (todos.type) {
                        case "add":
                            todos.addTask(todos.task);
                        case "remove":
                            if (todos.type.equals("remove")) {
                                todos.removeTask(todos.task);
                            }
                    }
                    out.println(todos.getAllTasks());
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }

    private Todos readAnswer(String request) {
        Gson gson = new Gson();
        todos = gson.fromJson(request, Todos.class);
        return todos;
    }
}

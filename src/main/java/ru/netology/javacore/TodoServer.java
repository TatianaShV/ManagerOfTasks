package ru.netology.javacore;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    private int port;
    Todos todos;
    Operation operation;

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
                    readAnswer(request);
                    switch (operation.type) {
                        case "ADD":
                            todos.addTask(operation.task);
                            break;
                        case "REMOVE":
                            todos.removeTask(operation.task);
                            break;
                    }
                    out.println(todos.getAllTasks());
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }

    private Operation readAnswer(String request) {
        Gson gson = new Gson();
        operation = gson.fromJson(request, Operation.class);
        return operation;
    }
}

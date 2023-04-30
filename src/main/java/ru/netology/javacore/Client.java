package ru.netology.javacore;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static String type;

    private static String task;

    public Client(@JsonProperty("type") String type,

                  @JsonProperty("task") String task) {
        Client.type = type;
        Client.task = task;
    }

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        try (Socket clientSocket = new Socket("localhost", 8989);
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            System.out.println("Введите операцию и название задачи через запятую");
            String[] input = scanner.nextLine().split(",");
            setType(input[0]);
            setTask(input[1]);

            try (PrintWriter out = new PrintWriter("request.json")) {
                Gson gson = new Gson();
                JsonObject request = new JsonObject();
                request.addProperty("type", type);
                request.addProperty("task", task);
                String clientrequestJson = gson.toJson(request);
                out.println(clientrequestJson);
                writer.println(clientrequestJson);
            }
            String response = reader.readLine();
            System.out.println(response);


        }
    }

    public static void setType(String type) {
        Client.type = type;
    }

    public static void setTask(String task) {
        Client.task = task;
    }

    public String toString() {
        return type + " " + task;
    }
}

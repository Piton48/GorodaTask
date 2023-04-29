package ru.netology.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            String actual = "Введите название города:";
            String letter = null;

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    out.println(actual);
                    String sent = in.readLine();

                    if (actual.equals("Введите название города:")) {
                        actual = sent;
                        letter = String.valueOf(sent.charAt(sent.length() - 1)).toLowerCase();
                        out.println("Ок");
                        continue;
                    }

                    if (String.valueOf(sent.charAt(0)).toLowerCase().equals(letter)) {
                        out.println("Ok");
                        actual = sent;
                        letter = String.valueOf(actual.charAt(actual.length() - 1)).toLowerCase();
                    } else {
                        out.println("Not Ok");
                    }

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

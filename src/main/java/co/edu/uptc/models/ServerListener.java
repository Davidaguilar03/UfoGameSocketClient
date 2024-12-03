package co.edu.uptc.models;

import java.io.BufferedReader;
import java.io.IOException;

public class ServerListener implements Runnable {
    private ClientMethodMap methodMap;
    private BufferedReader in;
    private boolean running;

    public ServerListener(UfoSocketClient client, BufferedReader in, boolean running) {
        this.methodMap = new ClientMethodMap(client);
        this.in = in;
        this.running = running;
    }

    @Override
    public void run() {
        try {
            String serverMessage;
            while (running && (serverMessage = in.readLine()) != null) {
                String[] parts = serverMessage.split(" ", 2);
                String key = parts[0];
                String inputLine = parts.length > 1 ? parts[1] : "";
                methodMap.run(key, inputLine);
            }
        } catch (IOException e) {
            if (running) {
                e.printStackTrace();
            }
        }
    }
}

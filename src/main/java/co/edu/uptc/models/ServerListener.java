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
            processServerMessages();
        } catch (IOException e) {
            handleIOException(e);
        } finally {
            closeBufferedReader();
        }
    }

    private void processServerMessages() throws IOException {
        String serverMessage;
        while (running && (serverMessage = in.readLine()) != null) {
            processServerMessage(serverMessage);
        }
    }

    private void processServerMessage(String serverMessage) {
        String[] parts = serverMessage.split(" ", 2);
        String key = parts[0];
        String inputLine = parts.length > 1 ? parts[1] : "";
        methodMap.run(key, inputLine);
    }

    private void handleIOException(IOException e) {
        if (running) {
            e.printStackTrace();
        }
    }

    private void closeBufferedReader() {
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        running = false;
    }
}

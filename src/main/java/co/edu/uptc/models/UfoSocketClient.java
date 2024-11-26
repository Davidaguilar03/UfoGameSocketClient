package co.edu.uptc.models;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import co.edu.uptc.interfaces.UfoGameInterface;
import co.edu.uptc.interfaces.UfoGameInterface.Presenter;
import co.edu.uptc.pojos.Ufo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UfoSocketClient implements UfoGameInterface.Model {
    private UfoGameInterface.Presenter presenter;
    private List<Ufo> ufos;
    private Ufo selectedUfo;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String username;
    private static Gson gson = new Gson();
    private volatile boolean running = true;

    public UfoSocketClient() {
        ufos = new ArrayList<>();
    }

    @Override
    public void startConnection(String ip, int port, String username) throws IOException {
        this.username = username;
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        sendMessage("CONNECT " + username);
        new Thread(new ServerListener()).start();
    }

    @Override
    public void stopConnection() throws IOException {
        running = false;
        sendMessage("DISCONNECT " + username);
        in.close();
        out.close();
        clientSocket.close();
    }

    public void sendMessage(String msg) {
        out.println(username + ": " + msg);
    }

    public String receiveMessage() throws IOException {
        return in.readLine();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void sendSpawnRate(int spawnRate) {
        sendMessage("SPAWN_RATE " + spawnRate);
    }

    @Override
    public void sendSpeed(int speed) {
        sendMessage("SPEED " + speed);
    }

    @Override
    public void sendNumberofUfos(int numberofUfos) {
        sendMessage("NUMBER_OF_UFOS " + numberofUfos);
    }

    @Override
    public void sendTrajectoryToServer(ArrayList<Point> trajectoryPoints) {
        String trajectoryJson = gson.toJson(trajectoryPoints);
        sendMessage("UFO_TRAJECTORY " + trajectoryJson);
    }

    @Override
    public void sendSelectedPoint(Point point) {
        sendMessage("SELECTED_POINT " + point.x + " " + point.y);
    }

    @Override
    public void startGame() {
        sendMessage("START_GAME");
    }

    @Override
    public void requestUfosList() {
        sendMessage("REQUEST_UFO_LIST");
    }

    private class ServerListener implements Runnable {
        @Override
        public void run() {
            try {
                String serverMessage;
                while (running && (serverMessage = in.readLine()) != null) {
                    System.out.println("Servidor: " + serverMessage);
                    if (serverMessage.startsWith("UPDATE_UFO_COUNT")) {
                        int size = Integer.parseInt(serverMessage.split(" ")[1]);
                        updateUfoCount(size);
                    } else if (serverMessage.equals("PLAY_CRASH_SOUND")) {
                        playCrashSound();
                    } else if (serverMessage.startsWith("INCREMENT_CRASHED_UFO_COUNT")) {
                        int crashedUfos = Integer.parseInt(serverMessage.split(" ")[1]);
                        incrementCrashedUfoCount(crashedUfos);
                    } else if (serverMessage.equals("PLAY_LANDING_SOUND")) {
                        playLandingSound();
                    } else if (serverMessage.equals("INCREMENT_LANDED_UFO_COUNT")) {
                        incrementLandedUfoCount();
                    } else if (serverMessage.contains("UPDATE_CONNECTED_PLAYERS")) {
                        int connectedPlayers = Integer.parseInt(serverMessage.split(" ")[1]);
                        updateConnectedPlayers(connectedPlayers);
                    } else if (serverMessage.equals("UPDATE_UFOS")) {
                        updateUfos();
                    } else if (serverMessage.startsWith("UFO_LIST")) {
                        String json = serverMessage.substring("UFO_LIST ".length());
                        Type listType = new TypeToken<List<Ufo>>() {}.getType();
                        List<Ufo> ufos = gson.fromJson(json, listType);
                        updateUfoList(ufos);
                    } else if (serverMessage.startsWith("SELECTED_UFO")) {
                        String json = serverMessage.substring("SELECTED_UFO ".length());
                        Ufo ufo = gson.fromJson(json, Ufo.class);
                        handleSelectedUfo(ufo);
                    } else if (serverMessage.startsWith("FORCE_START_GAME")) {
                        forceStartGame();
                    }
                }
            } catch (IOException e) {
                if (running) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void handleSelectedUfo(Ufo ufo) {
        this.selectedUfo = ufo;
    }

    private void updateUfoCount(int size) {
        presenter.updateUfoCount(size);
    }

    private void playCrashSound() {
        presenter.playCrashSound();
    }

    private void incrementCrashedUfoCount(int crashedUfos) {
        presenter.incrementCrashedUfoCount(crashedUfos);
    }

    private void playLandingSound() {
        presenter.playLandingSound();
    }

    private void incrementLandedUfoCount() {
        presenter.incrementLandedUfoCount();
    }

    private void updateConnectedPlayers(int increment) {
        presenter.updateConnectedPlayers(increment);
    }

    private void forceStartGame(){
        presenter.createUfoGamePlayView();
    }

    private void updateUfos() {
        requestUfosList();
        presenter.updateUFOs();
        presenter.updateUfoCount(ufos.size());
    }

    private void updateUfoList(List<Ufo> ufos) {
        this.ufos = ufos;
    }
}
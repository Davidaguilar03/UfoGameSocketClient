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
    private List<String> usersList;
    private static Gson gson = new Gson();
    private volatile boolean running = true;
    private ServerListener serverListener;

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
        startServerListener();
        presenter.updatePlayersList();
    }

    @Override
    public void stopConnection() throws IOException {
        running = false;
        sendMessage("DISCONNECT " + username);
        stopServerListener();
        out.close();
        clientSocket.close();
    }

    public void sendMessage(String msg) {
        out.println(msg);
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
    public void sendSelectedUfoDesign(String ufoDesign) {
        sendMessage("UFO_IMAGE " + ufoDesign);
    }

    @Override
    public void requestUfoDesign() {
        sendMessage("REQUEST_UFO_DESIGN ");
    }

    @Override
    public void startGame() {
        sendMessage("START_GAME ");
    }

    @Override
    public void requestUfosList() {
        sendMessage("REQUEST_UFO_LIST ");
    }

    @Override
    public void checkClientMode() {
        sendMessage("CHECK_CLIENT_MODE ");
    }

    @Override
    public void requestUsersList() {
        sendMessage("REQUEST_USERS_LIST ");
    }

    public void handleSelectedUfo(String serverMessage) {
        Ufo ufo = gson.fromJson(serverMessage, Ufo.class);
        this.selectedUfo = ufo;
    }

    public void handleUsersList(String serverMessage) {
        Type listType = new TypeToken<List<String>>() {
        }.getType();
        List<String> usersList = gson.fromJson(serverMessage, listType);
        this.usersList = usersList;
    }

    public void updateUfoCount(String serverMessage) {
        int size = Integer.parseInt(serverMessage);
        presenter.updateUfoCount(size);
    }

    public void playCrashSound() {
        presenter.playCrashSound();
    }

    public void incrementCrashedUfoCount(String serverMessage) {
        int crashedUfos = Integer.parseInt(serverMessage);
        presenter.incrementCrashedUfoCount(crashedUfos);
    }

    public void playLandingSound() {
        presenter.playLandingSound();
    }

    public void incrementLandedUfoCount() {
        presenter.incrementLandedUfoCount();
    }

    public void updateConnectedPlayers(String serverMessage) {
        int connectedPlayers = Integer.parseInt(serverMessage);
        presenter.updateConnectedPlayers(connectedPlayers);
    }

    public void updateUsernameList(){
        presenter.updatePlayersList();;
    }

    public void forceStartGame() {
        presenter.createUfoGamePlayView();
    }

    public void updateUfos() {
        requestUfosList();
        presenter.updateUFOs();
        presenter.updateUfoCount(ufos.size());
    }

    public void updateUfoList(String serverMessage) {
        Type listType = new TypeToken<List<Ufo>>() {
        }.getType();
        List<Ufo> ufos = gson.fromJson(serverMessage, listType);
        this.ufos = ufos;
    }

    public void updateUfoDesign(String serverMessage) {
        ;
        presenter.updateSelectedUfoDesign(serverMessage);
    }

    public void setClientMode() {
        presenter.setClientMode();
    }

    public void startServerListener() {
        serverListener = new ServerListener(this, in, running);
        new Thread(serverListener).start();
    }

    public void stopServerListener() {
        if (serverListener != null) {
            serverListener.stop();
        }
        running = false;
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
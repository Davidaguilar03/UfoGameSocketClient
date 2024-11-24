package co.edu.uptc.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import co.edu.uptc.interfaces.UfoGameInterface;
import co.edu.uptc.interfaces.UfoGameInterface.Presenter;
import co.edu.uptc.pojos.Ufo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UfoSocketClient implements UfoGameInterface.Model {
    private UfoGameInterface.Presenter presenter;
    private List<Ufo> Ufos;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String username;

    @Override
    public void startConnection(String ip, int port, String username) throws IOException {
        this.username = username;
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        sendMessage("CONNECT " + username);
    }

    
    @Override
    public void stopConnection() throws IOException {
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
        // TODO Auto-generated method stub
    }

    @Override
    public void sendSpeed(int speed) {
        // TODO Auto-generated method stub
    }

    @Override
    public void sendNumberofUfos(int numberofUfos) {
        // TODO Auto-generated method stub
    }

    public List<Ufo> receiveUfos() {
        // TODO Auto-generated method stub
        return null;
    }

}

package co.edu.uptc.presenters;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.interfaces.*;
import co.edu.uptc.interfaces.UfoGameInterface.Model;
import co.edu.uptc.interfaces.UfoGameInterface.View;
import co.edu.uptc.pojos.Ufo;
import lombok.Data;

@Data
public class UfoGamePresenter implements UfoGameInterface.Presenter {
    private UfoGameInterface.Model model;
    private UfoGameInterface.View view;

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public List<Ufo> getUfos() {
        return model.getUfos();
    }

    @Override
    public void updateUFOs() {
        view.updateUFOs();
    }

    @Override
    public void sendSpawnRate(int spawnRate) {
        model.sendSpawnRate(spawnRate);
    }

    @Override
    public void sendSpeed(int speed) {
        model.sendSpeed(Math.max(speed, 2)); 
    }

    @Override
    public void sendNumberofUfos(int numberofUfos) {
        model.sendNumberofUfos(numberofUfos);
    }


    @Override
    public void incrementCrashedUfoCount(int increment) {
        view.incrementCrashedUfoCount(increment);
    }

    @Override
    public void updateUfoCount(int ufoCount) {
        view.updateUfoCount(ufoCount);
    }

    @Override
    public void incrementLandedUfoCount() {
        view.incrementLandedUfoCount();
    }

    @Override
    public void playCrashSound() {
        view.playCrashSound();
    }

    @Override
    public void playLandingSound() {
        view.playLandingSound();
    }

    @Override
    public void startConnection(String ip, int port, String username) throws Exception {
        model.startConnection(ip, port, username);
    }

    @Override
    public void stopConnection() throws Exception {
        model.stopConnection();
    }

    @Override
    public void startGame() {
        model.startGame();
    }

    @Override
    public void requestUfosList() {
        model.requestUfosList();
    }

    @Override
    public void sendTrajectoryToServer(ArrayList<Point> trajectoryPoints) {
        model.sendTrajectoryToServer(trajectoryPoints);
    }

    @Override
    public void sendSelectedPoint(Point point) throws Exception {
        model.sendSelectedPoint(point);
    }

    @Override
    public Ufo getSelectedUfo() {
        return model.getSelectedUfo();
    }

    @Override
    public void updateConnectedPlayers(int connectedPlayers) {
        view.updateConnectedPlayers(connectedPlayers);
    }

    @Override
    public void createUfoGamePlayView() {
        view.createUfoGamePlayView();
    }

    @Override
    public void sendSelectedUfoDesign(String ufoDesign) {
        model.sendSelectedUfoDesign(ufoDesign);
    }

    @Override
    public void requestUfoDesign() {
        model.requestUfoDesign();
    }

    @Override
    public void updateSelectedUfoDesign(String ufoDesign) {
        view.updateSelectedUfoDesign(ufoDesign);
    }

    @Override
    public void setClientMode() {
        view.setClientMode();
    }

    @Override
    public void checkClientMode() {
        model.checkClientMode();
    }

}

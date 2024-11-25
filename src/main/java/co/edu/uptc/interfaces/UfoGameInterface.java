package co.edu.uptc.interfaces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import co.edu.uptc.pojos.Ufo;

public class UfoGameInterface {

    public interface Model {
        public List<Ufo> getUfos();

        public Ufo getSelectedUfo();        

        public void startGame();

        public void sendSpawnRate(int spawnRate);

        public void sendSpeed(int speed);

        public void sendNumberofUfos(int numberofUfos);

        public void setPresenter(Presenter presenter);

        public void startConnection(String ip, int port, String username) throws Exception;

        public void stopConnection() throws Exception;

        public void requestUfosList();

        public void sendTrajectoryToServer(ArrayList<Point> trajectoryPoints);

        public void sendSelectedPoint(Point point) throws Exception;
    }

    public interface View {
        public void begin();

        public void updateUFOs();

        public void setPresenter(Presenter presenter);

        public void updateUfoCount(int ufoCount);

        public void incrementCrashedUfoCount(int increment);

        public void incrementLandedUfoCount();

        public void playCrashSound();

        public void playLandingSound();
    }

    public interface Presenter {

        public List<Ufo> getUfos();

        public Ufo getSelectedUfo(); 

        public void updateUFOs();

        public void startGame();
        
        public void sendSpawnRate(int spawnRate);

        public void sendSpeed(int speed);

        public void sendNumberofUfos(int numberofUfos);

        public void updateUfoCount(int ufoCount);

        public void incrementCrashedUfoCount(int increment);
        
        public void incrementLandedUfoCount();

        public void setView(View view);

        public void setModel(Model model);

        public void playCrashSound();

        public void playLandingSound();

        public void startConnection(String ip, int port, String username) throws Exception;

        public void stopConnection() throws Exception;
        
        public void requestUfosList();

        public void sendTrajectoryToServer(ArrayList<Point> trajectoryPoints);

        public void sendSelectedPoint(Point point) throws Exception;
    }
}

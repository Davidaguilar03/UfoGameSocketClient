package co.edu.uptc.interfaces;

import java.util.List;
import co.edu.uptc.pojos.Ufo;

public class UfoGameInterface {

    public interface Model {
        public void startGame();

        public List<Ufo> getUfos();

        public void setSpawnRate(int spawnRate);

        public void setSpeed(int speed);

        public void setNumberofUfos(int numberofUfos);

        public void setPresenter(Presenter presenter);
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
        public void startGame();

        public List<Ufo> getUfos();

        public void updateUFOs();
        
        public void setSpawnRate(int spawnRate);
        
        public void setSpeed(int speed);
        
        public void setNumberofUfos(int numberofUfos);

        public void updateUfoCount(int ufoCount);

        public void incrementCrashedUfoCount(int increment);
        
        public void incrementLandedUfoCount();

        public void setView(View view);

        public void setModel(Model model);

        public void playCrashSound();

        public void playLandingSound();
    }
}

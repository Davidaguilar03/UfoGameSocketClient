package co.edu.uptc.models;

//import java.util.Iterator;
import java.util.List;
//import java.util.concurrent.CopyOnWriteArrayList;

import co.edu.uptc.interfaces.UfoGameInterface;
import co.edu.uptc.interfaces.UfoGameInterface.Presenter;
import co.edu.uptc.pojos.Ufo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UfoSocketClient implements UfoGameInterface.Model {
    private UfoGameInterface.Presenter presenter;
    // private UfoRunner ufoRunner;
    // private SpawnRunner spawnRunner;
    // private List<Ufo> Ufos;
    // private int spawnRate;
    // private int speed;
    // private int numberofUfos;

    public UfoSocketClient() {
        // this.Ufos = new CopyOnWriteArrayList<>();
        // ufoRunner = new UfoRunner(this);
        // spawnRunner = new SpawnRunner(this);
    }

    public synchronized void addUfo(int speed) {
        // UfoController ufoController = new UfoController(this);
        // Ufo newUfo = ufoController.createUfo(speed);
        // Ufos.add(newUfo);
    }

    @Override
    public void startGame() {
        // Thread moveThread = new Thread(ufoRunner);
        // moveThread.start();
        // Thread spawnThread = new Thread(spawnRunner);
        // spawnThread.start();
    }

    @Override
    public List<Ufo> getUfos() {
            return null;
        //return Ufos;
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void setSpawnRate(int spawnRate) {
        //this.spawnRate = spawnRate;
    }

    @Override
    public void setSpeed(int speed) {
        //this.speed = speed;
    }

    @Override
    public void setNumberofUfos(int numberofUfos) {
        //this.numberofUfos = numberofUfos;
    }
    
    public synchronized void moveAll() {
        // Iterator<Ufo> iterator = Ufos.iterator();
        // while (iterator.hasNext()) {
        //     Ufo ufo = iterator.next();
        //     UfoController ufoController = new UfoController(this);
        //     ufoController.moveUfo(ufo,Ufos);
        //     presenter.updateUfoCount(Ufos.size());
        // }
    }
}

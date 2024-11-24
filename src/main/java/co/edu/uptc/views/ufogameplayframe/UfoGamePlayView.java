package co.edu.uptc.views.ufogameplayframe;

import javax.swing.JFrame;
import co.edu.uptc.views.ufogamemainframe.UfoGameView;
import lombok.Getter;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
@Getter
public class UfoGamePlayView extends JFrame{
    private UfoGameView ufoGameView;
    private UfoGamePlayHeader ufoGamePlayHeader;
    private UfoGamePlayBody ufoGamePlayBody;

    public UfoGamePlayView(UfoGameView ufoGameView){
        this.ufoGameView=ufoGameView;
        this.initFrame();
        this.createUfoGamePlayBody();
        this.createUfoGamePlayHeader();
    }
    
    public void begin(){
        this.setVisible(true);
        ufoGameView.getPresenter().startGame();
    }

    private void initFrame(){
        this.setUndecorated(true);
        this.setLayout((LayoutManager) new BorderLayout());
        this.setSize(1200,756);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    private void createUfoGamePlayHeader(){
        ufoGamePlayHeader = new UfoGamePlayHeader(this);
        this.add(ufoGamePlayHeader,BorderLayout.NORTH);
    }

    private void createUfoGamePlayBody(){
        ufoGamePlayBody = new UfoGamePlayBody(this);
        this.add(ufoGamePlayBody,BorderLayout.CENTER);
    }

    public void updateUFOs() {
        ufoGamePlayBody.updateUFOs();
    }
}

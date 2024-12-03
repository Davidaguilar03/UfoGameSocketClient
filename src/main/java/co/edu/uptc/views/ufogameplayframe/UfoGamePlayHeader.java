package co.edu.uptc.views.ufogameplayframe;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

import co.edu.uptc.utilities.RoundedButton;
import co.edu.uptc.views.GlobalView;
import lombok.Getter;

@Getter
public class UfoGamePlayHeader extends JPanel {
    private UfoGamePlayView ufoGamePlayView;
    private JLabel pointsCounter;
    private int ufoCount;
    private int crashedUfoCount;
    private int landedUfoCount;
    private int connectedPlayers;
    private JPanel playersUsernamePanel;

    public UfoGamePlayHeader(UfoGamePlayView ufoGamePlayView) {
        this.ufoGamePlayView = ufoGamePlayView;
        initPanel();
        createPointsCounter();
        createExitBtn();
        createPlayersUsernameList();
    }
    
    private void initPanel() {
        this.setBackground(GlobalView.BODY_PLAY_BACKGROUND);
        this.setForeground(GlobalView.BODY_PLAY_FOREGROUND);
        this.setPreferredSize(new Dimension(0, 120));
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, GlobalView.BORDER_COLOR));
        this.setLayout(null);
    }
    
    private void createPointsCounter() {
        pointsCounter = new JLabel(
            "OVNIs en Movimiento: 0 | OVNIs Estrellados: 0 | OVNIs Aterrizados: 0 | Jugadores Conectados: 0");
            pointsCounter.setFont(new Font("Arial", Font.PLAIN, 24));
            pointsCounter.setBounds(20, 10, 1200, 50);
            pointsCounter.setForeground(GlobalView.SECUNDARY_BTN_TEXT_BACKGROUND);
            this.add(pointsCounter);
        }
        
        public void updateCounters() {
            pointsCounter.setText("OVNIs en Movimiento: " + ufoCount + " | OVNIs Estrellados: " + crashedUfoCount
            + " | OVNIs Aterrizados: " + landedUfoCount + " | Jugadores Conectados: " + connectedPlayers);
        }
        
        public void createPlayersUsernameList() {
            playersUsernamePanel = new JPanel();
            playersUsernamePanel.setLayout(new BoxLayout(playersUsernamePanel, BoxLayout.X_AXIS));
            playersUsernamePanel.setBounds(20, 60, 1040, 40); 
            playersUsernamePanel.setBackground(GlobalView.HEADER_MENU_BACKGROUND);
            playersUsernamePanel.setForeground(GlobalView.BODY_MENU_BACKGROUND);
            this.add(playersUsernamePanel);
            updatePlayersList();
        }
        
        public void addPlayerUsername(String username) {
            JLabel playerLabel = new JLabel("-"+username+"");
            playerLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            playerLabel.setForeground(GlobalView.BODY_MENU_BACKGROUND);
            playersUsernamePanel.add(playerLabel);
            playersUsernamePanel.revalidate();
            playersUsernamePanel.repaint();
        }
        
        public void updatePlayersList() {
            playersUsernamePanel.removeAll();
            for (String username : ufoGamePlayView.getUfoGameView().getPresenter().getUsersList()) {
                addPlayerUsername(username);
        }
    }

    public void updateUfoCount(int ufoCount) {
        this.ufoCount = ufoCount;
        updateCounters();
    }

    public void incrementCrashedUfoCount(int increment) {
        crashedUfoCount += increment;
        updateCounters();
    }

    public void incrementLandedUfoCount() {
        landedUfoCount++;
        updateCounters();
    }

    public void incrementConnectedPlayers(int increment) {
        connectedPlayers += increment;
        updateCounters();
    }

    private void createExitBtn() {
        RoundedButton exitBtn = new RoundedButton("<html><div style='text-align: center;'>X</html>", 20);
        configureExitBtn(exitBtn);
        addExitBtnActionListener(exitBtn);
        this.add(exitBtn);
    }

    private void configureExitBtn(RoundedButton exitBtn) {
        exitBtn.setBounds(1140, 10, 50, 50);
        exitBtn.setBackground(GlobalView.BTN_BACKGROUND);
        exitBtn.setForeground(GlobalView.BTN_FOREGROUND);
    }

    private void addExitBtnActionListener(RoundedButton exitBtn) {
        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleExitAction();
            }
        });
    }

    private void handleExitAction() {
        try {
            ufoGamePlayView.getUfoGameView().getPresenter().stopConnection();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.exit(0);
    }
}
